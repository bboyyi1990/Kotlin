package com.yi.common.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * create by yi on 2023/3/15
 * room 数据库 user 表
 */
@Entity
class User {
    companion object {
        fun getDao() = DbManager.DB.userDao()
    }

    //主键
    @PrimaryKey
    var id: Long = 0

    var name: String? = null

    //Ignore注解字段不加入表中
    @Ignore
    var sex: String? = null
}