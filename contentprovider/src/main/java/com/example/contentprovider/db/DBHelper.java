package com.example.contentprovider.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * @author JonesYang
 * @Data 2020-12-08
 * @Function SQLite 数据库相关操作
 */
public class DBHelper extends SQLiteOpenHelper {
    // 数据库名称
    private static final String DATABASE_NAME = "Gank.db";
    //数据库版本号
    private static final int DATABASE_VERSION = 1;
    //建表 ---> 用户信息
    private static final String USER = "CREATE TABLE user(id integer primary key autoincrement," +
            "peopleId varchar(64)," +
            "peoplePassword varchar(64)" +
            ")";

    //关于 SQL 的各种相关操作
    private static final String insertPeopleById = "INSERT INTO user(peopleId,peoplePassword) VALUES (? , ?)";
    private static final String queryPeopleById = "SELECT * FROM user WHERE peopleId = ?";
    private SQLiteDatabase mSQLiteDatabase;

    /**
     * 数据库的构造函数
     *
     * @param context
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 创建数据库时执行该方法
     *
     * @param db 数据库名称
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER);
    }

    /**
     * 升级数据库时执行该方法
     *
     * @param db         数据库名称
     * @param oldVersion 当前版本号
     * @param newVersion 新的版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * 根据 id 插入用户，如果用户已经存在，则插入失败
     *
     * @param id
     * @return
     */
    public boolean insertPeopleById(String id, String password) {
        mSQLiteDatabase = getReadableDatabase();
        //如果在数据库中存在，返回 false 插入失败
        if (queryPeopleInfoById(id, password)) {
            return false;
        }
        //不存在数据库中
        mSQLiteDatabase.execSQL(insertPeopleById, new Object[]{id, password});
        return true;
    }

    /**
     * 根据 ID 查询是否存在数据库中
     *
     * @param name     账号 ID
     * @param password
     * @return 默认没有查到
     */
    public boolean queryPeopleInfoById(String name, String password) {
        // TODO：只根据 peopleId 查找，还没有验证密码的正确性
        Cursor cursor = mSQLiteDatabase.rawQuery(queryPeopleById, new String[]{name.trim(), password.trim()});
        while (cursor.moveToNext()) {
            if (name.equals(cursor.getColumnIndex("peopleId"))) {
                return true;
            }
        }
        return false;
    }
    //TODO：目前只有查找和增加功能
}
