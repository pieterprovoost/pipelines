package org.gbif.converters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.gbif.pipelines.io.avro.ExtendedRecord;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DwcaToAvroConverterTest {

  private final String inpPath = getClass().getResource("/dwca/plants_dwca").getFile();
  private final String outPath = inpPath + "verbatim.avro";

  @Test
  public void avroDeserializingNoramlIdTest() throws IOException {

    // When
    DwcaToAvroConverter.create().inputPath(inpPath).outputPath(outPath).convert();

    // Should
    File verbatim = new File(outPath);
    Assert.assertTrue(verbatim.exists());

    // Deserialize ExtendedRecord from disk
    DatumReader<ExtendedRecord> datumReader = new SpecificDatumReader<>(ExtendedRecord.class);
    try (DataFileReader<ExtendedRecord> dataFileReader = new DataFileReader<>(verbatim, datumReader)) {
      while (dataFileReader.hasNext()) {
        ExtendedRecord record = dataFileReader.next();
        Assert.assertNotNull(record);
        Assert.assertNotNull(record.getId());
      }
    }

    Files.deleteIfExists(verbatim.toPath());

  }

}
