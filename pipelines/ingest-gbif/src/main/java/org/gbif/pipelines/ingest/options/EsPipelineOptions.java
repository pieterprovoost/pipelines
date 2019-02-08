package org.gbif.pipelines.ingest.options;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

/** Main pipeline options necessary for Elasticsearch index properties */
public interface EsPipelineOptions extends PipelineOptions {

  @Description("Target ES Max Batch Size bytes")
  @Default.Long(8_388_608L) // 10mb
  Long getEsMaxBatchSizeBytes();

  void setEsMaxBatchSizeBytes(Long batchSize);

  @Description("Es max batch size")
  @Default.Long(8_000L)
  long getEsMaxBatchSize();

  void setEsMaxBatchSize(long esBatchSize);

  @Description("List of Elasticsearch hosts. Required for the DWCA_TO_ES_INDEX step.")
  String[] getEsHosts();

  void setEsHosts(String[] esHosts);

  @Description("Name of the Elasticsearch index that will be used to index the records")
  String getEsIndexName();

  void setEsIndexName(String esIndexName);

  @Description("Name of the Elasticsearch aliases. The index created will be added to this aliases.")
  String[] getEsAlias();

  void setEsAlias(String[] esAlias);

  @Description("Path to an occurrence indexing schema")
  @Default.String("elasticsearch/es-occurrence-shcema.json")
  String getEsSchemaPath();

  void setEsSchemaPath(String esSchemaPath);

  @Description("How often to perform a refresh operation, which makes recent changes to the index visible to search. Defaults to 30s")
  @Default.String("30s")
  String getIndexRefreshInterval();

  void setIndexRefreshInterval(String indexRefreshInterval);

  @Description("The value of this setting determines the number of primary shards in the target index. The default value is 3.")
  @Default.Integer(3)
  Integer getIndexNumberShards();

  void setIndexNumberShards(Integer indexNumberShards);

  @Description(
      "The value of this setting determines the number of replica shards per primary shard in the target index. The default value is 0.")
  @Default.Integer(1)
  Integer getIndexNumberReplicas();

  void setIndexNumberReplicas(Integer indexNumberReplicas);
}
