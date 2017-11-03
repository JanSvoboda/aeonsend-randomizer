package com.games.boardgames.aeonsend;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.games.boardgames.aeonsend.cards.MarketSetupCard;
import com.games.boardgames.aeonsend.database.DatabaseHandler;
import com.games.boardgames.aeonsend.enums.Expansion;
import com.games.boardgames.aeonsend.fragments.PlayersBottomSheetDialogFragment;
import com.games.boardgames.aeonsend.utils.Constants;
import com.games.boardgames.aeonsend.listeners.OnDataPass;
import com.games.boardgames.aeonsend.listeners.OnExpansionsChange;
import com.games.boardgames.aeonsend.listeners.OnPlayersChange;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, OnDataPass, OnPlayersChange, OnExpansionsChange {

    // Setup for TabLayout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Bundle bundle = new Bundle();
    private TextView numPlayersTextMenu;
    private TextView expansionsTextMenu;
    private FragmentManager fm = getSupportFragmentManager();
    private BottomSheetBehavior mBottomSheetBehavior;
    private PlayersBottomSheetDialogFragment mPlayersBottomSheetFragment = new PlayersBottomSheetDialogFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        DatabaseHandler mHandler = DatabaseHandler.getInstance(this);
        SQLiteDatabase mDatabase = mHandler.getWritableDatabase();

        mDatabase.close();
        mHandler.close();

        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.include_expansion_fragment));
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        findViewById(R.id.include_expansion_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem numPlayersMenuItem = menu.findItem(R.id.main_menu_action_num_players);
        final MenuItem expansionsMenuItem = menu.findItem(R.id.main_menu_action_expansions);

        FrameLayout playersItemMenuView = (FrameLayout) numPlayersMenuItem.getActionView();
        FrameLayout expansionsItemMenuView = (FrameLayout) expansionsMenuItem.getActionView();

        numPlayersTextMenu = playersItemMenuView.findViewById(R.id.menu_item_number_players_text);
        expansionsTextMenu = expansionsItemMenuView.findViewById(R.id.menu_item_expansions_text);

        int numPlayers = bundle.getInt(Constants.EXTRASNUMPLAYERS);
        if (numPlayers == 0) {
            numPlayers = 4;
            onDataPass(Constants.EXTRASNUMPLAYERS, numPlayers);
        }
        numPlayersTextMenu.setText(String.valueOf(numPlayers));

        List<Expansion> listExpansions = (List) bundle.getSerializable(Constants.EXTRASSELECTEDEXPANSION);
        int numExpansions;
        try {
            // Get number of expansions. Basic is always included in list, therefore size of list should always be at least 1
            numExpansions = listExpansions.size() - 1;
        } catch (NullPointerException exception) {
            numExpansions = 0;
        }
        expansionsTextMenu.setText(String.valueOf(numExpansions));

        playersItemMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(numPlayersMenuItem);
            }
        });

        expansionsItemMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(expansionsMenuItem);
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem switchLayout = menu.findItem(R.id.main_menu_action_switch_layout);
        switchLayout.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.main_menu_action_num_players:
                mPlayersBottomSheetFragment.show(fm, "players_bottom_sheet_dialog_fragment");
                return true;
            case R.id.main_menu_action_expansions:
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
//                mExpansionBottomSheetDialogFragment.show(fm, "expansion_bottom_sheet_dialog_fragment");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("fragmentValuesBundle", getFragmentValuesBundle());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bundle = savedInstanceState.getBundle("fragmentValuesBundle");
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onDataPass(String name, List list) {
        bundle.putSerializable(name, (Serializable) list);
    }

    @Override
    public void onDataPass(String name, Integer i) {
        bundle.putInt(name, i);
    }

    @Override
    public void onDataPass(String name, MarketSetupCard card) {
        bundle.putSerializable(name, (Serializable) card);
    }

    @Override
    public Bundle getFragmentValuesBundle() {
        return bundle;
    }

    @Override
    public void onPlayersChange(int number) {
        numPlayersTextMenu.setText(String.valueOf(number));
    }

    @Override
    public void onExpansionsChange(int number) {
        expansionsTextMenu.setText(String.valueOf(number));
    }

}
