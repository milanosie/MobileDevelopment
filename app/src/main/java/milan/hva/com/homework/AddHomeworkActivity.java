package milan.hva.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddHomeworkActivity extends AppCompatActivity {

    private HomeworkDataSource mDataSource;

    private EditText mEditName;
    private EditText mEditSubject;
    private EditText mEditDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataSource = new HomeworkDataSource(this);

        mEditName = (EditText) findViewById(R.id.edit_title);
        mEditSubject = (EditText) findViewById(R.id.edit_subject);
        mEditDate = (EditText) findViewById(R.id.edit_subject);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Homework homework = new Homework();
                homework.setName(mEditName.getText().toString());
                homework.setSubject(mEditSubject.getText().toString());
                homework.setDate(mEditDate.getText().toString());
                mDataSource.addHomework(homework);
                Intent intent = new Intent(AddHomeworkActivity.this, HomeworkListActivity.class);

                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
