package org.gbif.pipelines.standalone;

import org.gbif.pipelines.ingest.options.DwcaPipelineOptions;
import org.gbif.pipelines.ingest.options.PipelinesOptionsFactory;
import org.gbif.pipelines.ingest.pipelines.DwcaToEsIndexPipeline;
import org.gbif.pipelines.ingest.pipelines.DwcaToInterpretedPipeline;
import org.gbif.pipelines.ingest.pipelines.DwcaToVerbatimPipeline;
import org.gbif.pipelines.ingest.pipelines.InterpretedToEsIndexAmpPipeline;
import org.gbif.pipelines.ingest.pipelines.InterpretedToEsIndexExtendedPipeline;
import org.gbif.pipelines.ingest.pipelines.VerbatimToInterpretedAmpPipeline;
import org.gbif.pipelines.ingest.pipelines.VerbatimToInterpretedPipeline;
import org.gbif.pipelines.ingest.pipelines.XmlToEsIndexPipeline;
import org.gbif.pipelines.ingest.pipelines.XmlToInterpretedPipeline;
import org.gbif.pipelines.ingest.pipelines.XmlToVerbatimPipeline;

/**
 * Entry point for running one of the four pipelines:
 *
 * <pre>
 *  1) From DwCA to ExtendedRecord *.avro file
 *  2) From DwCA to GBIF interpreted *.avro files
 *  3) From DwCA to Elasticsearch index
 *  4) From XML to ExtendedRecord *.avro file
 *  5) From XML to GBIF interpreted *.avro files
 *  6) From XML to Elasticsearch index
 *  7) From GBIF interpreted *.avro files to Elasticsearch index
 *  8) From ExtendedRecord *.avro file to GBIF interpreted *.avro files
 *  9) From interpreted amplification extension *.avro files to appended Elasticsearch index
 *  10) From ExtendedRecord *.avro file to interpreted amplification extension *.avro files
 * </pre>
 *
 * Please read README.md
 */
public class DwcaPipeline {

  public static void main(String[] args) {

    // Create PipelineOptions
    DwcaPipelineOptions options = PipelinesOptionsFactory.create(DwcaPipelineOptions.class, args);

    switch (options.getPipelineStep()) {
      case DWCA_TO_VERBATIM:
        DwcaToVerbatimPipeline.run(options);
        break;
      case DWCA_TO_INTERPRETED:
        DwcaToInterpretedPipeline.run(options);
        break;
      case DWCA_TO_ES_INDEX:
        DwcaToEsIndexPipeline.run(options);
        break;
      case XML_TO_VERBATIM:
        XmlToVerbatimPipeline.run(options);
        break;
      case XML_TO_INTERPRETED:
        XmlToInterpretedPipeline.run(options);
        break;
      case XML_TO_ES_INDEX:
        XmlToEsIndexPipeline.run(options);
        break;
      case INTERPRETED_TO_ES_INDEX:
        options.setTargetPath(options.getInputPath());
        PipelinesOptionsFactory.registerHdfs(options);
        InterpretedToEsIndexExtendedPipeline.run(options);
        break;
      case VERBATIM_TO_INTERPRETED:
        PipelinesOptionsFactory.registerHdfs(options);
        VerbatimToInterpretedPipeline.run(options);
        break;
      case INTERPRETED_TO_ES_INDEX_AMP:
        options.setTargetPath(options.getInputPath());
        PipelinesOptionsFactory.registerHdfs(options);
        InterpretedToEsIndexAmpPipeline.run(options);
        break;
      case VERBATIM_TO_INTERPRETED_AMP:
        PipelinesOptionsFactory.registerHdfs(options);
        VerbatimToInterpretedAmpPipeline.run(options);
        break;
      default:
        break;
    }
  }
}
