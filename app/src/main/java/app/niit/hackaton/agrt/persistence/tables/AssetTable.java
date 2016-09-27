package app.niit.hackaton.agrt.persistence.tables;

/**
 * Created by ChandraSekharPonugot on 27-09-2016.
 */

import android.content.ContentValues;
import android.net.Uri;

import java.io.File;

import app.niit.hackaton.agrt.dto.Asset;
import app.niit.hackaton.agrt.dto.AssetRegister;
import app.niit.hackaton.agrt.provider.AgtrProvider;


public class AssetTable {
    //AssetTable table database columns keys
    //The ID column must named only _id. Then only CursorAdapter will work
    private static final String ID = "_id";
    public static final String ASSET_NAME   = "ASSET_NAME ";
    public static final String ASSET_DESCRIPTION   = "ASSET_DESCRIPTION";
    public static final String SCAN_CODE  = "SCAN_CODE";
    public static final String SCAN_TYPE  = "SCAN_TYPE";
    public static final String ASSET_OWNER   = "ASSET_OWNER";
    public static final String ORG_ID   = "ORG_ID";


    //AssetTable table and its content uri
    public static final String TABLE = "ASSET_TABLE";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AgtrProvider.AUTHORITY + File.separator + TABLE);

    //AssetTable table creation
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ASSET_NAME  + " TEXT,"
            + ASSET_DESCRIPTION  + " TEXT,"
            + SCAN_CODE  + " TEXT,"
            + SCAN_TYPE  + " TEXT,"
            + ASSET_OWNER  + " TEXT,"
            + ORG_ID  + " INTEGER)";

    //AssetTable table projection
    public static final String[] PROJECTION = new String[]{
            ID,
            ASSET_NAME ,
            ASSET_DESCRIPTION,
            SCAN_CODE,
            SCAN_TYPE ,
            ASSET_OWNER,
            ORG_ID
    };

    public static ContentValues createValuesFromObject(final Asset v) {
        final ContentValues cv = new ContentValues();
        cv.put(ID, v.getId());
        cv.put(ASSET_NAME, v.getAssetName());
        cv.put(ASSET_DESCRIPTION, v.getAssetDescription());
        cv.put(SCAN_CODE, v.getScanCode());
        cv.put(SCAN_TYPE, v.getScanType());
        cv.put(ASSET_OWNER, v.getAssetOwner());
        cv.put(ORG_ID, v.getOrg().getId());
        return cv;
    }
}
