package edu.tamu.cs.success.srinath.michelinchef.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import edu.tamu.cs.success.srinath.michelinchef.activities.MainActivity;
import edu.tamu.cs.success.srinath.michelinchef.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopStoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopStoriesFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";
    private int widthFragment = 0;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TopStoriesFragment.
     */
    public static TopStoriesFragment newInstance(int sectionNumber) {
        TopStoriesFragment fragment = new TopStoriesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TopStoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_top_stories, container, false);
        LinearLayout customLLGrid = (LinearLayout) fragmentView.findViewById(R.id.imageGridView);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        widthFragment = displayMetrics.widthPixels;

        customLLGrid.addView(setUpSingleImage());
        customLLGrid.addView(setUpDoubleImage());

        return fragmentView;
    }

    /**
     * sets up a horizontal two image layout
     * @return View
     */
    private View setUpDoubleImage() {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View doubleImage = layoutInflater.inflate(R.layout.two_images_linearlayout, null);
        ImageView firstOfTwoImageViews = (ImageView) doubleImage.findViewById(R.id.firstOfTwoImages);
        ImageView secondOfTwoImageViews = (ImageView) doubleImage.findViewById(R.id.secondOfTwoImages);
        firstOfTwoImageViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubleImage.startAnimation(AnimationUtils.loadAnimation(
                        getActivity().getApplicationContext(), R.anim.image_press_animation
                ));
                Toast.makeText(getActivity().getApplicationContext(), "First of Two Images",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        secondOfTwoImageViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubleImage.startAnimation(AnimationUtils.loadAnimation(
                        getActivity().getApplicationContext(), R.anim.image_press_animation
                ));
                Toast.makeText(getActivity().getApplicationContext(), "Second of Two Images",
                        Toast.LENGTH_SHORT)
                        .show();

            }
        });

        return doubleImage;
    }

    /**
     * sets up a single image layout
     * @return View
     */
    private View setUpSingleImage() {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View singleImage = layoutInflater.inflate(R.layout.single_image_linearlayout, null);
        ImageView singleImageView = (ImageView) singleImage.findViewById(R.id.singleImage);
        singleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleImage.startAnimation(AnimationUtils.loadAnimation(
                        getActivity().getApplicationContext(), R.anim.image_press_animation
                ));
                Toast.makeText(getActivity().getApplicationContext(), "One of One Images",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
        return singleImage;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
