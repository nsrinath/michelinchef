package edu.tamu.cs.success.srinath.michelinchef.entities;

/**
 * Created by srinath on 4/29/15.
 * This is a class that represents a single step of the Recipe.
 * contains a String representing instructions for the step
 * and an image representing the outcome for the step
 */

public class RecipeStep {

    String whatToDo;
    int whatItWillLookLike;
    long howLongToCookInMillis;

    public long getHowLongToCookInMillis() {
        return howLongToCookInMillis;
    }

    public String getWhatToDo() {
        return whatToDo;
    }

    public int getWhatItWillLookLike() {
        return whatItWillLookLike;
    }

    public RecipeStep(String whatToDo, int whatItWillLookLike, long howLongToCookInMillis) {
        this.whatToDo = whatToDo;
        this.whatItWillLookLike = whatItWillLookLike;
        this.howLongToCookInMillis = howLongToCookInMillis;
    }

}
