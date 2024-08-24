package br.com.gabrielnovaes.lockthinksapp.domain.usecase

import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagPreferencesRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPreferencesNfcStatusUseCaseTest {
    private lateinit var mockPreferencesRepository: NfcTagPreferencesRepository

    private lateinit var getPreferencesNfcStatusUseCase: GetPreferencesNfcStatusUseCase

    @Before
    fun setUp() {
        mockPreferencesRepository = mockk()

         getPreferencesNfcStatusUseCase = GetPreferencesNfcStatusUseCase(mockPreferencesRepository)
    }

    @Test
    fun `invoke should return true when repository returns true`() {
         every { mockPreferencesRepository.getStatus() } returns true

         val result = getPreferencesNfcStatusUseCase.invoke()

         assertEquals(true, result)
    }

    @Test
    fun `invoke should return false when repository returns false`() {
         every { mockPreferencesRepository.getStatus() } returns false

         val result = getPreferencesNfcStatusUseCase.invoke()

         assertEquals(false, result)
    }
}