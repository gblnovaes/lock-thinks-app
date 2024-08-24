package br.com.gabrielnovaes.lockthinksapp.domain.usecase

import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagPreferencesRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SaveNfcPreferencesStatusUseCaseTest {
    private lateinit var mockPreferencesRepository: NfcTagPreferencesRepository

    private lateinit var saveNfcPreferencesStatusUseCase: SaveNfcPreferencesStatusUseCase

    @Before
    fun setUp() {
        mockPreferencesRepository = mockk(relaxed = true)

        saveNfcPreferencesStatusUseCase = SaveNfcPreferencesStatusUseCase(mockPreferencesRepository)
    }

    @Test
    fun `invoke should call saveStatus with true`() {
        saveNfcPreferencesStatusUseCase.invoke(true)

        verify { mockPreferencesRepository.saveStatus(true) }
    }

    @Test
    fun `invoke should call saveStatus with false`() {
        saveNfcPreferencesStatusUseCase.invoke(false)

        verify { mockPreferencesRepository.saveStatus(false) }
    }
}