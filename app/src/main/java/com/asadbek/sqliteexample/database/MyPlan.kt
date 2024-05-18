package com.asadbek.sqliteexample.database

import com.asadbek.sqliteexample.models.MyUser

interface MyPlan {
    // Database da bo`lishi kerak bo`lgan qo`shimcha imkoniyatlar
    // user ni qo`shish uchun fun...
    fun addUser(myUser: MyUser)

    // userlarni olish uchun fun
    fun getAllUsers():ArrayList<MyUser>

    // user ma`lumotlarini o`zgartirish uchun
    fun editUser(myUser: MyUser):Int


    // user ni o`chirish
    fun deleteUser(myUser: MyUser)

}