package master_provider_else.reclamos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import master_provider_else.reclamos.data.database.dao.UserDao
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeEntity
import master_provider_else.reclamos.data.database.entity.UserEntity

@Database(
  entities = [UserEntity::class, ReclamoEntity::class, ReclamoInformeEntity::class],
  version = 1
)
abstract class QuoteDatabase : RoomDatabase() {
  abstract fun getLogin(): UserDao
}