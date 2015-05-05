package edu.tamu.cs.success.srinath.michelinchef.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.tamu.cs.success.smit.michelinchef.DatabaseHelper;
import edu.tamu.cs.success.srinath.michelinchef.R;
import edu.tamu.cs.success.srinath.michelinchef.fragments.CheapEatsFragment;
import edu.tamu.cs.success.srinath.michelinchef.fragments.CuisinesFragment;
import edu.tamu.cs.success.srinath.michelinchef.fragments.HealthyRecipesFragment;
import edu.tamu.cs.success.srinath.michelinchef.fragments.NavigationDrawerFragment;
import edu.tamu.cs.success.srinath.michelinchef.fragments.PlaceholderFragment;
import edu.tamu.cs.success.srinath.michelinchef.fragments.QuickEasyFragment;
import edu.tamu.cs.success.srinath.michelinchef.fragments.SearchableFragment;
import edu.tamu.cs.success.srinath.michelinchef.fragments.TopStoriesFragment;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final String TAG = "MichelinChef-MainAct";

    DatabaseHelper helper;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mTmpCardLauncher = (Button) findViewById(R.id.tempCardLauncher);
        mTmpCardLauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchRecipeCardActivity = new Intent(MainActivity.this, RecipeCardActivity.class);
                startActivity(launchRecipeCardActivity);
            }
        });

        helper = new DatabaseHelper(this);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, getCorrespondingFragment(position+1)) //PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    /**
     * Takes the sectionNumber (position on the list) as input and
     * generates the corresponding Fragment before returning it for display
     * @param sectionNumber
     * @return Fragment containing the required UI
     */
    private Fragment getCorrespondingFragment(int sectionNumber) {
        switch (sectionNumber) {
            case 1: Log.d(TAG, "Launching top stories...");
                return TopStoriesFragment.newInstance(sectionNumber);
            case 2: Log.d(TAG, "Launching Cheap eats fragment...");
                return CheapEatsFragment.newInstance(sectionNumber);
            case 3: Log.d(TAG, "Launching healthy recipes fragment...");
                return HealthyRecipesFragment.newInstance(sectionNumber);
            case 4: Log.d(TAG, "Launching Quick & Easy fragment");
                return QuickEasyFragment.newInstance(sectionNumber);
            case 5: Log.d(TAG, "Launching Cuisines fragment");
                return CuisinesFragment.newInstance(sectionNumber);
            default:Log.d(TAG, "Section Number == None?");
                break;
        }
        return PlaceholderFragment.newInstance(sectionNumber);
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_home);
                break;
            case 2:
                mTitle = getString(R.string.title_cheap_eats);
                break;
            case 3:
                mTitle = getString(R.string.title_healthy_recipes);
                break;
            case 4:
                mTitle = getString(R.string.title_quick_easy);
                break;
            case 5:
                mTitle = getString(R.string.title_cuisines);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

}
