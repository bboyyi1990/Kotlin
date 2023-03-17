//package com.yi.common.realmdatabase
//
//import io.realm.Realm
//import io.realm.RealmConfiguration
//import io.realm.RealmMigration
//import io.realm.RealmResults
//
//
//object RealmHelper {
//
//    private lateinit var realmDataBase: Realm
//
//
//    fun getRealDb(): Realm {
//        if (null == realmDataBase) {
////            Realm.getDefaultInstance()
//            //在realm初始化config 中设置数据库名，版本号，升级迁移细节。
//            val config = RealmConfiguration.Builder()
//                .name("myrealm.realm") //文件名
//                .schemaVersion(0) //版本号
//                .migration(realmMigration)
//                .build()
//            realmDataBase = Realm.getInstance(config)
//        }
//
//        return realmDataBase
//    }
//
//    /**
//     * 数据库升级迁移
//     */
//    private val realmMigration = RealmMigration { realm, oldVersion, newVersion ->
//        var oldV = oldVersion
//        val schema = realm.schema
//        //定义数据库升级细节
//        //0-1 升级
//        if (oldV == 0L) {
//            //获取UserRealm 表
//            val userSchema = schema.get("UserRealm")
//            //增加 nickname 字段
//            userSchema.addField("nickName", String::class.java)
//            userSchema.transform {
//                it.set("nikeName", "")
//            }
//            oldV++
//        }
//        //1-2升级
//        if (oldV == 1L) {
//
//        }
//    }
//
//    //realm 异步操作执行插入任务
////    fun insert() {
////        //数据库执行一个异步任务，插入对象
////        val transaction = getRealDb().executeTransactionAsync(Realm.Transaction { realm ->
////            val userRealm = realm.createObject(UserRealm::class.java)
////            userRealm.id = 111
////            userRealm.name = "${System.currentTimeMillis()}"
////        }, Realm.Transaction.OnSuccess {
////            //成功回调，还可设置失败回调
////        })
////        //可以对异步任务进行取消操作。
//////        transaction.cancel()
////    }
//
//    /**
//     * realm 执行查询操作
//     */
////    fun find() {
////        val result:RealmResults<UserRealm> = getRealDb().where(UserRealm::class.java)
////            .equalTo("id", 1111)
////            .findAllAsync()//异步查询避免arn
////    }
//
//}