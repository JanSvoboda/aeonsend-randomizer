package com.example.honza.aeonsend;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.honza.aeonsend.cards.MarketSetupCard;
import com.example.honza.aeonsend.database.DatabaseHandler;
import com.example.honza.aeonsend.fragments.PlayersBottomSheetDialogFragment;
import com.example.honza.aeonsend.utils.Constants;
import com.example.honza.aeonsend.utils.OnDataPass;
import com.example.honza.aeonsend.utils.OnPlayersChange;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, OnDataPass, OnPlayersChange {

    // Setup for TabLayout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Bundle bundle = new Bundle();
    private TextView numPlayersTextMenu;
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
        findViewById(R.id.include_expansion_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem numPlayersMenuItem = menu.findItem(R.id.main_menu_action_num_players);

        FrameLayout rootItemMenuView = (FrameLayout) numPlayersMenuItem.getActionView();
        numPlayersTextMenu = rootItemMenuView.findViewById(R.id.menu_item_number_players_text);

        int numPlayers = bundle.getInt(Constants.EXTRASNUMPLAYERS);
        if (numPlayers == 0) {
            numPlayers = 4;
        }
        numPlayersTextMenu.setText(String.valueOf(numPlayers));

        rootItemMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(numPlayersMenuItem);
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

        if (id == R.id.main_menu_action_num_players) {
            mPlayersBottomSheetFragment.show(fm, "players_bottom_sheet_dialog_fragment");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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

}
