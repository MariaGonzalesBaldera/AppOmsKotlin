package master_provider_else.reclamos.di

import android.content.Context
import androidx.room.Room
import master_provider_else.reclamos.data.database.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import master_provider_else.reclamos.data.database.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
  private const val QUOTE_DATABASE_NAME = "oms_database"

  @Singleton
  @Provides
  fun provideRoom(@ApplicationContext context: Context) =
    Room.databaseBuilder(context, QuoteDatabase::class.java, QUOTE_DATABASE_NAME)
      .fallbackToDestructiveMigration().build()

  @Singleton
  @Provides
  fun provideUserDao(db: QuoteDatabase): UserDao = db.getLogin()

}