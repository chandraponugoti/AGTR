package app.niit.hackaton.agrt.persistence.tables;

/**
 * Created by ChandraSekharPonugot on 27-09-2016.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;

import app.niit.hackaton.agrt.dto.Asset;
import app.niit.hackaton.agrt.dto.AssetRegister;
import app.niit.hackaton.agrt.persistence.AgtrDbHelper;
import app.niit.hackaton.agrt.provider.AgtrProvider;
import app.niit.hackaton.agrt.util.Util;


public class AssetTable {
    //AssetTable table database columns keys
    //The ID column must named only _id. Then only CursorAdapter will work
    public static final String ID = "_id";
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

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE;

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
        cv.put(ASSET_NAME, v.getAssetName());
        cv.put(ASSET_DESCRIPTION, v.getAssetDescription());
        cv.put(SCAN_CODE, v.getScanCode());
        cv.put(SCAN_TYPE, v.getScanType());
        cv.put(ASSET_OWNER, v.getAssetOwner());
        cv.put(ORG_ID, v.getOrg().getId());
        return cv;
    }

    public static Asset createObjectFromCursor(final Cursor cursor) {
        final Long id = AgtrDbHelper.getLong(cursor, ID, 0);
        final String name = AgtrDbHelper.getString(cursor, ASSET_NAME, "");
        final String description = AgtrDbHelper.getString(cursor,ASSET_DESCRIPTION, "");
        final String scan_code = AgtrDbHelper.getString(cursor,SCAN_CODE,"");
        final String scan_type = AgtrDbHelper.getString(cursor,SCAN_TYPE,"");
        final String asset_owner = AgtrDbHelper.getString(cursor,ASSET_OWNER,"");
        final long orgId = AgtrDbHelper.getLong(cursor,ORG_ID,0);
        final Asset asset = new Asset();
        asset.setId(id);
        asset.setAssetName(name);
        asset.setAssetDescription(description);
        asset.setScanCode(scan_code);
        asset.setScanType(scan_type);
        asset.setAssetOwner(asset_owner);
        asset.setOrg(Util.getOrganisation(orgId));
        return asset;
    }
}
