package com.example.unininfuzijska_pumpa;

public enum ParametriEnum {
    TEZINA("tezina", "Težina"),
    VISINA("visina", "Visina");

    private final String name;
    private final String label;

    ParametriEnum(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label;
    }

}
