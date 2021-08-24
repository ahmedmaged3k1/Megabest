package com.example.megabest.app.features.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.megabest.R;
import com.example.megabest.app.Adapters.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import static android.content.ContentValues.TAG;

public class HomeActivity extends AppCompatActivity {
    TabLayout tabManger;
    ViewPager2 viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sendUserInformation();
        setFragments();
    }

    private void sendUserInformation() {
        Bundle bundle = new Bundle();
        bundle.putString("Name", getIntent().getStringExtra("UserName"));
        bundle.putInt("Id", getIntent().getIntExtra("User ID", 0));
        Log.d(TAG, "onCreate: home activity  " + bundle.get("Name"));
        HomeFragment.newInstance(bundle);
        //homeFragment.setArguments(bundle);
    }

    private void setFragments() {
        tabManger = findViewById(R.id.tabController);

        KeyboardVisibilityEvent.setEventListener(
                HomeActivity.this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen) {
                            tabManger.setVisibility(View.GONE);
                        } else {
                            tabManger.setVisibility(View.VISIBLE);
                        }
                    }
                });
        viewPagerAdapter = findViewById(R.id.viewPager);
        viewPagerAdapter.setUserInputEnabled(false);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        viewPagerAdapter.setAdapter(fragmentAdapter);
        tabManger.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //  sendUserInformation();

                viewPagerAdapter.setCurrentItem(tab.getPosition());
                //sendUserInformation();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPagerAdapter.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //sendUserInformation();
                tabManger.selectTab(tabManger.getTabAt(position));
            }
        });
    }
}