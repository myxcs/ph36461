package com.ph36461.thi_thu_1.tabs;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ph36461.thi_thu_1.DbHelper;
import com.ph36461.thi_thu_1.StudentAdapter;
import com.ph36461.thi_thu_1.list.Student;

import java.util.List;

public class HomePagerAdapter extends FragmentStateAdapter {

    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DanhSachFragment();
            case 1:
                return new SuaFragment();
            default:
                return new DanhSachFragment();
        }
    }
}



