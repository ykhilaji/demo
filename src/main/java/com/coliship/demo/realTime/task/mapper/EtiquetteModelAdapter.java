package com.coliship.demo.realTime.task.mapper;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtiquetteModelAdapter {
    private AddressModel expediteur;
    private AddressModel destinataire;
    private Double prix;
    private Double prixRetour;
    private Double prixOptions;
    private Produit produit;
    private PointRetrait pointRetrait;
    private CaracteristiquesModel caracteristiques;
    private MontantDesOptions montantDesOptions;
    // Pour les options et produits disponibles, on peut utiliser JsonNode ou Object selon votre besoin.
    private List<Object> availableOptions;
    private List<Object> availableProducts;
    private Boolean retour;
    @Builder.Default
    private Boolean collecte = false;
    private Boolean deleted;
    private String numeroCommande;
    private List<Field> customFields;
    private List<Field> field;
    private String colisId;
    private AddressModel crbt;
    private AddressModel cn23;
    private String partenaireCab;
    private String partenaireNom;
    private Integer numeroDepot;
    private Integer lineNumer;
    @Builder.Default
    private Boolean pluginActive = true;
    @Builder.Default
    private Boolean isReturnByLot = false;
    @Builder.Default
    private Boolean command = false;
    private Long cmdId;
}

