package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {
    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        EditText editTextNote = findViewById(R.id.editNote);
        Button saveNote = findViewById(R.id.btnSave);
        db = new DatabaseAdapter(this);
        saveNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String noteValue = editTextNote.getText().toString();
                        if (!noteValue.equals("") && db.insertData(noteValue)) {
                            Toast.makeText(AddNote.this, "Заметка добавлена", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddNote.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(AddNote.this, "Заметка не добавлена", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
}