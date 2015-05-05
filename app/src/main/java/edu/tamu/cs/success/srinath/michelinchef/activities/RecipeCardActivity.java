package edu.tamu.cs.success.srinath.michelinchef.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import edu.tamu.cs.success.smit.michelinchef.DatabaseHelper;
import edu.tamu.cs.success.srinath.michelinchef.R;
import edu.tamu.cs.success.srinath.michelinchef.adapters.RecipeRecyclerViewAdapter;
import edu.tamu.cs.success.srinath.michelinchef.entities.RecipeStep;
import edu.tamu.cs.success.srinath.michelinchef.fragments.CheapEatsFragment;

/**
 * Created by srinath on 4/29/15.
 */

public class RecipeCardActivity extends Activity{
    private static final String TAG = "RecipeCardAct";
    private final long MILLIS_IN_SEC = 1000;
    public static final String TIMER_FORMAT = "%02d:%02d";

    // holds all steps of the current recipe
    private List<RecipeStep> aRecipe;

    // holds all timers for the current recipe - same size() as the list of RecipeSteps...
    List<RecipeCountDownTimer> timerList = new ArrayList<>();

    // controls the start of first timer
    private boolean bLaunchFirstTimer = true;

    // tracks the timer that is currently running - need this for pausing timers
    private int currentRunningTimer = 0;
    private long currentTimerMillisRemaining = 0;

    // Views...
    TextView recipeCountDownTimerTV;
    RecyclerView recyclerView;
    Button recipeStartPauseButton;
    String recipeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_card_recycler_view);

        recipeCountDownTimerTV = (TextView) findViewById(R.id.recipeStepCountDownTimer);
        recipeStartPauseButton = (Button) findViewById(R.id.recipeStartPauseButton);
        recyclerView = (RecyclerView) findViewById(R.id.recipeCardRecyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        /* get Recipe ID from Intent */
        Bundle extras = getIntent().getExtras();
        Log.e("DEBUG_MC", extras.getString(CheapEatsFragment.RECIPE_ID));
        recipeId = extras.getString(CheapEatsFragment.RECIPE_ID);

        /* Initialize the list of steps for the current recipe */
        initializeRecipeSteps();

        /* Initialize the list of timer objects for the current recipe */
        initializeRecipeStepTimers(aRecipe.size());

        /* Hand over display to RecyclerView and its adapter */
        RecipeRecyclerViewAdapter recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(aRecipe);
        recyclerView.setAdapter(recipeRecyclerViewAdapter);

        /* Set up the button to start/onPause */
        recipeStartPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recipeStartPauseButton.getText().toString().equals(
                        getResources().getString(R.string.start_button_text)
                )) {
                    // Start functionality - start the first timer...
                    if (bLaunchFirstTimer) {
                        timerList.get(0).start();
                        bLaunchFirstTimer = false;
                    }
                    recipeStartPauseButton.setText(getResources().getString(
                            R.string.pause_button_text));

                } else if (recipeStartPauseButton.getText().toString().equals(
                        getResources().getString(R.string.pause_button_text)
                )) {
                    // Pause functionality - store the remaining millis
                    currentTimerMillisRemaining = timerList.get(currentRunningTimer)
                                                    .millisRemainingToComplete;
                    timerList.get(currentRunningTimer).cancel();
                    recipeStartPauseButton.setText(getResources().getString(
                            R.string.resume_button_text));

                } else if (recipeStartPauseButton.getText().toString().equals(
                        getResources().getString(R.string.resume_button_text)
                )) {
                    // Resume functionality - replace the current timer with a new timer
                    // initialized to remaining millis from the Pause functionality above
                    RecipeCountDownTimer rcdt = new RecipeCountDownTimer
                            (currentRunningTimer, currentTimerMillisRemaining, MILLIS_IN_SEC);
                    timerList.set(currentRunningTimer, rcdt);
                    timerList.get(currentRunningTimer).start();
                    recipeStartPauseButton.setText(getResources().getString(
                            R.string.pause_button_text));

                }
            }
        });
    }

    /**
     * Get the recipe steps form the database
     */
    private void initializeRecipeSteps() {
        //Get Step text, step image in int and step time in milliseconds from database
        aRecipe = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List tempRecipeSteps = dbHelper.GetListOfSteps(recipeId);
        List tempRecipeImages = dbHelper.GetListOfStepImages(recipeId);
        List tempRecipeTimes = dbHelper.GetListOfStepTimes(recipeId);
       // textOfImages = new String[tempRecipeSteps.size()];
        String[] ImagesString = new String[tempRecipeImages.size()];
        int[] images = new int[tempRecipeImages.size()];
        for(int i=0;i<tempRecipeImages.size();i++) {
            ImagesString[i] = tempRecipeImages.get(i).toString();
            images[i]=getResources().getIdentifier(ImagesString[i], "drawable", getApplicationContext().getPackageName());
        }
        long[] time = new long[tempRecipeTimes.size()];
        for(int i = 0;i<tempRecipeTimes.size();i++)
        {
         time[i]=  Integer.parseInt(tempRecipeTimes.get(i).toString()) * 60000;
        }

        for(int i = 0;i< tempRecipeImages.size();i++)
            aRecipe.add(new RecipeStep(tempRecipeSteps.get(i).toString(), images[i], time[i]));
        /*aRecipe.add(new RecipeStep("This is the first step!", R.drawable.cuisine_american, 10000));
        aRecipe.add(new RecipeStep("Now, the dish is Indian!", R.drawable.cuisine_chinese, 7000));
        aRecipe.add(new RecipeStep("LOL! This time though..", R.drawable.cuisine_indian, 3000));*/
    }

    /**
     *  Initialize the list of timer objects for the current recipe
     * @param size
     */
    private void initializeRecipeStepTimers(int size) {
        for (int i = 0; i < size; i++) {
            Log.d(TAG, "Adding to timerList " + aRecipe.get(i).getHowLongToCookInMillis());
            timerList.add(new RecipeCountDownTimer(i, aRecipe.get(i).getHowLongToCookInMillis(),
                                                    MILLIS_IN_SEC));
        }
    }

    private class RecipeCountDownTimer extends CountDownTimer {
        /* Current object's counter ID */
        private int counterID;

        /* timer values to track for pause/resume functionality */
        private long millisRemainingToComplete;
        private long millisInTotal;

        private RecipeCountDownTimer(int counterID, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            Log.d(TAG, "Setting timer object counterID to " + counterID);
            this.counterID = counterID;
            this.millisInTotal = millisInFuture;
        }

        /**
         * Callback fired on regular interval.
         *
         * @param millisUntilFinished The amount of time until finished.
         */
        @Override
        public void onTick(long millisUntilFinished) {
            if (recipeCountDownTimerTV == null) {
                Log.e(TAG, "Error - null text view for timer - onTick()");
            }
            recipeCountDownTimerTV.setText("" +
                    String.format(TIMER_FORMAT,
                            (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) *
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),

                            (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) *
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
            );
            millisRemainingToComplete = millisUntilFinished;
        }

        /**
         * Callback fired when the time is up.
         */
        @Override
        public void onFinish() {
            Log.d(TAG, "Launching the next timer object with counterID = " + (this.counterID+1));
            startNextTimer(this.counterID + 1);
        }
    }

    private void startNextTimer(int nextStepIdx) {
        if (nextStepIdx >=0 && nextStepIdx < timerList.size()) {
            currentRunningTimer = nextStepIdx;
            timerList.get(nextStepIdx).start();
            recyclerView.scrollToPosition(nextStepIdx);
        }
        else {
            recipeCountDownTimerTV.setText("Recipe complete!");
            recipeStartPauseButton.setEnabled(false);
        }
    }

}
