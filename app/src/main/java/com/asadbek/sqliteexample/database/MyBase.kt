package com.asadbek.sqliteexample.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.asadbek.sqliteexample.models.MyUser


const val TABLE_NAME = "users"
const val ID = "id"
const val NAME = "name"
const val SURNAME = "surname"


class MyBase(context: Context):SQLiteOpenHelper(context,"mydatabase.db",null,1),MyPlan{
    override fun onCreate(db: SQLiteDatabase?) {
        // databaselarni tablisalarini yaratish uchun kodlar yoziladi
        val query = "create table $TABLE_NAME($ID integer primary key autoincrement not null,$NAME text not null,$SURNAME text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addUser(myUser: MyUser) {
        val writer = this.writableDatabase
        // id avtomatik oshib borishi hisobiga uni qo`shish shart emas
        val contentValue = ContentValues()
        contentValue.put("$NAME",myUser.name)
        contentValue.put("$SURNAME",myUser.surname)
        writer.insert("$TABLE_NAME",null,contentValue)
        writer.close()
    }

    override fun getAllUsers(): ArrayList<MyUser> {
        val reader = this.readableDatabase // basedan ma`lumotni o`qib olish
        val list = ArrayList<MyUser>() // base dab olingan malumotlarni listga joylash uchun
        val query = "select * from $TABLE_NAME" // SQLite helperga so`rob yuborish
        val cursor = reader.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val myUser = MyUser(cursor.getInt(0),cursor.getString(1),cursor.getString(2))
                list.add(myUser)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun editUser(myUser: MyUser): Int {
        val writer = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("$ID",myUser.id)
        contentValues.put("$NAME",myUser.name)
        contentValues.put("$SURNAME",myUser.surname)
        return writer.update("$TABLE_NAME",contentValues,"$ID = ?", arrayOf(myUser.id.toString()))
    }

    override fun deleteUser(myUser: MyUser) {
        val writer = this.writableDatabase
        writer.delete("$TABLE_NAME","$ID = ?", arrayOf(myUser.id.toString()))
        writer.close()
    }
}