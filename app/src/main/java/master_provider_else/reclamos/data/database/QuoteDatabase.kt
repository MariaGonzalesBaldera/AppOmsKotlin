package master_provider_else.reclamos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import master_provider_else.reclamos.data.database.dao.UserDao
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.database.entity.UserEntity

@Database(
  entities = [UserEntity::class, ReclamoEntity::class, ReclamoInformeOMSEntity::class],
  version = 2
)
abstract class QuoteDatabase : RoomDatabase() {
  abstract fun getLogin(): UserDao
}