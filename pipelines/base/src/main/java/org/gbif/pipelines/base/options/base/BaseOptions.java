package org.gbif.pipelines.base.options.base;

import java.util.List;
import java.util.Optional;

import org.apache.beam.sdk.io.hdfs.HadoopFileSystemOptions;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.DefaultValueFactory;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.CommonConfigurationKeysPublic;

public interface BaseOptions extends PipelineOptions {

  @Description("Id of the dataset used to name the target file in file system.")
  @Validation.Required
  String getDatasetId();

  void setDatasetId(String id);

  @Description("Attempt of the dataset used to name the target file in file system.")
  @Validation.Required
  Integer getAttempt();

  void setAttempt(Integer attempt);

  @Description("Path of the input file.")
  String getInputPath();

  void setInputPath(String inputPath);

  @Description("Target path where the outputs of the pipeline will be written to. Required.")
  @Validation.Required
  @Default.InstanceFactory(DefaultDirectoryFactory.class)
  String getTargetPath();

  void setTargetPath(String targetPath);

  @Description("If set to true it writes the outputs of every step of the pipeline")
  @Default.Boolean(false)
  boolean getWriteOutput();

  void setWriteOutput(boolean writeOutput);

  @Description("Avro compression type")
  @Default.String("SNAPPY")
  String getAvroCompressionType();

  void setAvroCompressionType(String compressionType);

  @Description("Avro sync interval time")
  @Default.Integer(2_097_152)
  int getAvroSyncInterval();

  void setAvroSyncInterval(int syncInterval);

  /** A {@link DefaultValueFactory} which locates a default directory. */
  class DefaultDirectoryFactory implements DefaultValueFactory<String> {

    public static Optional<String> getDefaultFs(PipelineOptions options) {
      List<Configuration> configs =
          options.as(HadoopFileSystemOptions.class).getHdfsConfiguration();
      if (configs != null && !configs.isEmpty()) {
        // we take the first config as default
        return Optional.ofNullable(
            configs.get(0).get(CommonConfigurationKeysPublic.FS_DEFAULT_NAME_KEY));
      }
      return Optional.empty();
    }

    @Override
    public String create(PipelineOptions options) {
      // return root dir if no configurations are provided
      return getDefaultFs(options).orElse("hdfs://");
    }
  }
}