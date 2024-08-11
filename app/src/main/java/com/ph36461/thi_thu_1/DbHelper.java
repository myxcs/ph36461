package com.ph36461.thi_thu_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ph36461.thi_thu_1.list.Student;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    Context context;

    public DbHelper(@Nullable Context context) {
        super(context, "student.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableStudent = "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, mssv TEXT)";
        sqLiteDatabase.execSQL(tableStudent);

        String addStudent = "INSERT INTO student VALUES " +
                "(1, 'Dang', 18, 'PH36461')," +
                "(2, 'Huy', 18, 'PH36462')," +
                "(3, 'Minh', 18, 'PH36463')," +
                "(4, 'Nam', 18, 'PH36464')," +
                "(5, 'Long', 18, 'PH36465')";

        sqLiteDatabase.execSQL(addStudent);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i < i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS student");
            onCreate(sqLiteDatabase);
            Toast.makeText(context, "Update thanh cong", Toast.LENGTH_SHORT).show();
        }
    }


    public void insertStudent(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("age", student.getAge());
        values.put("mssv", student.getMssv());

        long result = sqLiteDatabase.insert("student", null, values);
        if (result > 0) {
            Toast.makeText(context, "Them thanh cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Them that bai", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateStudent(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("age", student.getAge());
        values.put("mssv", student.getMssv());

        long result = sqLiteDatabase.update("student", values, "id=?", new String[]{String.valueOf(student.getId())});
        if (result > 0) {
            Toast.makeText(context, "Update thanh cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update that bai", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteStudent(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete("student", "id = ?", new String[]{String.valueOf(id)});
        if (result > 0) {
            Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
        }
    }



    public List<Student> getAllStudent() {

        List<Student> list = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT * FROM student";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor != null) {
            list = new ArrayList<>();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String mssv = cursor.getString(3);
                Student student = new Student(id, name, age, mssv);
                list.add(student);
            }
            cursor.close();
        }
        return list;
    }
}
