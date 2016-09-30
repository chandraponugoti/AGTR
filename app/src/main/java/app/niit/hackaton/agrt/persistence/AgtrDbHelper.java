package app.niit.hackaton.agrt.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import app.niit.hackaton.agrt.dto.Asset;
import app.niit.hackaton.agrt.dto.AssetRegister;
import app.niit.hackaton.agrt.dto.Organisation;
import app.niit.hackaton.agrt.dto.Role;
import app.niit.hackaton.agrt.dto.User;
import app.niit.hackaton.agrt.persistence.tables.AppUserTable;
import app.niit.hackaton.agrt.persistence.tables.AssetRegisteryTable;
import app.niit.hackaton.agrt.persistence.tables.AssetTable;
import app.niit.hackaton.agrt.persistence.tables.OrganizationTable;
import app.niit.hackaton.agrt.persistence.tables.RoleTable;


public class AgtrDbHelper extends SQLiteOpenHelper {
    //Application Database name & version
    private static final String DB_NAME = "AGTR";
    private static final int DB_VERSION = 1;

    public AgtrDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Get string-value from cursor
     *
     * @param cursor:Cursor
     * @param fieldIndex:String
     * @param defaultValue:String
     * @return String
     */
    public static String getString(final Cursor cursor, final String fieldIndex, final String defaultValue) {
        try {
            final int index = cursor.getColumnIndex(fieldIndex);
            final String s = cursor.getString(index);
            if (null != s) {
                return s;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * Get boolean-value from cursor
     *
     * @param cursor:Cursor
     * @param fieldIndex:String
     * @return String
     */
    public static boolean getBoolean(final Cursor cursor, final String fieldIndex) {
        try {
            final int index = cursor.getColumnIndex(fieldIndex);
            final int i = cursor.getInt(index);
            return 1 == i;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int convertToBoolean(final boolean value) {
        if (value) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Get char-value from cursor
     *
     * @param cursor:Cursor
     * @param fieldIndex:String
     * @return char
     */
    public static char getChar(final Cursor cursor, final String fieldIndex) {
        try {
            final int index = cursor.getColumnIndex(fieldIndex);
            return cursor.getString(index).charAt(0);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return '?';
    }

    /**
     * Get int-value from cursor
     *
     * @param cursor:Cursor
     * @param fieldIndex:String
     * @return int
     */
    public static int getInt(final Cursor cursor, final String fieldIndex) {
        try {
            final int index = cursor.getColumnIndex(fieldIndex);
            return cursor.getInt(index);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get long-value from cursor
     *
     * @param cursor:Cursor
     * @param fieldIndex:String
     * @param defaultValue:long
     * @return long
     */
    public static long getLong(final Cursor cursor, final String fieldIndex, final long defaultValue) {
        try {
            final int index = cursor.getColumnIndex(fieldIndex);
            return cursor.getLong(index);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    @Override
    public void onOpen(final SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Added encode for special characters like umlaut etc
        db.execSQL(OrganizationTable.CREATE_TABLE);
        db.execSQL(AppUserTable.CREATE_TABLE);
        db.execSQL(RoleTable.CREATE_TABLE);
        db.execSQL(AssetTable.CREATE_TABLE);
        db.execSQL(AssetRegisteryTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * add Organisation to DB
     *
     * @param o {@link app.niit.hackaton.agrt.dto.Organisation}
     */
    public long saveOrganisation(final Organisation o) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = OrganizationTable.createValuesFromObject(o);
        long row = db.insert(OrganizationTable.TABLE, null, cv);
        if (db.isOpen()) {
            db.close();
        }
        return row;
    }

    /**
     * add User to DB
     *
     * @param user {@link app.niit.hackaton.agrt.dto.User}
     */
    public long saveAppUser(final User user) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = AppUserTable.createValuesFromObject(user);
        Long row = db.insert(AppUserTable.TABLE, null, cv);
        if (db.isOpen()) {
            db.close();
        }
        return row;
    }

    /**
     * add Role to DB
     *
     * @param role {@link Role}
     */
    public long saveRole(final Role role) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = RoleTable.createValuesFromObject(role);
        Long row = db.insert(RoleTable.TABLE, null, cv);
        if (db.isOpen()) {
            db.close();
        }
        return row;
    }

    /**
     * add Asset to DB
     *
     * @param asset {@link app.niit.hackaton.agrt.dto.Asset}
     */
    public long saveAsset(final Asset asset) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = AssetTable.createValuesFromObject(asset);
        Long row = db.insert(AssetTable.TABLE, null, cv);
        if (db.isOpen()) {
            db.close();
        }
        return row;
    }

    /**
     * add AssetRegister to DB
     *
     * @param asset {@link app.niit.hackaton.agrt.dto.AssetRegister}
     */
    public long saveAssetRegister(final AssetRegister asset) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = AssetRegisteryTable.createValuesFromObject(asset);
        Long row = db.insert(AssetRegisteryTable.TABLE, null, cv);
        if (db.isOpen()) {
            db.close();
        }
        return row;
    }

    public Organisation getOrganisationIdByName(String orgName) {
        final SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor;
        cursor = db.rawQuery(
                OrganizationTable.SELECT_ALL + " WHERE  " + OrganizationTable.ORG_NAME + " = '" + orgName + "'", null
        );
        Organisation lf = null;
        if (0 < cursor.getCount()) {
            cursor.moveToFirst();
            lf = OrganizationTable.createObjectFromCursor(cursor);
        }
        cursor.close();
        return lf;
    }

    public Organisation getOrganisationById(Long id) {
        final SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor;
        cursor = db.rawQuery(
                OrganizationTable.SELECT_ALL + " WHERE  " + OrganizationTable.ID + " = '" + id + "'", null
        );
        Organisation organisation = null;
        if (0 < cursor.getCount()) {
            cursor.moveToFirst();
            organisation = OrganizationTable.createObjectFromCursor(cursor);
        }
        cursor.close();
        return organisation;
    }

    public Asset getAssetByScanCodeAndType(String scancode, String scantype) {
        final SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor;
        cursor = db.rawQuery(
                AssetTable.SELECT_ALL + " WHERE  " + AssetTable.SCAN_CODE + " = '" + scancode + "' AND " + AssetTable.SCAN_TYPE + " = '" + scantype + "'", null
        );
        Asset asset = null;
        if (0 < cursor.getCount()) {
            cursor.moveToFirst();
            asset = AssetTable.createObjectFromCursor(cursor);
        }
        cursor.close();
        return asset;
    }

    public ArrayList<Organisation> getParentOrganisationList() {
        final ArrayList<Organisation> assets = new ArrayList<Organisation>();
        final SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor;
        cursor = db.rawQuery(
                OrganizationTable.SELECT_ALL, null
        );
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                final Organisation v = OrganizationTable.createObjectFromCursor(cursor);
                if ((null != v) && v.getParentId() == 0) {
                    assets.add(v);
                }
            } while (cursor.moveToNext());
        }
        return assets;
    }

    public ArrayList<Organisation> getOrganisationList() {
        final ArrayList<Organisation> assets = new ArrayList<Organisation>();
        final SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor;
        cursor = db.rawQuery(
                OrganizationTable.SELECT_ALL, null
        );
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                final Organisation v = OrganizationTable.createObjectFromCursor(cursor);
                if ((null != v)) {
                    assets.add(v);
                }
            } while (cursor.moveToNext());
        }
        return assets;
    }

    public ArrayList<AssetRegister> getAssetsListFromRegister() {
        final ArrayList<AssetRegister> assets = new ArrayList<AssetRegister>();
        final SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor;
        cursor = db.rawQuery(
                AssetRegisteryTable.SELECT_ALL, null
        );
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                final AssetRegister asset = AssetRegisteryTable.createObjectFromCursor(cursor);
                if ((null != assets)) {
                    assets.add(asset);
                }
            } while (cursor.moveToNext());
        }
        return assets;
    }

    public Asset getAssetById(Long id) {
        final SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor;
        cursor = db.rawQuery(
                AssetTable.SELECT_ALL + " WHERE  " + AssetTable.ID + " = '" + id + "'", null
        );
        Asset asset = null;
        if (0 < cursor.getCount()) {
            cursor.moveToFirst();
            asset = AssetTable.createObjectFromCursor(cursor);
        }
        cursor.close();
        return asset;
    }
}
