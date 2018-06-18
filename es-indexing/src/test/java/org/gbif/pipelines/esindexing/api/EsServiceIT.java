package org.gbif.pipelines.esindexing.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.elasticsearch.client.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.gbif.pipelines.esindexing.common.EsConstants.MAPPINGS_FIELD;
import static org.gbif.pipelines.esindexing.common.SettingsType.INDEXING;
import static org.gbif.pipelines.esindexing.common.SettingsType.SEARCH;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the {@link EsService}.
 */
public class EsServiceIT extends EsApiIntegrationTest {

  private static final String ALIAS_TEST = "alias";

  /**
   * {@link Rule} requires this field to be public.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void cleanIndexes() {
    EsService.deleteAllIndexes(esServer.getEsClient());
  }

  @Test
  public void createIndexTest() {
    String idx = EsService.createIndex(esServer.getEsClient(), "idx", INDEXING);

    // check directly with the rest client to isolate the test
    try {
      Response response = esServer.getRestClient().performRequest(HttpGet.METHOD_NAME, "/" + idx);
      assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    } catch (IOException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void createIndexWithSettingsAndMappingsTest() {
    String idx = EsService.createIndex(esServer.getEsClient(), "idx-settings", INDEXING, TEST_MAPPINGS_PATH);

    // check that the index was created as expected
    assertTrue(EsService.existsIndex(esServer.getEsClient(), idx));

    // check settings
    assertIndexingSettings(idx);

    // check mappings
    JsonNode mappings = getMappingsFromIndex(idx).path(idx).path(MAPPINGS_FIELD);
    assertTrue(mappings.has("doc"));
    assertTrue(mappings.path("doc").path("properties").has("test"));
    assertEquals("text", mappings.path("doc").path("properties").path("test").get("type").asText());
  }

  @Test
  public void createAndUpdateIndexWithSettingsTest() {
    String idx = EsService.createIndex(esServer.getEsClient(), "idx-settings", INDEXING);

    // check that the index was created as expected
    assertTrue(EsService.existsIndex(esServer.getEsClient(), idx));

    // check settings
    assertIndexingSettings(idx);

    EsService.updateIndexSettings(esServer.getEsClient(), idx, SEARCH);

    // check settings
    assertSearchSettings(idx);
  }

  @Test
  public void updateMissingIndexTest() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage(CoreMatchers.containsString("Error updating index"));

    EsService.updateIndexSettings(esServer.getEsClient(), "fake-index", INDEXING);
  }

  @Test
  public void createWrongIndexTest() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage(CoreMatchers.containsString("Error creating index"));

    EsService.createIndex(esServer.getEsClient(), "UPPERCASE", INDEXING);
  }

  @Test
  public void duplicatedIndexTest() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage(CoreMatchers.containsString("Error creating index"));

    String idx1 = EsService.createIndex(esServer.getEsClient(), "idx", INDEXING);
    String idx2 = EsService.createIndex(esServer.getEsClient(), "idx", INDEXING);
  }

  @Test
  public void getIndexesByAliasAndSwapIndexTest() {
    // create some indexes to test
    String idx1 = EsService.createIndex(esServer.getEsClient(), "idx1", INDEXING);
    String idx2 = EsService.createIndex(esServer.getEsClient(), "idx2", INDEXING);
    String idx3 = EsService.createIndex(esServer.getEsClient(), "idx3", INDEXING);
    Set<String> initialIndexes = new HashSet<>(Arrays.asList(idx1, idx2, idx3));

    // there shouldn't be indexes before we start
    Set<String> indexes = EsService.getIndexesByAliasAndIndexPattern(esServer.getEsClient(), "idx*", ALIAS_TEST);
    assertEquals(0, indexes.size());

    // add them to the same alias
    addIndexesToAlias(ALIAS_TEST, initialIndexes);

    // get the indexes of the alias
    indexes = EsService.getIndexesByAliasAndIndexPattern(esServer.getEsClient(), "idx*", ALIAS_TEST);

    // assert conditions
    assertEquals(3, indexes.size());
    assertTrue(indexes.containsAll(initialIndexes));

    // create a new index and swap it to the alias
    String idx4 = EsService.createIndex(esServer.getEsClient(), "idx4", INDEXING);
    EsService.swapIndexes(esServer.getEsClient(), ALIAS_TEST, Collections.singleton(idx4), initialIndexes);
    assertSwapResults(idx4, "idx*", ALIAS_TEST, initialIndexes);

    // repeat previous step with a new index
    String idx5 = EsService.createIndex(esServer.getEsClient(), "idx5", INDEXING);
    EsService.swapIndexes(esServer.getEsClient(), ALIAS_TEST, Collections.singleton(idx5), Collections.singleton(idx4));
    assertSwapResults(idx5, "idx*", ALIAS_TEST, Collections.singleton(idx4));
  }

  @Test
  public void getIndexesFromMissingAliasTest() {
    Set<String> idx = EsService.getIndexesByAliasAndIndexPattern(esServer.getEsClient(), "idx*", "fake-alias");
    assertTrue(idx.isEmpty());
  }

  @Test
  public void swapEmptyAliasTest() {
    String idx1 = EsService.createIndex(esServer.getEsClient(), "idx1", INDEXING);
    EsService.swapIndexes(esServer.getEsClient(), ALIAS_TEST, Collections.singleton(idx1), Collections.emptySet());
    assertSwapResults(idx1, "idx*", ALIAS_TEST, Collections.emptySet());
  }

  @Test
  public void swapMissingIndexTest() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage(CoreMatchers.containsString("Error swapping index"));

    EsService.swapIndexes(esServer.getEsClient(),
                          "fake-alias",
                          Collections.singleton("fake-index"),
                          Collections.emptySet());
  }

  @Test
  public void countEmptyIndexTest() {
    // create the index
    String idx = EsService.createIndex(esServer.getEsClient(), "idx_1", SEARCH);

    // should have 0 documents
    assertEquals(0L, EsService.countIndexDocuments(esServer.getEsClient(), idx));
  }

  /**
   * It also tests indirectly the methods {@link EsService#indexDocument}, {@link EsService#deleteDocument} and
   * {@link EsService#refreshIndex}
   */
  @Test
  public void countIndexDocumentsTest() {
    // create the index using default settings
    String idx = EsService.createIndex(esServer.getEsClient(), "idx_1", Collections.emptyMap(), TEST_MAPPINGS_PATH);

    // index some documents
    long n = 3;
    final String type = "doc";
    String document = "{\"test\" : \"test value\"}";
    for (int i = 1; i <= n; i++) {
      EsService.indexDocument(esServer.getEsClient(), idx, type, i, document);
    }

    // they shouldn't be searchable yet.
    assertEquals(0, EsService.countIndexDocuments(esServer.getEsClient(), idx));

    // refresh the index to make all the documents searchable.
    EsService.refreshIndex(esServer.getEsClient(), idx);

    // assert results
    assertEquals(n, EsService.countIndexDocuments(esServer.getEsClient(), idx));

    // delete last document
    EsService.deleteDocument(esServer.getEsClient(), idx, type, n);
    EsService.refreshIndex(esServer.getEsClient(), idx);

    // assert results again
    assertEquals(n - 1, EsService.countIndexDocuments(esServer.getEsClient(), idx));
  }

  @Test
  public void countMissingIndexTest() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage(CoreMatchers.containsString("Could not get count from index"));

    EsService.countIndexDocuments(esServer.getEsClient(), "fake");
  }

  @Test
  public void existsIndexTest() {
    String idx1 = EsService.createIndex(esServer.getEsClient(), "idx1", INDEXING);
    assertTrue(EsService.existsIndex(esServer.getEsClient(), idx1));
  }

  @Test
  public void existsMissingIndexTest() {
    assertFalse(EsService.existsIndex(esServer.getEsClient(), "missing"));
  }

  @Test
  public void deleteAllIndicesTest() {
    String idx1 = EsService.createIndex(esServer.getEsClient(), "idx1", INDEXING);
    String idx2 = EsService.createIndex(esServer.getEsClient(), "idx2", INDEXING);

    // they should exist
    assertTrue(EsService.existsIndex(esServer.getEsClient(), idx1));
    assertTrue(EsService.existsIndex(esServer.getEsClient(), idx2));

    // delete all of them
    EsService.deleteAllIndexes(esServer.getEsClient());

    // they shouldn't exist now
    assertFalse(EsService.existsIndex(esServer.getEsClient(), idx1));
    assertFalse(EsService.existsIndex(esServer.getEsClient(), idx2));
  }

}
