package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseAdapter dataBaseAdapter;
    ListView listView;
    Button editNote;
    public SimpleCursorAdapter simpleCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreCreateDB.copyDB(this);
        dataBaseAdapter = new DatabaseAdapter(this);
        listView = findViewById(R.id.listViewItewNote);
        editNote = findViewById(R.id.btnEditNote);
        simpleCursorAdapter = dataBaseAdapter.populateListViewFromDB();
        listView.setAdapter(simpleCursorAdapter);
        Button addNote = findViewById(R.id.btnAddNote);
        addNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, AddNote.class);
                        startActivity(intent);
                    }
                }
        );
        editNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, EditNote.class);
                        startActivity(intent);
                    }
                }
        );
    }
    public void deleteNote(View view) {
        String idValue = ((Button) view).getText().toString();
        Toast.makeText(MainActivity.this, "Заметка успешно удалена", Toast.LENGTH_LONG).show();
        dataBaseAdapter.deleteDataNew(Integer.parseInt(idValue));
        simpleCursorAdapter = dataBaseAdapter.populateListViewFromDB();
        listView.setAdapter(simpleCursorAdapter);
    }
}