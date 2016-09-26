package app.niit.hackaton.agrt.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import app.niit.hackaton.agrt.persistence.AgtrDbHelper;
import app.niit.hackaton.agrt.persistence.tables.AppUserTable;
import app.niit.hackaton.agrt.persistence.tables.OrganizationTable;


public class AgtrProvider extends ContentProvider {
    //Name of Content provider
    public static final String AUTHORITY = "hack.agtr.provider";

    //Content Ids
    private static final int ORGANIZATION = 1;
    private static final int APP_USER = 2;


    private UriMatcher uriMatcher;

    private AgtrDbHelper mAgrtDbHelper;

    @Override
    public boolean onCreate() {
        //Adding all Tables to Uri Matcher
        mAgrtDbHelper = new AgtrDbHelper(getContext());
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, OrganizationTable.TABLE, ORGANIZATION);
        uriMatcher.addURI(AUTHORITY, AppUserTable.TABLE, APP_USER);
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case ORGANIZATION:
                queryBuilder.setTables(OrganizationTable.TABLE);
                break;
            case APP_USER:
                queryBuilder.setTables(AppUserTable.TABLE);
                break;


            default:
                break;
        }
        //Getting Database writing mode, querying based on URI, notifying uri and returning cursor
        SQLiteDatabase db = mAgrtDbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        if (getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = mAgrtDbHelper.getWritableDatabase();
        long id = -1;
        Uri _uri = null;
        switch (uriMatcher.match(uri)) {
            case ORGANIZATION:
                id = db.insert(OrganizationTable.TABLE, null, values);
                if (id > 0) {
                    _uri = ContentUris.withAppendedId(OrganizationTable.CONTENT_URI, id);
                }
                break;

            case APP_USER:
                id = db.insert(AppUserTable.TABLE, null, values);
                if (id > 0) {
                    _uri = ContentUris.withAppendedId(AppUserTable.CONTENT_URI, id);
                }
                break;


            default:
                break;
        }
        //Getting Database writing mode, inserting based on URI, notifying uri and it
        if (id > 0 && getContext() != null && _uri != null) {
            getContext().getContentResolver().notifyChange(_uri, null);
        }
        return _uri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mAgrtDbHelper.getWritableDatabase();
        int deleted = 0;
        switch (uriMatcher.match(uri)) {
            case ORGANIZATION:
                deleted = db.delete(OrganizationTable.TABLE, selection, selectionArgs);
                break;

            case APP_USER:
                deleted = db.delete(AppUserTable.TABLE, selection, selectionArgs);
                break;

            default:
                break;
        }
        //Getting Database writing mode, deleting based on URI, notifying uri and returning deleted value.
        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mAgrtDbHelper.getWritableDatabase();
        int updated = 0;
        switch (uriMatcher.match(uri)) {

            default:
                break;
        }
        //Getting Database writing mode, updating based on URI, notifying uri and returning updated value.
        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }
}

