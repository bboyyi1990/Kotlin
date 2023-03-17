package com.yi.common.database

import androidx.room.Room
import com.yi.common.base.CommonBaseApplication
import com.yi.common.util.MemoryUtil

/**
 * create by yi on 2023/3/15
 * room 数据管理类
 */
object DbManager {

    /**
     * 数据库对象
     */
    val DB by lazy {
        val userId = MemoryUtil.getLong("userId", 0L)
        Room.databaseBuilder(
            CommonBaseApplication.getInstance(),
            AppDataBase::class.java,
            "yi.database_$userId"
        ).addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
//            .allowMainThreadQueries()//允许主线程数据库操作
            .build()
    }

    /**
     * 获取userDao 接口
     */
    fun getUserDao() = DB.userDao()
}