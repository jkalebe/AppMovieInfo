package br.com.example.appmovieinfo.di

import android.content.Context
import androidx.room.Room
import br.com.example.appmovieinfo.repository.AppDatabase
import br.com.example.appmovieinfo.repository.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideChanelDao(appDatabase: AppDatabase):MovieDao{
        return appDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext appContext: Context): AppDatabase{
        return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "appDb"
            ).build()
        }
}