package com.knights.vita;

/**
 * Created by josephvarghese on 18/03/18.
 */

public class TraitsClass {
    String reccomendation;
    String food;
    String trait;


    public TraitsClass(String reccomendation, String food, String trait) {
        this.reccomendation = reccomendation;
        this.food = food;
        this.trait = trait;
    }

    public TraitsClass() {
    }


    public String getReccomendation() {
        return reccomendation;
    }

    public void setReccomendation(String reccomendation) {
        this.reccomendation = reccomendation;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }
}
