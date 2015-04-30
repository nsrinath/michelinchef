package edu.tamu.cs.success.srinath.michelinchef.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.tamu.cs.success.srinath.michelinchef.R;
import edu.tamu.cs.success.srinath.michelinchef.entities.RecipeStep;

/**
 * Created by srinath on 4/29/15.
 */
public class RecipeRecyclerViewAdapter extends
        RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder> {

    List<RecipeStep> aRecipe;

    public RecipeRecyclerViewAdapter(List<RecipeStep> aRecipe) {
        this.aRecipe = aRecipe;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recipe_card, viewGroup, false);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(view);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder recipeViewHolder, int i) {
        recipeViewHolder.stepTimer.setText(
                timerValueAsString(aRecipe.get(i).getHowLongToCookInMillis()) + " minutes");
        recipeViewHolder.stepInstruction.setText(aRecipe.get(i).getWhatToDo());
        recipeViewHolder.stepOutput.setImageResource(aRecipe.get(i).getWhatItWillLookLike());
    }

    private String timerValueAsString(long howLongToCookInMillis) {
        return String.valueOf (howLongToCookInMillis/(1000*60));
    }

    @Override
    public int getItemCount() {
        return aRecipe.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView stepTimer;
        private TextView stepInstruction;
        private ImageView stepOutput;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.recipeCardView);
            stepTimer = (TextView) itemView.findViewById(R.id.recipeStepTimer);
            stepInstruction = (TextView) itemView.findViewById(R.id.recipeCurrentStep);
            stepOutput = (ImageView) itemView.findViewById(R.id.recipeCurrentImage);
        }

    }
}
