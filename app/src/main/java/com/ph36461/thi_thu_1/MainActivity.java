package com.ph36461.thi_thu_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ph36461.thi_thu_1.list.Student;
import com.ph36461.thi_thu_1.tabs.HomePagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
     ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager2);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(this);
        viewPager2.setAdapter(homePagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Danh sách");
                    break;
                case 1:
                    tab.setText("Sửa");
                    break;
            }
        }).attach();

    }


}