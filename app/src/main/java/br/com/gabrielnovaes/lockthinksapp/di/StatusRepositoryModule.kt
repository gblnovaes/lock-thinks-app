package br.com.gabrielnovaes.lockthinksapp.di

import br.com.gabrielnovaes.lockthinksapp.data.local.repository.NfcTagStatusRepositoryImp
import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StatusRepositoryModule {
    @Provides
    @Singleton
    fun bindNfcTagStatusRepository(
        imp: NfcTagStatusRepositoryImp
    ): NfcTagPreferencesRepository {
        return imp
    }
}