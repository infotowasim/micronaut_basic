package com.wasim;

import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class MarketControllerTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/markets")
    RxHttpClient client;

    @Test
    void returnsListOfMarkets() {
      final List result = client.toBlocking().retrieve("/", List.class);
      assertEquals(7,result.size());
      final List<LinkedHashMap<String, String>> markets=result;
      assertThat(markets)
              .extracting(entry -> entry.get("value"))
              .containsExactlyInAnyOrder("Apply","Amazon","Facebook","Google","Microsoft","Netflix","Tesla");

    }



}
