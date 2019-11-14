package org.gbif.pipelines.core.interpreters.core;

import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.gbif.api.model.registry.MachineTag;
import org.gbif.api.util.VocabularyUtils;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.TagName;
import org.gbif.common.parsers.LicenseParser;
import org.gbif.pipelines.io.avro.ExtendedRecord;
import org.gbif.pipelines.io.avro.MetadataRecord;
import org.gbif.pipelines.parsers.ws.client.metadata.MetadataServiceClient;
import org.gbif.pipelines.parsers.ws.client.metadata.response.Dataset;
import org.gbif.pipelines.parsers.ws.client.metadata.response.Network;
import org.gbif.pipelines.parsers.ws.client.metadata.response.Organization;

import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** Interprets GBIF metadata by datasetId */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MetadataInterpreter {

  public static final String DEFAULT_TERM_NAMESPACE = "default-term.gbif.org";

  /** Gets information from GBIF API by datasetId */
  public static BiConsumer<String, MetadataRecord> interpret(MetadataServiceClient client) {
    return (datasetId, mdr) -> {
      if (client != null) {

        mdr.setDatasetKey(datasetId);

        Dataset dataset = client.getDataset(datasetId);
        mdr.setDatasetTitle(dataset.getTitle());
        mdr.setInstallationKey(dataset.getInstallationKey());
        mdr.setPublishingOrganizationKey(dataset.getPublishingOrganizationKey());
        Optional.ofNullable(getLicense(dataset.getLicense())).ifPresent(license -> mdr.setLicense(license.name()));

        List<Network> networkList = client.getNetworkFromDataset(datasetId);
        if (networkList != null && !networkList.isEmpty()) {
          mdr.setNetworkKeys(networkList.stream().map(Network::getKey).collect(Collectors.toList()));
        } else {
          mdr.setNetworkKeys(Collections.emptyList());
        }

        Organization organization = client.getOrganization(dataset.getPublishingOrganizationKey());
        mdr.setEndorsingNodeKey(organization.getEndorsingNodeKey());
        mdr.setPublisherTitle(organization.getTitle());
        mdr.setDatasetPublishingCountry(organization.getCountry());
        getLastCrawledDate(dataset.getMachineTags()).ifPresent(d -> mdr.setLastCrawled(d.getTime()));
        copyMachineTags(dataset, mdr);
      }
    };
  }

  /** Sets attempt number as crawlId */
  public static Consumer<MetadataRecord> interpretCrawlId(Integer attempt) {
    return mdr -> Optional.ofNullable(attempt).ifPresent(mdr::setCrawlId);
  }

  /** Gets information about dataset source endpoint type (DWC_ARCHIVE, BIOCASE_XML_ARCHIVE, TAPIR .. etc) */
  public static Consumer<MetadataRecord> interpretEndpointType(String endpointType) {
    return mdr -> {
      if (!Strings.isNullOrEmpty(endpointType)) {
        com.google.common.base.Optional<EndpointType> lookup = VocabularyUtils.lookup(endpointType, EndpointType.class);
        if (lookup.isPresent()) {
          mdr.setProtocol(lookup.get().name());
        }
      }
    };
  }

  /** Replace all the default verbatim values with the values defined as MachineTags with the namespace 'default-term.gbif.org'.*/
  public static Consumer<ExtendedRecord> interpretDefaultValues(MetadataRecord mr) {
    return er -> {
      if (Objects.nonNull(mr.getMachineTags())) {
        mr.getMachineTags()
          .stream()
          .filter(tag -> DEFAULT_TERM_NAMESPACE.equalsIgnoreCase(tag.getNamespace()))
          .forEach(defaultValTag -> er.getCoreTerms().replace(defaultValTag.getName(), defaultValTag.getValue()));
      }
    };
  }

  /** Returns ENUM instead of url string */
  private static License getLicense(String url) {
    URI uri = Optional.ofNullable(url).map(x -> {
      try {
        return URI.create(x);
      } catch (IllegalArgumentException ex) {
        return null;
      }
    }).orElse(null);
    License license = LicenseParser.getInstance().parseUriThenTitle(uri, null);
    //UNSPECIFIED must be mapped to null
    return License.UNSPECIFIED == license ? null : license;
  }

  /** Gets the latest crawl attempt time, if exists. */
  private static Optional<Date> getLastCrawledDate(List<MachineTag> machineTags) {
    if (Objects.nonNull(machineTags)) {
      return machineTags.stream()
          .filter(tag -> TagName.CRAWL_ATTEMPT.getName().equals(tag.getName())
              && TagName.CRAWL_ATTEMPT.getNamespace().getNamespace().equals(tag.getNamespace()))
          .sorted(Comparator.comparing(MachineTag::getCreated).reversed())
          .map(MachineTag::getCreated)
          .findFirst();
    }
    return Optional.empty();
  }

  /** Copies dataset.machineTags to mr.machineTags. */
  private static void copyMachineTags(Dataset dataset, MetadataRecord mdr) {
    if (Objects.nonNull(dataset.getMachineTags())) {
      mdr.setMachineTags(dataset.getMachineTags()
                          .stream()
                          .map(machineTag -> org.gbif.pipelines.io.avro.MachineTag.newBuilder()
                            .setKey(machineTag.getKey())
                            .setNamespace(machineTag.getNamespace())
                            .setName(machineTag.getName())
                            .setValue(machineTag.getValue())
                            .setCreatedBy(machineTag.getCreatedBy())
                            .setCreated(machineTag.getCreated().getTime())
                            .build())
                          .collect(Collectors.toList()));
    }
  }
}
