package app.niit.hackaton.agrt.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * add Organisation to DB
     *
     * @param o {@link app.niit.hackaton.agrt.dto.Organisation}
     */
    public void saveOrganisation(final Organisation o) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = OrganizationTable.createValuesFromObject(o);
        db.insert(OrganizationTable.TABLE,null,cv);
        if (db.isOpen()) {
            db.close();
        }
    }

    /**
     * add User to DB
     *
     * @param user {@link app.niit.hackaton.agrt.dto.User}
     */
    public void saveAppUser(final User user) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = AppUserTable.createValuesFromObject(user);
        db.insert(AppUserTable.TABLE,null,cv);
        if (db.isOpen()) {
            db.close();
        }
    }

    /**
     * add Role to DB
     *
     * @param role {@link app.niit.hackaton.agrt.dto.Role}
     */
    public void saveAppUser(final Role role) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = RoleTable.createValuesFromObject(role);
        db.insert(RoleTable.TABLE,null,cv);
        if (db.isOpen()) {
            db.close();
        }
    }

    /**
     * add Asset to DB
     *
     * @param asset {@link app.niit.hackaton.agrt.dto.Asset}
     */
    public void saveAsset(final Asset asset) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = AssetTable.createValuesFromObject(asset);
        db.insert(AssetTable.TABLE,null,cv);
        if (db.isOpen()) {
            db.close();
        }
    }

    /**
     * add AssetRegister to DB
     *
     * @param asset {@link app.niit.hackaton.agrt.dto.AssetRegister}
     */
    public void saveAsset(final AssetRegister asset) {
        final SQLiteDatabase db = getReadableDatabase();
        final ContentValues cv = AssetRegisteryTable.createValuesFromObject(asset);
        db.insert(AssetRegisteryTable.TABLE,null,cv);
        if (db.isOpen()) {
            db.close();
        }
    }
}
