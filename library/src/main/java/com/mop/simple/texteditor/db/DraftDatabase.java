package com.mop.simple.texteditor.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Administrator on 2018/2/2.
 */
@Database(name = DraftDatabase.NAME, version = DraftDatabase.VERSION)
public class DraftDatabase {
    //数据库名称
    public static final String NAME = "draft";
    //数据库版本号
    public static final int VERSION = 1;
}

