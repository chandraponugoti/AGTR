package app.niit.hackaton.agrt.persistence.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;

import app.niit.hackaton.agrt.dto.Asset;
import app.niit.hackaton.agrt.dto.Organisation;
import app.niit.hackaton.agrt.persistence.AgtrDbHelper;
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

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE;

    //OrganizationTable table projection
    public static final String[] PROJECTION = new String[]{
            ID,
            PARENT_ORG_ID,
            ORG_NAME,
            BRANCH,
            ADDRESS,
    };

    public static ContentValues createValuesFromObject(final Organisation v) {
        final ContentValues cv = new ContentValues();
        cv.put(PARENT_ORG_ID, v.getParentId());
        cv.put(ORG_NAME, v.getOrganisationName());
        cv.put(BRANCH, v.getBranch());
        cv.put(ADDRESS, v.getAddress());
        return cv;
    }

    public static Organisation createObjectFromCursor(final Cursor cursor) {
        Organisation org = new Organisation();
        final int id = AgtrDbHelper.getInt(cursor, ID);
        final String name = AgtrDbHelper.getString(cursor, ORG_NAME, "");
        final String branch = AgtrDbHelper.getString(cursor, BRANCH, "");
        final String address = AgtrDbHelper.getString(cursor, ADDRESS, "");
        final int parent = AgtrDbHelper.getInt(cursor, PARENT_ORG_ID);
        org.setOrganisationName(name);
        org.setBranch(branch);
        org.setAddress(address);
        org.setParentId(parent);
        org.setId(id);
        return org;
    }
}
