package edu.tamu.cs.success.srinath.michelinchef.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import edu.tamu.cs.success.srinath.michelinchef.R;
import edu.tamu.cs.success.srinath.michelinchef.activities.RecipeCardActivity;
import edu.tamu.cs.success.srinath.michelinchef.entities.RecipeStep;

/**
 * Created by srinath on 4/29/15.
 */
public class RecipeRecyclerViewAdapter extends
        RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder> {

    private static final String TAG = "Recipe-RVAdptr";
    List<RecipeStep> aRecipe;
    private int lastPosition = -1;

    public RecipeRecyclerViewAdapter(List<RecipeStep> aRecipe) {
        this.aRecipe = aRecipe;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.activity_recipe_card, viewGroup, false);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(view);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder recipeViewHolder, int i) {
        final long MILLIS_IN_SEC = 1000;
        final String TIMER_FORMAT = "%02d:%02d";
        recipeViewHolder.stepTimer.setText(
                timerValueAsString(aRecipe.get(i).getHowLongToCookInMillis()));
        recipeViewHolder.stepInstruction.setText(aRecipe.get(i).getWhatToDo());
        recipeViewHolder.stepOutput.setImageResource(aRecipe.get(i).getWhatItWillLookLike());

        // animate the entry....
        if (i >= lastPosition) {
            Animation animation;
            animation = AnimationUtils.loadAnimation(recipeViewHolder.cardView.getContext(),
                    android.R.anim.slide_in_left);
            recipeViewHolder.cardView.startAnimation(animation);
            lastPosition = i;
        }
    }

    private String timerValueAsString(long howLongToCookInMillis) {
        return String.format(RecipeCardActivity.TIMER_FORMAT,
                (TimeUnit.MILLISECONDS.toMinutes(howLongToCookInMillis) -
                        TimeUnit.MILLISECONDS.toMinutes(howLongToCookInMillis) *
                                TimeUnit.MILLISECONDS.toHours(howLongToCookInMillis)),

                (TimeUnit.MILLISECONDS.toSeconds(howLongToCookInMillis) -
                        TimeUnit.MILLISECONDS.toSeconds(howLongToCookInMillis) *
                                TimeUnit.MILLISECONDS.toMinutes(howLongToCookInMillis)));
    }

    @Override
    public int getItemCount() {
        return aRecipe.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public TextView getStepTimer() {
            return stepTimer;
        }

        public TextView stepTimer;
        public TextView stepInstruction;
        public ImageView stepOutput;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.recipeCardView);
            stepTimer = (TextView) itemView.findViewById(R.id.recipeStepTimer);
            stepInstruction = (TextView) itemView.findViewById(R.id.recipeCurrentStep);
            stepOutput = (ImageView) itemView.findViewById(R.id.recipeCurrentImage);
        }
    }

}
