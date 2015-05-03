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

import edu.tamu.cs.success.srinath.michelinchef.activities.MainActivity;
import edu.tamu.cs.success.srinath.michelinchef.R;
import edu.tamu.cs.success.srinath.michelinchef.adapters.RecipeGridAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link edu.tamu.cs.success.srinath.michelinchef.fragments.CuisinesFragment} factory method to
 * create an instance of this fragment.
 */
public class CuisinesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "CuisinesFragment";
    private String[] textOfImages = {
            "India",
            "Chinese",
            "American",
            "Thai",
            "Italian",
            "Mexicano"
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
    public static CuisinesFragment newInstance(int sectionNumber) {
        CuisinesFragment fragment = new CuisinesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CuisinesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cuisines, container, false);
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
