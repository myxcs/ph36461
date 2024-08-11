package com.ph36461.thi_thu_1.tabs;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ph36461.thi_thu_1.DbHelper;
import com.ph36461.thi_thu_1.R;
import com.ph36461.thi_thu_1.StudentAdapter;
import com.ph36461.thi_thu_1.list.Student;

import java.util.List;


public class DanhSachFragment extends Fragment {


    RecyclerView recyclerView;
    FloatingActionButton fab;
    DbHelper dbHelper;
    List<Student> studentList;
    StudentAdapter studentAdapter;

    EditText edName, edAge, edMssv;
    Button btnAdd, btnCancel, btnSync;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_sach, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        dbHelper = new DbHelper(getContext());

        studentList = dbHelper.getAllStudent();
        studentAdapter = new StudentAdapter(studentList, getContext());
        recyclerView.setAdapter(studentAdapter);

        btnSync = view.findViewById(R.id.btn_sync);


        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                studentList.clear();
                studentList.addAll(dbHelper.getAllStudent());
                studentAdapter.notifyDataSetChanged();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("students");
                studentList = new DbHelper(getContext()).getAllStudent();

                for (Student student : studentList) {
                    myRef.child(String.valueOf(student.getId())).setValue(student).addOnSuccessListener(unused -> {
                        Toast.makeText(getContext(), "Sync thanh cong", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Sync that bai", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });

        // Set up the FloatingActionButton

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.dialog_add, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


                edName = view.findViewById(R.id.ed_name);
                edAge = view.findViewById(R.id.ed_age);
                edMssv = view.findViewById(R.id.ed_mssv);
                btnAdd = view.findViewById(R.id.btn_add);
                btnCancel = view.findViewById(R.id.btn_cancel);


                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name = edName.getText().toString();
                        String age = edAge.getText().toString();
                        String mssv = edMssv.getText().toString();

                        if (name.isEmpty() || age.isEmpty() || mssv.isEmpty()) {
                            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!age.matches("\\d+")) {
                            Toast.makeText(getContext(), "Tuổi phải là số", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (Integer.parseInt(age) < 18) {
                            Toast.makeText(getContext(), "Tuổi phải lớn hơn 18", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Student student = new Student(name, Integer.parseInt(age), mssv);
                        dbHelper.insertStudent(student);
                        studentList.clear();
                        studentList.addAll(dbHelper.getAllStudent());
                        studentAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }

        });
        return view;
    }
}