package com.sector.contacts.di.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sector.contacts.entity.User
import com.sector.contacts.model.database.dao.UserDao

@Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseProvider: RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "contacts_db"
    }

    abstract fun userDao(): UserDao
}