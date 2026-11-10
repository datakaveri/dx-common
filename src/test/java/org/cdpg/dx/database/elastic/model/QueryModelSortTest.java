package org.cdpg.dx.database.elastic.model;

import static org.assertj.core.api.Assertions.assertThat;

import co.elastic.clients.elasticsearch._types.SortOptions;
import io.vertx.core.json.JsonObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("QueryModel sort ordering")
class QueryModelSortTest {

  private static Map<String, String> sortFields() {
    Map<String, String> fields = new LinkedHashMap<>();
    fields.put("itemCreatedAt", "desc");
    fields.put("name", "asc");
    return fields;
  }

  private static List<String> describe(List<SortOptions> options) {
    return options.stream()
        .map(o -> o.isScore() ? "_score" : o.field().field() + ":" + o.field().order().jsonValue())
        .toList();
  }

  @Test
  @DisplayName("without text query: requested fields first, _score last")
  void fieldsBeforeScore() {
    QueryModel model = new QueryModel();
    model.setSortFields(sortFields());

    assertThat(describe(model.toSortOptions()))
        .containsExactly("itemCreatedAt:desc", "name:asc", "_score");
  }

  @Test
  @DisplayName("with text query: _score first, requested fields as tie-breakers")
  void scoreBeforeFields() {
    QueryModel model = new QueryModel();
    model.setSortFields(sortFields());
    model.setRelevanceFirst(true);

    assertThat(describe(model.toSortOptions()))
        .containsExactly("_score", "itemCreatedAt:desc", "name:asc");
  }

  @Test
  @DisplayName("no sort fields: _score only, regardless of flag")
  void scoreOnly() {
    QueryModel model = new QueryModel();
    assertThat(describe(model.toSortOptions())).containsExactly("_score");
    model.setRelevanceFirst(true);
    assertThat(describe(model.toSortOptions())).containsExactly("_score");
  }

  @Test
  @DisplayName("relevanceFirst survives JSON round-trip (event-bus proxy)")
  void flagSurvivesSerialization() {
    QueryModel model = new QueryModel();
    model.setRelevanceFirst(true);
    QueryModel copy = new QueryModel(new JsonObject(model.toJson().encode()));
    assertThat(copy.isRelevanceFirst()).isTrue();
  }
}
