package org.boes.praktikum.gameclient;

public class Card {
    int value ;
    String name;

    public Card(String name, int value ) {
        this.value = value;
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
