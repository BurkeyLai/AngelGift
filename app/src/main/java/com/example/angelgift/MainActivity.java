package com.example.angelgift;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_MESSAGE
            = "com.example.android.twoactivities.extra.MESSAGE";

    //private BottomNavigationView mBNV;
    private FirstFragment mFirstFragment;
    //private SecondFragment mSecondFragment;
    private ThirdFragment mThirdFragment;
    //public static final String DETAIL_MESSAGE = "DetailMessage";
    private Intent inputdataIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ((BottomNavigationView) findViewById(R.id.navigation)).setOnNavigationItemSelectedListener(this);
        ((BottomNavigationView) findViewById(R.id.navigation)).setSelectedItemId(R.id.navigation_first);

        inputdataIntent = new Intent(this, InputDataActivity.class);
        ((BottomNavigationView) findViewById(R.id.navigation)).getMenu().getItem(1).setIntent(inputdataIntent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.navigation_first:
                //getFragmentManager().popBackStackImmediate("FIRST", 0);
                if (mFirstFragment == null) mFirstFragment = new FirstFragment();
                //if (mSecondFragment != null) fragmentTransaction.hide(mSecondFragment);
                if (mThirdFragment != null) fragmentTransaction.hide(mThirdFragment);
                if (mFirstFragment.isAdded()) {
                    fragmentTransaction.show(mFirstFragment);
                } else {
                    fragmentTransaction.add(R.id.container_main, mFirstFragment, "FIRST");
                }
                fragmentTransaction.commit();

                return true;

            case R.id.navigation_second:
                return true;

            case R.id.navigation_third:
                if (mThirdFragment == null) mThirdFragment = new ThirdFragment();
                if (mFirstFragment != null) fragmentTransaction.hide(mFirstFragment)/*.addToBackStack("FIRST")*/;
                //if (mSecondFragment != null) fragmentTransaction.hide(mSecondFragment)/*.addToBackStack("SECOND")*/;
                //if (mFirstFragment != null) hideFragment(fragmentTransaction, mFirstFragment, "FIRST");
                //if (mSecondFragment != null) hideFragment(fragmentTransaction, mSecondFragment, "SECOND");
                if (mThirdFragment.isAdded()) {
                    fragmentTransaction.show(mThirdFragment);
                } else {
                    fragmentTransaction.add(R.id.container_main, mThirdFragment, "THIRD");
                }
                fragmentTransaction.commit();
                return true;
        }

        return false;
    }

    public void setSelectedDate(String s) {
        inputdataIntent.putExtra(EXTRA_MESSAGE, s);
    }
    /*
    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment F, String tag) {
        if (getFragmentManager().findFragmentByTag(tag) != null) {
            Log.i("MainActivity", tag + " is found!");
            fragmentTransaction.hide(F);
        } else {
            Log.i("MainActivity", tag + " is not found!");
            fragmentTransaction.hide(F).addToBackStack(tag);
        }
    }*/
    /*
    // Control the navigation button selection when BackPressed.
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed(){
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
            String currentFragmentTag = getFragmentManager().getFragments()
                    .get(getFragmentManager().getFragments().size() - 1).getTag();
            Log.i("MainActivity", "popping backstack: " + currentFragmentTag);
            if ("FIRST".equals(currentFragmentTag)) {
                //getFragmentManager().popBackStackImmediate("FIRST", 0);
                ((BottomNavigationView) findViewById(R.id.navigation)).setSelectedItemId(R.id.navigation_first);
            } else if ("SECOND".equals(currentFragmentTag)) {
                ((BottomNavigationView) findViewById(R.id.navigation)).setSelectedItemId(R.id.navigation_second);
            } else if ("THIRD".equals(currentFragmentTag)) {
                ((BottomNavigationView) findViewById(R.id.navigation)).setSelectedItemId(R.id.navigation_third);
            }
        } else {
            super.onBackPressed();
        }
    }
    */
}