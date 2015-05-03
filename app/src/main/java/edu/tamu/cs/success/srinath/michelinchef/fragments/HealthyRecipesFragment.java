package edu.tamu.cs.success.srinath.michelinchef.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.tamu.cs.success.srinath.michelinchef.activities.MainActivity;
import edu.tamu.cs.success.srinath.michelinchef.R;
import edu.tamu.cs.success.srinath.michelinchef.adapters.RecipeGridAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HealthyRecipesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthyRecipesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "HealthyRecipesFragment";
    public static final String DATABASE_NAME = "MichelinCook.db";

    SQLiteDatabase db = SQLiteDatabase.openDatabase(DATABASE_NAME, null, 1);
    //db.execSQL("SELECT c.name FROM Other_Category a, Recipe_Category b, Recipe_Master c WHERE a.name = 'Quick & Easy' AND a.other_id = b.other_id AND b.recipe_id = c.recipe_id");

    private String[] textOfImages = {
            "Indian",
            "Chinese",
            "Thai",
            "Italian",
            "US",
            "Mexican"
    };
    private int[] images = {
            R.drawable.cuisine_indian,
            R.drawable.cuisine_chinese,
            R.drawable.cuisine_thai,
            R.drawable.cuisine_italian,
            R.drawable.cuisine_american,
            R.drawable.cuisine_mexican
    };

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HealthyRecipesFragment newInstance(int sectionNumber) {
        HealthyRecipesFragment fragment = new HealthyRecipesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HealthyRecipesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_healthy_recipes, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.hrGridView);
        RecipeGridAdapter recipeGridAdapter = new RecipeGridAdapter(
                                                    getActivity().getApplicationContext(),
                                                    images, textOfImages);
        gridView.setAdapter(recipeGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "You clicked on item " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
