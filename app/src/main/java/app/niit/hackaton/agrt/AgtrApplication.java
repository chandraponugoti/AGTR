package app.niit.hackaton.agrt;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicReference;

import app.niit.hackaton.agrt.persistence.AgtrDbHelper;

/**
 * Created by ChandraSekharPonugot on 28-09-2016.
 */

public class AgtrApplication extends Application {

    private static final AtomicReference<AgtrApplication> currentApplication = new AtomicReference<AgtrApplication>(null);
    private static AgtrDbHelper dba = null;

    public static AgtrApplication getCurrentInstance() {
        return currentApplication.get();
    }

    public static AgtrDbHelper getDbHelper() {
        return dba;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize database
        dba = new AgtrDbHelper(getApplicationContext());
        final SQLiteDatabase db = dba.getWritableDatabase();
        db.close();
    }

    @Override
    public void onTerminate() {
        currentApplication.set(null);
        super.onTerminate();
    }

    @Override
    protected void attachBaseContext(final Context base) {
        super.attachBaseContext(base);
        currentApplication.set(this);
    }
}
