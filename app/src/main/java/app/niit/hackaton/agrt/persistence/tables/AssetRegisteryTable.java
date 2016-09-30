package app.niit.hackaton.agrt.persistence.tables;

/**
 * Created by ChandraSekharPonugot on 27-09-2016.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;

import app.niit.hackaton.agrt.dto.AssetRegister;
import app.niit.hackaton.agrt.dto.Status;
import app.niit.hackaton.agrt.persistence.AgtrDbHelper;
import app.niit.hackaton.agrt.provider.AgtrProvider;
import app.niit.hackaton.agrt.util.Util;


public class AssetRegisteryTable {
    public static final String ASSET_ID = "ASSET_ID";
    public static final String STATUS = "STATUS";
    public static final String REGISTER_DATE = "REGISTER_DATE";
    public static final String EMPLOYEE_NAME = "EMPLOYEE_NAME";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String LOCATION = "LOCATION";
    //AssetRegisteryTable table and its content uri
    public static final String TABLE = "ASSET_REGISTERY";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AgtrProvider.AUTHORITY + File.separator + TABLE);
    public static final String SELECT_ALL = "SELECT * FROM " + TABLE;
    //AssetRegisteryTable table database columns keys
    //The ID column must named only _id. Then only CursorAdapter will work
    private static final String ID = "_id";
    //AssetRegisteryTable table creation
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ASSET_ID + " INTEGER,"
            + STATUS + " INTEGER,"
            + REGISTER_DATE + " NUMBER,"
            + EMPLOYEE_NAME + " TEXT,"
            + LATITUDE + " NUMBER,"
            + LONGITUDE + " NUMBER,"
            + LOCATION + " TEXT)";
    //AssetRegisteryTable table projection
    public static final String[] PROJECTION = new String[]{
            ID,
            ASSET_ID,
            STATUS,
            REGISTER_DATE,
            EMPLOYEE_NAME,
            LATITUDE,
            LONGITUDE,
            LOCATION
    };

    public static ContentValues createValuesFromObject(final AssetRegister v) {
        final ContentValues cv = new ContentValues();
        cv.put(ASSET_ID, v.getAsset().getId());
        cv.put(STATUS, String.valueOf(v.getStatus()));
        cv.put(REGISTER_DATE, v.getRegisterDate());
        cv.put(EMPLOYEE_NAME, v.getEmpName());
        cv.put(LATITUDE, v.getLatitude());
        cv.put(LONGITUDE, v.getLogitude());
        cv.put(LOCATION, v.getLocation());
        return cv;
    }


    public static AssetRegister createObjectFromCursor(final Cursor cursor) {
        final String id = AgtrDbHelper.getString(cursor, ID, "");
        final Long assetId = AgtrDbHelper.getLong(cursor, ASSET_ID, 0);
        final int status = AgtrDbHelper.getInt(cursor, STATUS);
        final long registerDate = AgtrDbHelper.getLong(cursor, REGISTER_DATE, 0);
        final String empName = AgtrDbHelper.getString(cursor, EMPLOYEE_NAME, "");
        final long longitude = AgtrDbHelper.getLong(cursor, LATITUDE, 0);
        final long latitude = AgtrDbHelper.getLong(cursor, LONGITUDE, 0);
        final String location = AgtrDbHelper.getString(cursor, LOCATION, "");
        final AssetRegister asset = new AssetRegister();
        asset.setAsset(Util.getAsset(assetId));
        if(status == 0) {
            asset.setStatus(Status.IN);
        }else{
            asset.setStatus(Status.OUT);
        }
        asset.setRegisterDate(registerDate);
        asset.setEmpName(empName);
        asset.setLatitude(latitude);
        asset.setLongitude(longitude);
        asset.setLocation(location);
        return asset;
    }
}
