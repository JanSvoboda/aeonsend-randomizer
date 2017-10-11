package com.games.boardgames.aeonsend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.games.boardgames.aeonsend.cards.MarketSetupCard;
import com.games.boardgames.aeonsend.enums.Expansion;
import com.games.boardgames.aeonsend.utils.Constants;
import com.games.boardgames.aeonsend.utils.GetIntentExtras;

import java.util.List;

/**
 * Created by honza on 22.9.17.
 */

public class GeneratedSetupActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, GetIntentExtras {

    // Setup for TabLayout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Intent intentExtras;
    private Bundle extrasBundle;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get passed Intent data
        intentExtras = getIntent();
        extrasBundle = intentExtras.getExtras();

        setContentView(R.layout.activity_generated_setup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_generatedsetup);
        setSupportActionBar(toolbar);

        // Add back button to actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_generatedsetup);

        tabLayout.addTab(tabLayout.newTab().setText("Nemesis"));
        tabLayout.addTab(tabLayout.newTab().setText("Characters"));
        tabLayout.addTab(tabLayout.newTab().setText("Market"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager_generatedsetup);

        //Creating our pager adapter
        GeneratedSetupPager adapter = new GeneratedSetupPager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        //Adding onPageChangeListener to select tab after swipe
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

//        // Floating action button that returns to Main Activity and finishes Generated Setup Activity
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }


    // Finish activity when back button is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
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
    public int getPlayers() {
        return extrasBundle.getInt(Constants.EXTRASNUMPLAYERS);
    }

    @Override
    public Expansion[] getExpansions() {
        List<Expansion> list = (List) extrasBundle.getSerializable(Constants.EXTRASSELECTEDEXPANSION);
        Expansion[] expansions = new Expansion[list.size()];
        list.toArray(expansions);
        return expansions;
    }

    @Override
    public MarketSetupCard getSetup() {
        return (MarketSetupCard) extrasBundle.getSerializable(Constants.EXTRASCHOSENSETUP);
    }

}
