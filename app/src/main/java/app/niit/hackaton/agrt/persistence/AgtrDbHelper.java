package app.niit.hackaton.agrt.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.niit.hackaton.agrt.persistence.tables.AppUserTable;
import app.niit.hackaton.agrt.persistence.tables.OrganizationTable;


public class AgtrDbHelper extends SQLiteOpenHelper {
    //Application Database name & version
    private static final String DB_NAME = "AGTR";
    private static final int DB_VERSION = 1;

    public AgtrDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Added encode for special characters like umlaut etc
        db.execSQL(OrganizationTable.CREATE_TABLE);
        db.execSQL(AppUserTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
