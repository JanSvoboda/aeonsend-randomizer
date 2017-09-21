package com.example.honza.aeonsend;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.GridView;
import android.widget.ListView;

import com.example.honza.aeonsend.adapter.MarketGridViewAdapter;
import com.example.honza.aeonsend.adapter.MarketListViewAdapter;
import com.example.honza.aeonsend.database.MarketCardList;

public class MainActivity extends AppCompatActivity {

    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private MarketGridViewAdapter marketGridViewAdapter;
    private MarketListViewAdapter marketListViewAdapter;

    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stubGrid = (ViewStub) findViewById(R.id.stub_grid);
        stubList = (ViewStub) findViewById(R.id.stub_list);

        stubGrid.inflate();
        stubList.inflate();

        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        // Default view is ListView
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);

        gridView = (GridView) findViewById(R.id.marketGridView);
        listView = (ListView) findViewById(R.id.marketListView);

        switchView();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Find button for layout switching and set it to correct icon correlated to currentViewMode
        MenuItem switchLayoutButton = menu.findItem(R.id.action_switch_layout);

        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);

        switch (currentViewMode) {
            case VIEW_MODE_LISTVIEW:
                switchLayoutButton.setIcon(R.mipmap.ic_view_module_white_24dp);
                break;
            case VIEW_MODE_GRIDVIEW:
                switchLayoutButton.setIcon(R.mipmap.ic_view_list_white_24dp);
                break;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_switch_layout) {

            switch (currentViewMode) {
                case VIEW_MODE_LISTVIEW:
                    // Change icon of layout switching button to list icon
                    item.setIcon(R.mipmap.ic_view_list_white_24dp);
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                    break;
                case VIEW_MODE_GRIDVIEW:
                    // Change icon of layout switching button to grid icon
                    item.setIcon(R.mipmap.ic_view_module_white_24dp);
                    currentViewMode = VIEW_MODE_LISTVIEW;
                    break;
            }
            switchView();

            SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("currentViewMode", currentViewMode);
            editor.apply();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchView() {
        switch (currentViewMode) {
            case VIEW_MODE_LISTVIEW:
                // Hide GridView
                stubGrid.setVisibility(View.GONE);
                // Display ListView
                stubList.setVisibility(View.VISIBLE);
                break;

            case VIEW_MODE_GRIDVIEW:
                // Display GridView
                stubGrid.setVisibility(View.VISIBLE);
                // Hide ListView
                stubList.setVisibility(View.GONE);
                break;
        }
        setAdapter();

    }

    private void setAdapter() {
        switch (currentViewMode) {
            case VIEW_MODE_LISTVIEW:
                marketListViewAdapter = new MarketListViewAdapter(this, MarketCardList.getMarketCards());
                listView.setAdapter(marketListViewAdapter);
                break;
            case VIEW_MODE_GRIDVIEW:
                marketGridViewAdapter = new MarketGridViewAdapter(this, MarketCardList.getMarketCards());
                gridView.setAdapter(marketGridViewAdapter);
                break;
        }
    }
}
