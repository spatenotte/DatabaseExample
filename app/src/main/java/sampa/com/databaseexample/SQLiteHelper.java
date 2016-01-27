package sampa.com.databaseexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    String createDB = "CREATE TABLE data (_id INTEGER PRIMARY KEY, name TEXT NOT NULL, age INTEGER NOT NULL)";
    SQLiteDatabase db;

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, 1);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addData(String name, int age) {
        db.execSQL("INSERT INTO data (name, age) VALUES('" + name + "', "+ age + " )");
        Log.d("Saved data", name);
    }

    public ArrayList<Data> getData() {
        Cursor c = db.rawQuery("SELECT * FROM data", null);
        ArrayList<Data> data = new ArrayList<Data>();
        c.moveToFirst();
        while(c.isAfterLast() == false) {
            data.add(new Data(c.getInt(0), c.getString(1), c.getInt(2)));
            c.moveToNext();
        }
        c.close();
        return data;
    }

    public void deleteRecord(int id) {
        db.execSQL("DELETE FROM data WHERE _id=" + id);
    }
}
