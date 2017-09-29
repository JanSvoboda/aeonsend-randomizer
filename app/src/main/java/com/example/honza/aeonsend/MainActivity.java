package com.example.honza.aeonsend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.honza.aeonsend.adapter.MarketGridViewAdapter;
import com.example.honza.aeonsend.adapter.MarketListViewAdapter;
import com.example.honza.aeonsend.cards.Card;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.database.MarketSetupCardList;
import com.example.honza.aeonsend.enums.CardType;

public class MainActivity extends AppCompatActivity  {

    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private MarketGridViewAdapter marketGridViewAdapter;
    private MarketListViewAdapter marketListViewAdapter;

    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    // Setup for TabLayout
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stubGrid = (ViewStub) findViewById(R.id.setup_fragment_stub_grid);
        stubList = (ViewStub) findViewById(R.id.setup_fragment_stub_list);

        stubGrid.inflate();
        stubList.inflate();

        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        // Default view is ListView
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);

        gridView = (GridView) findViewById(R.id.marketGridView);
        listView = (ListView) findViewById(R.id.marketListView);

        switchView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), GeneratedSetupActivity.class);
                startActivity(intent);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), GeneratedSetupActivity.class);
                startActivity(intent);
            }
        });

        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(this);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Card card = databaseHandler.getCard(db, 1, CardType.SPELL);
        databaseHandler.close();
//        databaseHandler.addCard(new CharacterCard(1,"blabla", CardType.CHARACTER, "blabla", Expansion.BASIC), CardType.CHARACTER.getValue());


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
                marketListViewAdapter = new MarketListViewAdapter(this, MarketSetupCardList.getMarketSetupCards());
                listView.setAdapter(marketListViewAdapter);
                break;
            case VIEW_MODE_GRIDVIEW:
                marketGridViewAdapter = new MarketGridViewAdapter(this, MarketSetupCardList.getMarketSetupCards());
                gridView.setAdapter(marketGridViewAdapter);
                break;
        }
    }
}
