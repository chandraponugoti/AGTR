package app.niit.hackaton.agrt.persistence.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;

import app.niit.hackaton.agrt.dto.User;
import app.niit.hackaton.agrt.persistence.AgtrDbHelper;
import app.niit.hackaton.agrt.provider.AgtrProvider;
import app.niit.hackaton.agrt.util.Util;


public class AppUserTable {
    public static final String FIRST_NAME   = "FIRST_NAME ";
    public static final String LAST_NAME   = "LAST_NAME";
    public static final String PASSWORD  = "PASSWORD";
    public static final String ACCOUNT_NAME  = "ACCOUNT_NAME";
    public static final String NFC   = "NFC";
    public static final String MOBILE_NUMBER   = "MOBILE_NUMBER";
    public static final String ORG_ID   = "ORG_ID";
    public static final String ROLE_ID    = "ROLE_ID";
    //AppUserTable table and its content uri
    public static final String TABLE = "APP_USER";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AgtrProvider.AUTHORITY + File.separator + TABLE);
    public static final String SELECT_ALL = "SELECT * FROM " + TABLE;
    //AppUserTable table database columns keys
    //The ID column must named only _id. Then only CursorAdapter will work
    private static final String ID = "_id";
    //AppUserTable table creation
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FIRST_NAME  + " TEXT,"
            + LAST_NAME  + " TEXT,"
            + PASSWORD  + " TEXT,"
            + ACCOUNT_NAME  + " TEXT,"
            + NFC  + " TEXT,"
            + MOBILE_NUMBER  + " INTEGER,"
            + ORG_ID  + " INTEGER,"
            + ROLE_ID  + " INTEGER )";
    //AppUserTable table projection
    public static final String[] PROJECTION = new String[]{
            ID,
            FIRST_NAME ,
            LAST_NAME,
            PASSWORD,
            ACCOUNT_NAME ,
            NFC,
            MOBILE_NUMBER,
            ORG_ID,
            ROLE_ID

    };

    public static ContentValues createValuesFromObject(final User v) {
        final ContentValues cv = new ContentValues();
        cv.put(ID, v.getId());
        cv.put(FIRST_NAME, v.getFirstName());
        cv.put(LAST_NAME, v.getLastName());
        cv.put(PASSWORD, v.getPassword());
        cv.put(ACCOUNT_NAME, v.getAccountName());
        cv.put(NFC, String.valueOf(v.getNfc()));
        cv.put(MOBILE_NUMBER, v.getMobileNumber());
        cv.put(ORG_ID, v.getOrg().getId());
        cv.put(ROLE_ID, v.getRole().getId());
        return cv;
    }

    public static User createObjectFromCursor(final Cursor cursor) {
        User user = new User();
        final Long id = AgtrDbHelper.getLong(cursor, ID, 0);
        final String firstName = AgtrDbHelper.getString(cursor, FIRST_NAME, "");
        final String lastName = AgtrDbHelper.getString(cursor, LAST_NAME, "");
        final String password = AgtrDbHelper.getString(cursor, PASSWORD, "");
        final String accountName = AgtrDbHelper.getString(cursor, ACCOUNT_NAME, "");
        final String mobileNumber = AgtrDbHelper.getString(cursor, MOBILE_NUMBER, "");
        final String nfc = AgtrDbHelper.getString(cursor, NFC, "");
        final Long orgId = AgtrDbHelper.getLong(cursor, ORG_ID, 0);
        final Long roleId = AgtrDbHelper.getLong(cursor, ROLE_ID, 0);
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setAccountName(accountName);
        user.setMobileNumber(mobileNumber);
        user.setNfc(nfc);
        user.setOrg(Util.getOrganisation(orgId));
        user.setRole(Util.getRolesById(roleId));
        return user;
    }
}
