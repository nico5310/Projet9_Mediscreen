package com.nico5310.assessmentMicroservice.constants;

import java.util.List;

public class Triggers {

    protected static final List<String> triggersList = List.of("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps");


    public static List<String> getTriggerList() {
        return triggersList;
    }

}
