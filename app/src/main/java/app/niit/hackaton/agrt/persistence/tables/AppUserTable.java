package app.niit.hackaton.agrt.persistence.tables;

import android.net.Uri;

import java.io.File;

import app.niit.hackaton.agrt.provider.AgtrProvider;


public class AppUserTable {
    //OrganizationTable table database columns keys
    //The ID column must named only _id. Then only CursorAdapter will work
    private static final String ID = "_id";
    public static final String FIRST_NAME   = "FIRST_NAME ";
    public static final String LAST_NAME   = "LAST_NAME";
    public static final String PASSWORD  = "PASSWORD";
    public static final String ACCOUNT_NAME  = "ACCOUNT_NAME";
    public static final String NFC   = "NFC";
    public static final String MOBILE_NUMBER   = "MOBILE_NUMBER";
    public static final String ORG_ID   = "ORG_ID";
    public static final String ROLE_ID    = "ROLE_ID";
    public static final String FK_USER_ORGANISATION    = "FK_USER_ORGANISATION";


    //OrganizationTable table and its content uri
    public static final String TABLE = "APP_USER";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AgtrProvider.AUTHORITY + File.separator + TABLE);

    //OrganizationTable table creation
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FIRST_NAME  + " TEXT,"
            + LAST_NAME  + " TEXT,"
            + PASSWORD  + " TEXT,"
            + ACCOUNT_NAME  + " TEXT,"
            + NFC  + " TEXT,"
            + MOBILE_NUMBER  + " INTEGER,"
            + ORG_ID  + " INTEGER,"
            + ROLE_ID  + " INTEGER ,"
            + FK_USER_ORGANISATION  + " TEXT)";

    //OrganizationTable table projection
    public static final String[] PROJECTION = new String[]{
            ID,
            FIRST_NAME ,
            LAST_NAME,
            PASSWORD,
            ACCOUNT_NAME ,
            NFC,
            MOBILE_NUMBER,
            ORG_ID,
            ROLE_ID,
            FK_USER_ORGANISATION

    };
}
