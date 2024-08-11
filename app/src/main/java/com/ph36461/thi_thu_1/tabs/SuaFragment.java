package com.ph36461.thi_thu_1.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ph36461.thi_thu_1.DbHelper;
import com.ph36461.thi_thu_1.R;
import com.ph36461.thi_thu_1.StudentAdapter;
import com.ph36461.thi_thu_1.list.Student;

import java.util.List;


public class SuaFragment extends Fragment {

    EditText edId, edName, edAge, edMssv;
    Button btnUpdate, btnCancel;
    DbHelper dbHelper;
    Student student;

    DanhSachFragment danhSachFragment = new DanhSachFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sua, container, false);



        edId = view.findViewById(R.id.ed_id);
        edName = view.findViewById(R.id.ed_name);
        edAge = view.findViewById(R.id.ed_age);
        edMssv = view.findViewById(R.id.ed_mssv);
        btnUpdate = view.findViewById(R.id.btn_update);
        btnCancel = view.findViewById(R.id.btn_cancel);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edId.setText("");
                edName.setText("");
                edAge.setText("");
                edMssv.setText("");
            }
        });


        dbHelper = new DbHelper(getContext());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edId.getText().toString();
                String name = edName.getText().toString();
                String age = edAge.getText().toString();
                String mssv = edMssv.getText().toString();

                student = new Student(Integer.parseInt(id), name, Integer.parseInt(age), mssv);
                dbHelper.updateStudent(student);

                edId.setText("");
                edName.setText("");
                edAge.setText("");
                edMssv.setText("");

            }
        });

        return view;
    }
}