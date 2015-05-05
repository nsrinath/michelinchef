package edu.tamu.cs.success.srinath.michelinchef.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import edu.tamu.cs.success.smit.michelinchef.DatabaseHelper;
import edu.tamu.cs.success.srinath.michelinchef.activities.MainActivity;
import edu.tamu.cs.success.srinath.michelinchef.R;
import edu.tamu.cs.success.srinath.michelinchef.activities.RecipeReview;
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
    public static final String RECIPE_ID = "recipe_id";



    private String[] textOfImages;
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
        DatabaseHelper dbHelper = new DatabaseHelper(this.getActivity());
        List tempRecipeNames = dbHelper.GetHealthyRecipesNames();
        List tempRecipeImages = dbHelper.GetHealthyRecipesImages();
        final List tempRecipeIds = dbHelper.GetHealthyRecipeIds();
        textOfImages = new String[tempRecipeNames.size()];
        String[] ImagesString = new String[tempRecipeImages.size()];
        images = new int[tempRecipeImages.size()];
        for(int i=0;i<tempRecipeImages.size();i++) {
            ImagesString[i] = tempRecipeImages.get(i).toString();
            images[i]=getResources().getIdentifier(ImagesString[i] , "drawable", getActivity().getApplicationContext().getPackageName());
        }
        for(int i=0;i<tempRecipeNames.size();i++) {
            textOfImages[i] = tempRecipeNames.get(i).toString();
        }
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
                Intent intent = new Intent(getActivity(), RecipeReview.class);
                intent.putExtra(RECIPE_ID, tempRecipeIds.get(position).toString());
                startActivity(intent);
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
