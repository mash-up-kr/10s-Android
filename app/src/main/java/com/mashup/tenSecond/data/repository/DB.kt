/*
package com.mashup.tenSecond.data.repository

class aaaDB {
    private var Instance: aaaDB? = null
    private lateinit var database: AppDatabase

    constructor(database: AppDatabase){
        this.database = database
        // do something from server data
    }


    fun newInstance(database : AppDatabase) : aaaDB {
        if(Instance == null){
            synchronized(aaaDB::class){
                Instance = aaaDB(database)
            }
        }
        return Instance!!
    }


}*/
