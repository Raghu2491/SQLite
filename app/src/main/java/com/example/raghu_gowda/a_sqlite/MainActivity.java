package com.example.raghu_gowda.a_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.name)
    EditText name;

    @Bind(R.id.surname)
    EditText surname;

    @Bind(R.id.marks)
    EditText marks;

    @Bind(R.id.add)
    Button add;
    @Bind(R.id.retrieve)
    Button retrieve;

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myDB = new DatabaseHelper(this);
    }

    @OnClick(R.id.add)
    public void ButtonClick(View view)
    {
        System.out.print("--------------> add button");
        String _name = name.getText().toString();
        String _surname = surname.getText().toString();
        String _marks = marks.getText().toString();
        if (view.getId() == R.id.add)
        {
            if( myDB.insertData(_name,_surname,_marks))
                Toast.makeText(MainActivity.this,"Values Added",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Error adding values",Toast.LENGTH_LONG).show();
        }


    }
}
