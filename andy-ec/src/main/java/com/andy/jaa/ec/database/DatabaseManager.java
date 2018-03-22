package com.andy.jaa.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by quanxi on 2018/3/21.
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mUserDao = null;

    private DatabaseManager(){}

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class HOLDER{
        static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return HOLDER.INSTANCE;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"andyFestEc.db");
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        mUserDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getUserDao(){
        return mUserDao;
    }
}
