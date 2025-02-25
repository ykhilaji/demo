package com.coliship.demo.realTime.Task.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.coliship.demo.realTime.task.utils.Mock;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MockTest {

    @Autowired
    private Mock mock;

    @Test
    public void testLoadJson() throws Exception {
        // Appel de la méthode loadJson qui renvoie un CompletableFuture<JsonNode>
        CompletableFuture<JsonNode> future = mock.loadJson("tasks.json");
        // On attend le résultat (timeout de 5 secondes)
        JsonNode json = future.get(5, TimeUnit.SECONDS);

        // Vérifie que le résultat n'est pas null
        assertNotNull(json);
        // Exemple de vérification : on teste qu'un champ attendu existe dans le JSON
        assertThat(json.has("user_id")).isTrue();
    }
}
