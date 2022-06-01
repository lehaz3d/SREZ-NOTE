package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {
    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        EditText editTextNote = findViewById(R.id.editNote);
        EditText editTextId = findViewById(R.id.editId);
        Button saveNote = findViewById(R.id.btnSave);
        db = new DatabaseAdapter(this);
        saveNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String noteValue = editTextNote.getText().toString();
                        String idValue = editTextId.getText().toString();
                        if (!noteValue.equals("") && !idValue.equals("") && db.updateData(Integer.parseInt(idValue),noteValue)) {
                            Toast.makeText(EditNote.this, "Заметка обновлена", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditNote.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EditNote.this, "Заметка не обновлена", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
}