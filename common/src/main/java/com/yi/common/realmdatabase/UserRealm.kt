//package com.yi.common.realmdatabase
//
//import io.realm.RealmModel
//import io.realm.annotations.Ignore
//import io.realm.annotations.PrimaryKey
//import io.realm.annotations.RealmClass
//import io.realm.annotations.Required
//
//
///**
// * create by yi on 2023/3/15
// */
//@RealmClass
//open class UserRealm : RealmModel {
//    //主键
//    @PrimaryKey
//    var id: Int = 0
//
//    //不可空修饰，为空则导致编译失败
//    @Required
//    var name: String = ""
//
//    //忽略字段，存储时忽略该字段
//    @Ignore
//    var stuff: String? = null
//
//}