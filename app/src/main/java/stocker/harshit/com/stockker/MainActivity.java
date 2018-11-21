package stocker.harshit.com.stockker;

import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.mauker.materialsearchview.MaterialSearchView;
import stocker.harshit.com.stockker.adapter.StockAdapter;
import stocker.harshit.com.stockker.fragments.AllStockFragment;
import stocker.harshit.com.stockker.fragments.SearchFragment;
import stocker.harshit.com.stockker.nseDataDownloader.StockInfo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,AllStockFragment.OnFragmentInteractionListener ,SearchFragment.OnFragmentInteractionListener {


    private StockAdapter adapter;
    private MaterialSearchView searchView;
    private ArrayList<StockInfo> StockInfoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment();
        //Adding SearchFragment related code
        searchView = findViewById(R.id.search_view);
        searchView.adjustTintAlpha(0.8f);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.equals(""))
                {
                    setFragment();
                }
                else
                setSearchFragment(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewOpened() {
                // Do something once the view is open.
            }

            @Override
            public void onSearchViewClosed() {
                // Do something once the view is closed.
            }
        });
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Do something when the suggestion list is clicked.
                String suggestion = searchView.getSuggestionAtPosition(position);

                searchView.setQuery(suggestion, false);
            }
        });
        searchView.setOnVoiceClickedListener(new MaterialSearchView.OnVoiceClickedListener() {
            @Override
            public void onVoiceClicked() {
                Toast.makeText(getApplicationContext(), "Voice clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        searchView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Long clicked position: " + i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        //Just adding toolbar and navigation bar for now without any specific use
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    private void setFragment()
    {
        Fragment allStockFragment = new AllStockFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, allStockFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setSearchFragment(String sss)
    {
        Fragment searchFrag = SearchFragment.newInstance(sss,null);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, searchFrag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (searchView.isOpen()) {
            // Close the search on the back button press.
            searchView.closeSearch();

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_search_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                // Open the search view on the menu item click.

                searchView.openSearch();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchView.activityResumed();
        String[] arr = new String[2];
        arr[0]="2";
        arr[1]="1";

        searchView.addSuggestions(arr);
    }

    private void clearHistory() {
        searchView.clearHistory();
    }

    private void clearSuggestions() {
        searchView.clearSuggestions();
    }

    private void clearAll() {
        searchView.clearAll();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Graph) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
