package com.ph36461.thi_thu_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ph36461.thi_thu_1.list.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    List<Student> studentList;
    DbHelper dbHelper;
    Context context;

    public StudentAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        dbHelper = new DbHelper(context);
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        Student studentPos = studentList.get(position);
        // Bind student data to the views in the item layout
        holder.idTextView.setText("ID: " + studentPos.getId());
        holder.nameTextView.setText("Name: " + studentPos.getName());
        holder.ageTextView.setText("Age: " + studentPos.getAge());
        holder.mssvTextView.setText("MSSV: " + studentPos.getMssv());

        holder.deleteImageView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xác nhận xóa");
            builder.setIcon(R.drawable.ic_delete);
            builder.setMessage("Bạn có chắc chắn muốn xóa " + studentPos.getName() + " khỏi danh sách?");
            builder.setCancelable(false);

            builder.setPositiveButton("Xóa", (dialog, which) -> {
                // Delete the student
                dbHelper.deleteStudent(studentPos.getId());
                studentList.clear();
                studentList.addAll(dbHelper.getAllStudent());
                notifyDataSetChanged();
                dialog.dismiss();
            });
            builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, nameTextView, ageTextView, mssvTextView;
        ImageView deleteImageView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views in the item layout
            idTextView = itemView.findViewById(R.id.txt_id);
            nameTextView = itemView.findViewById(R.id.txt_name);
            ageTextView = itemView.findViewById(R.id.txt_age);
            mssvTextView = itemView.findViewById(R.id.txt_mssv);
            deleteImageView = itemView.findViewById(R.id.img_delete);
            // Bind data to the views
        }
    }
}
