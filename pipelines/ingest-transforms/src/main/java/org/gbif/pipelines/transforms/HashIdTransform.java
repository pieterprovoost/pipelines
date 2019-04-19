package org.gbif.pipelines.transforms;

import org.gbif.pipelines.core.utils.HashUtils;
import org.gbif.pipelines.io.avro.ExtendedRecord;
import org.gbif.pipelines.transforms.converters.GbifJsonTransform;

import org.apache.beam.sdk.metrics.Counter;
import org.apache.beam.sdk.metrics.Metrics;
import org.apache.beam.sdk.transforms.DoFn;

import lombok.AllArgsConstructor;

import static org.gbif.pipelines.common.PipelinesVariables.Metrics.HASH_ID_COUNT;

@AllArgsConstructor(staticName = "create")
public class HashIdTransform extends DoFn<ExtendedRecord, ExtendedRecord> {

  private final Counter counter = Metrics.counter(GbifJsonTransform.class, HASH_ID_COUNT);

  // Id prefix
  private final String datasetId;

  @ProcessElement
  public void processElement(@Element ExtendedRecord er, OutputReceiver<ExtendedRecord> out) {
    String id = HashUtils.getSha1(datasetId, er.getId());
    out.output(ExtendedRecord.newBuilder(er).setId(id).build());
    counter.inc();
  }
}
