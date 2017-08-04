package com.example.codetribe.studentreportcard;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {


    TextView identity,learnerId;
    EditText nameBox, Marks,subjectBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //casting views  by id
        identity = (TextView) findViewById(R.id.display);
        nameBox = (EditText) findViewById(R.id.nameB);
        subjectBox =(EditText)findViewById(R.id.subject);
        Marks = (EditText) findViewById(R.id.marks);
        learnerId =(TextView)findViewById(R.id.id);

    }

    //add new learner into database
    public void newLearner(View view) {
        DatabaseHandler dHandler = new DatabaseHandler(this);
        int id = Integer.parseInt(Marks.getText().toString());
        Student student = new Student(nameBox.getText().toString(), id);
        dHandler.addLearner(student);
        subjectBox.setText("");
        nameBox.setText("");
        Marks.setText("");
        learnerId.setText("");
        Toast.makeText(this,"Learner added succefully ",Toast.LENGTH_SHORT).show();

    }
    //searching for student that are stored in database

    public void searchLearner(View view) {
        DatabaseHandler dHandler = new DatabaseHandler(this);
        Student student = dHandler.searchLearner(nameBox.getText().toString());

        if (student != null) {
            identity.setText(String.valueOf(student.getFullName()));
            nameBox.setText(String.valueOf(student.getFullName()));
            Marks.setText(String.valueOf(student.getSubject()));
            subjectBox.setText(String.valueOf(student.getMarks()));
            learnerId.setText(String.valueOf(student.getId()));

        } else
            {
            identity.setText("No Match found");
            identity.setBackgroundColor(Color.RED);
        }

    }
    //delete learner in database
    public void deleteLearner(View view) {
        DatabaseHandler dHandler = new DatabaseHandler(this);
        boolean result = dHandler.deleteLearner(nameBox.getText().toString());

        if (result) {
            identity.setText("Record Deleted");
            subjectBox.setText("");
            Marks.setText("");
            nameBox.setText("");
            learnerId.setText("");
        } else {
            identity.setText("No Match Found");
        }
    }
    public void updateLearner(View view){
        DatabaseHandler dHandler = new DatabaseHandler(this);
        int id = Integer.parseInt(Marks.getText().toString());
        Student student = new Student(nameBox.getText().toString(), id);
        dHandler.updateLearner(student);
        subjectBox.setText("");
        identity.setText("");
        Marks.setText("");
        learnerId.setText("");
        Toast.makeText(this,"Update was sucessful",Toast.LENGTH_SHORT).show();
    }
    }

