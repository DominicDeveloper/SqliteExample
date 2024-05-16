package com.asadbek.sqliteexample.models

class MyUser{
    var id:Int? = null
    var name:String? = null
    var surname:String? = null
    constructor()
    constructor(id: Int?, name: String?, surname: String?) {
        this.id = id
        this.name = name
        this.surname = surname
    }

    constructor(name: String?, surname: String?) {
        this.name = name
        this.surname = surname
    }


}
