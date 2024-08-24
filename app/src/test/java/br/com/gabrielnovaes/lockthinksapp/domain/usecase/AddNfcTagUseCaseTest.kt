package br.com.gabrielnovaes.lockthinksapp.domain.usecase

import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag
import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNfcTagUseCaseTest {
    private lateinit var mockRepository: NfcTagRepository

    private lateinit var addNfcTagUseCase: AddNfcTagUseCase

    @Before
    fun setUp() {
        mockRepository = mockk(relaxed = true)
        addNfcTagUseCase = AddNfcTagUseCase(mockRepository)
    }

    @Test
    fun `invoke should call insertTagNfc with correct NfcTag`() = runBlocking {
        val tagText = "37:C6:F8:5D"

        val tagSlot = slot<NfcTag>()

        coEvery { mockRepository.insertTagNfc(capture(tagSlot)) } returns Unit

        addNfcTagUseCase.invoke(tagText)

        coVerify { mockRepository.insertTagNfc(any()) }

        assert(tagSlot.captured.tag == tagText)
    }
}