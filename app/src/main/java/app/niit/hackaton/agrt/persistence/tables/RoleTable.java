package app.niit.hackaton.agrt.persistence.tables;

/**
 * Created by ChandraSekharPonugot on 27-09-2016.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;

import app.niit.hackaton.agrt.dto.Role;
import app.niit.hackaton.agrt.persistence.AgtrDbHelper;
import app.niit.hackaton.agrt.provider.AgtrProvider;
import app.niit.hackaton.agrt.util.Util;


public class RoleTable {
    public static final String ROLE_NAME   = "ROLE_NAME ";
    public static final String ORG_ID   = "ORG_ID";
    //RoleTable table and its content uri
    public static final String TABLE = "ROLE";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AgtrProvider.AUTHORITY + File.separator + TABLE);
    //RoleTable table database columns keys
    //The ID column must named only _id. Then only CursorAdapter will work
    private static final String ID = "_id";
    //RoleTable table creation
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ROLE_NAME  + " TEXT,"
            + ORG_ID  + " INTEGER )";

    //RoleTable table projection
    public static final String[] PROJECTION = new String[]{
            ID,
            ROLE_NAME ,
            ORG_ID
    };

    public static ContentValues createValuesFromObject(final Role v) {
        final ContentValues cv = new ContentValues();
        cv.put(ROLE_NAME, v.getRoleName());
        cv.put(ORG_ID,  v.getOrg().getId());
        return cv;
    }

    public static Role createObjectFromCursor(final Cursor cursor) {
        Role role = new Role();
        final Long id = AgtrDbHelper.getLong(cursor, ID, 0);
        final String name = AgtrDbHelper.getString(cursor, ROLE_NAME, "");
        final Long orgId = AgtrDbHelper.getLong(cursor, ORG_ID, 0);
        role.setId(id);
        role.setOrg(Util.getOrganisation(orgId));
        role.setRoleName(name);
        return role;
    }
}
