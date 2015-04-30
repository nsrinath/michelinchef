package edu.tamu.cs.success.smit.michelinchef;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smit on 4/28/2015.
 */
public class Recipe {

    private int recipe_id;
    private String recipe_title;
    private List<String> steps = new ArrayList<String>();
    private List<String> imagePaths = new ArrayList<String>();

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_title() {
        return recipe_title;
    }

    public void setRecipe_title(String recipe_title) {
        this.recipe_title = recipe_title;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
