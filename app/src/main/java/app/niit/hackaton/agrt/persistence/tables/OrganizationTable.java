package app.niit.hackaton.agrt.persistence.tables;

import android.net.Uri;

import java.io.File;

import app.niit.hackaton.agrt.provider.AgtrProvider;


public class OrganizationTable {
    //OrganizationTable table database columns keys
    //The ID column must named only _id. Then only CursorAdapter will work
    private static final String ID = "_id";
    public static final String PARENT_ORG_ID  = "PARENT_ORG_ID";
    public static final String ORG_NAME  = "ORG_NAME";
    public static final String BRANCH = "BRANCH";
    public static final String ADDRESS = "ADDRESS";

    //OrganizationTable table and its content uri
    public static final String TABLE = "ORGANIZATION";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AgtrProvider.AUTHORITY + File.separator + TABLE);

    //OrganizationTable table creation
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PARENT_ORG_ID + " INTEGER NOT NULL,"
            + ORG_NAME + " TEXT NOT NULL,"
            + BRANCH + " TEXT NOT NULL,"
            + ADDRESS + " TEXT NOT NULL)";

    //OrganizationTable table projection
    public static final String[] PROJECTION = new String[]{
            ID,
            PARENT_ORG_ID,
            ORG_NAME,
            BRANCH,
            ADDRESS,
    };
}
