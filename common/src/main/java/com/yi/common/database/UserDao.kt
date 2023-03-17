package com.yi.common.database

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import io.reactivex.Observable

/**
 * create by yi on 2023/3/15
 * room 数据库 user 表 的 dao 接口
 */
@Dao
interface UserDao {

    /**
     * 增、改操作
     * 数据存在则替换更新
     * 数据不存在则插入
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: User)

    @Delete
    fun delete(vararg users: User)

    @Query("select * from user")
    fun queryAll(): MutableList<User>

    @Query("select * from user where id = :id")
    fun queryById(id: String): User?

    @Query("select * from user")
    fun queryAllLiveDate(): LiveData<MutableList<User>>

    @Query("select * from user")
    fun queryAllObservable(): Observable<MutableList<User>>
}