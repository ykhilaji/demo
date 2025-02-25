package com.coliship.demo.realTime.task.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component
public class Mock {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;
    private final String root = "classpath:mock/";

    // Injection par constructeur
    public Mock(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    /**
     * Charge un fichier JSON et le convertit en un objet du type spécifié.
     *
     * @param filename le nom du fichier à charger (situé dans "public/mock/")
     * @param clazz    la classe dans laquelle convertir le JSON
     * @param <T>      le type cible
     * @return un CompletableFuture contenant l'objet converti
     */
    public <T> CompletableFuture<T> loadJsonAs(String filename, Class<T> clazz) {
        return CompletableFuture.supplyAsync(() -> {
            String path = root + filename;
            Resource resource = resourceLoader.getResource(path);
            try (InputStream stream = resource.getInputStream()) {
                return objectMapper.readValue(stream, clazz);
            } catch (IOException e) {
                throw new RuntimeException("Impossible de charger le fichier " + path, e);
            }
        });
    }

    /**
     * Charge un fichier JSON et retourne le contenu sous forme de JsonNode.
     *
     * @param filename le nom du fichier à charger
     * @return un CompletableFuture contenant le JsonNode résultant
     */
    public CompletableFuture<JsonNode> loadJson(String filename) {
        return loadJsonAs(filename, JsonNode.class);
    }
}
