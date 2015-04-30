package edu.tamu.cs.success.srinath.michelinchef;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.tamu.cs.success.srinath.michelinchef.adapters.RecipeRecyclerViewAdapter;
import edu.tamu.cs.success.srinath.michelinchef.entities.RecipeStep;

/**
 * Created by srinath on 4/29/15.
 */
public class RecipeCardActivity extends Activity{

    private List<RecipeStep> aRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_card_recycler_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recipeCardRecyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        initializeRecipeSteps();

        RecipeRecyclerViewAdapter recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(aRecipe);
        recyclerView.setAdapter(recipeRecyclerViewAdapter);
    }

    private void initializeRecipeSteps() {
        aRecipe = new ArrayList<>();
        aRecipe.add(new RecipeStep("This is the first step!", R.drawable.cuisine_american, 270000));
        aRecipe.add(new RecipeStep("Now, the dish is Indian!", R.drawable.cuisine_chinese, 300000));
        aRecipe.add(new RecipeStep("LOL! This time though..", R.drawable.cuisine_indian, 330000));

    }
}
