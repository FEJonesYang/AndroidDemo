package com.example.contentprovider.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.contentprovider.db.DBHelper;

/**
 * @author JonesYang
 * @Data 2020-12-15
 * @Function 使用 ContentProvider 操作数据库
 */
public class TestProvider extends ContentProvider {

    /**
     *  思考：
     *  1、使用 ContentProvider 操作数据，把我们这个应用程序的数据库提供给其他的 App。
     *  2、进程 A 需要操作 进程 B 的 数据，ContentProvider 应该在进程B中，在进程 A 中
     *      需要使用 ContentResolver 访问 进程 B 数据。
     *  3、ContentProvider 不是数据源，只是数据源的中间操作者，它提供了一个相对安全的环境。
     * */

    private Context mContext;
    private DBHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    static UriMatcher sUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        return matcher;
    }

    /**
     * @return 初始化数据库
     */
    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
        mDatabase.execSQL("DELETE FROM user");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
