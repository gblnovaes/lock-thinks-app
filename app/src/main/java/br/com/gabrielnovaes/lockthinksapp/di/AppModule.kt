package br.com.gabrielnovaes.lockthinksapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import br.com.gabrielnovaes.lockthinksapp.data.local.NfcTagPreferencesImp
import br.com.gabrielnovaes.lockthinksapp.data.local.database.AppDatabase
import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagRepository
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.AddNfcTagUseCase
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.GetNfcTagUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "nfc_db").build()
    }

    @Provides
    @Singleton
    fun provideNfcTagDao(database: AppDatabase) = database.nfcTagDao()

    @Provides
    @Singleton
    fun provideAddNfcTagUseCase(repository: NfcTagRepository): AddNfcTagUseCase {
        return AddNfcTagUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetNfcTagUseCase(repository: NfcTagRepository): GetNfcTagUseCase {
        return GetNfcTagUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideNfcTagPreferences(
        sharedPreferences: SharedPreferences
    ): NfcTagPreferencesImp {
        return NfcTagPreferencesImp(sharedPreferences)
    }
}

