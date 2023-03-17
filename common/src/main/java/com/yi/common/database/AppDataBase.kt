package com.yi.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * create by yi on 2023/3/15
 * room 数据库
 * @param entities 表集合
 * @param version 版本
 * @param exportSchema 是否导出数据库概要、图解到文件夹
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    //定义user Dao 层接口
    abstract fun userDao(): UserDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // 数据库升级操作
        database.execSQL("ALTER TABLE user ADD COLUMN job TEXT")
    }
}

val MIGRATION_2_3 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        //TODO 数据库升级操作
    }
}
