package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DatabaseAdapter {

    DatabaseHelper helper;
    SQLiteDatabase db;
    Context context;

    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        this.context = context;
    }

    public SimpleCursorAdapter populateListViewFromDB(){
        String columns[] = {DatabaseHelper.KEY_ROWID, DatabaseHelper.KEY_DESCRIPTION};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,null, null,null, null, null, null);
        String[] fromFieldNotes = new String[]{
                DatabaseHelper.KEY_ROWID, DatabaseHelper.KEY_DESCRIPTION
        };
        int[] toViewIDs = new int[]{R.id.item_id, R.id.item_description};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,
                R.layout.single_item,
                cursor,
                fromFieldNotes,
                toViewIDs
        );
        return contactAdapter;
    }

    public boolean insertData(String note) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_DESCRIPTION, note);

        long result = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean updateData(int id, String note) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_ROWID, id);
        contentValues.put(DatabaseHelper.KEY_DESCRIPTION, note);

        long result = db.update(DatabaseHelper.TABLE_NAME, contentValues, "_id = ?", new String[]{String.valueOf(id)});
        return result != -1;
    }

    public int deleteDataNew(int selectItem) {
        int item = selectItem + 1;
        String whereArgs[] = {""+selectItem};
        int count = db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.KEY_ROWID + "=?", whereArgs);
        return count;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "mydb.db";
        private static final String TABLE_NAME = "note";
        private static final int DATABASE_VERSION = 2;
        private static final String KEY_ROWID = "_id";
        private static final String KEY_DESCRIPTION = "description";
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }
    }

