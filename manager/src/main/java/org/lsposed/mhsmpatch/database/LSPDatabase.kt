package org.lsposed.mhsmpatch.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.lsposed.mhsmpatch.database.dao.ModuleDao
import org.lsposed.mhsmpatch.database.dao.ScopeDao

import org.lsposed.mhsmpatch.database.entity.Module
import org.lsposed.mhsmpatch.database.entity.Scope

@Database(entities = [Module::class, Scope::class], version = 1)
abstract class LSPDatabase : RoomDatabase() {
    abstract fun moduleDao(): ModuleDao
    abstract fun scopeDao(): ScopeDao
}
