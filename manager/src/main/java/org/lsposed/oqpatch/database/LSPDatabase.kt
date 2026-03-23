package org.lsposed.oqpatch.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.lsposed.oqpatch.database.dao.ModuleDao
import org.lsposed.oqpatch.database.dao.ScopeDao

import org.lsposed.oqpatch.database.entity.Module
import org.lsposed.oqpatch.database.entity.Scope

@Database(entities = [Module::class, Scope::class], version = 1)
abstract class LSPDatabase : RoomDatabase() {
    abstract fun moduleDao(): ModuleDao
    abstract fun scopeDao(): ScopeDao
}
