package edu.tamu.cs.success.srinath.michelinchef.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
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
 * Use the {@link edu.tamu.cs.success.srinath.michelinchef.fragments.CheapEatsFragment} factory method to
 * create an instance of this fragment.
 */
public class CheapEatsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "CheapEatsFragment";
    public static final String RECIPE_ID = "recipe_id";
    private String[] textOfImages;
    private int[] images;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CheapEatsFragment newInstance(int sectionNumber) {
        CheapEatsFragment fragment = new CheapEatsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CheapEatsFragment() {
    }

   public void search()
   {

   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatabaseHelper dbHelper = new DatabaseHelper(this.getActivity());
        String temp ="cheese";
        dbHelper.GetSearchRecipeNames(temp);

        List tempRecipeNames = dbHelper.GetCheapEatsRecipeNames();
        List tempRecipeImages = dbHelper.GetCheapEatsRecipeImages();
        final List tempRecipeIds = dbHelper.GetCheapEatsRecipeIds();
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
        Log.d("DEBUG-MC", "Inflating rootView");
        View rootView = inflater.inflate(R.layout.fragment_cheap_eats, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.hrGridView);
        RecipeGridAdapter recipeGridAdapter = new RecipeGridAdapter(
                getActivity().getApplicationContext(),
                images, textOfImages);
        gridView.setAdapter(recipeGridAdapter);
        Log.d("DEBUG-MC", "Setting grid view adapter");
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("DEBUG-MC", "Launchin Toast");
                Toast.makeText(getActivity().getApplicationContext(),
                        "You clicked on item howdy" + tempRecipeIds.get(position),
                        Toast.LENGTH_SHORT).show();
//                Log.d("DEBUG-MC", "Launchin Intent");
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
