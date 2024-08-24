package br.com.gabrielnovaes.lockthinksapp.domain.usecase

import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag
import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetNfcTagUseCaseTest {
    private lateinit var mockRepository: NfcTagRepository

    private lateinit var getNfcTagUseCase: GetNfcTagUseCase

    @Before
    fun setUp() {
        mockRepository = mockk()

        getNfcTagUseCase = GetNfcTagUseCase(mockRepository)
    }

    @Test
    fun `invoke should return NfcTag from repository`() = runBlocking {
        val nfcTags = listOf(
            NfcTag(tag = "37:C6:F8:5D")
        )

        coEvery { mockRepository.getNfcTag() } returns nfcTags

        val result = getNfcTagUseCase.invoke()

        coVerify { mockRepository.getNfcTag() }

        assertEquals(nfcTags, result)
    }
}
