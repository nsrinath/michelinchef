package edu.tamu.cs.success.srinath.michelinchef.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import edu.tamu.cs.success.smit.michelinchef.DatabaseHelper;
import edu.tamu.cs.success.srinath.michelinchef.R;
import edu.tamu.cs.success.srinath.michelinchef.fragments.CheapEatsFragment;

public class RecipeReview extends Activity {
    TableLayout table_layout;
    String recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_review);

        Bundle extras = getIntent().getExtras();
        Log.e("DEBUG_MC", extras.getString(CheapEatsFragment.RECIPE_ID));
        recipeId = extras.getString(CheapEatsFragment.RECIPE_ID);

        // look up the db for recipeID and display ingredients and steps...
//        sqlcon = new SQLController(this);

        table_layout = (TableLayout) findViewById(R.id.tableLayout1);

        BuildTable();
        Button cookButton;
        cookButton = (Button) findViewById(R.id.button);
        cookButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent intent = new Intent(RecipeReview.this, RecipeCardActivity.class);
                intent.putExtra(CheapEatsFragment.RECIPE_ID, recipeId);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void BuildTable() {

        //sqlcon.open();
        //Cursor c = sqlcon.readEntry();

//        int rows = c.getCount();
  //      int cols = c.getColumnCount();

        int cols = 2;

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List tempIngredientNames = dbHelper.GetIngredientNames(recipeId);
        List tempIngredientQuantities = dbHelper.GetIngredientQuantities(recipeId);
        String recipeImage = "R.drawable."+dbHelper.GetIngredientImage(recipeId);
        int rows = (tempIngredientNames.size() < tempIngredientQuantities.size()) ? tempIngredientNames.size() : tempIngredientQuantities.size();

        ImageView imageView = (ImageView) findViewById(R.id.imageButton);
        imageView.setImageResource(getResources().getIdentifier( recipeImage, "drawable", getApplicationContext().getPackageName()));
        // outer for loop
        for (int i = 0; i < rows; i++) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

            // inner for loop
            for (int j = 0; j < cols; j++) {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                tv.setBackgroundResource(R.drawable.ab_transparent_orangetheme);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(12);
    //            tv.setPadding(0, 5, 0, 5);
                if(j==0)//Ingredient name
                    tv.setText(tempIngredientNames.get(i).toString());
                else//Ingredient Quantities
                    tv.setText(tempIngredientQuantities.get(i).toString());

                row.addView(tv);

            }

            //c.moveToNext();

            table_layout.addView(row);

        }
        //sqlcon.close();
    }

}
