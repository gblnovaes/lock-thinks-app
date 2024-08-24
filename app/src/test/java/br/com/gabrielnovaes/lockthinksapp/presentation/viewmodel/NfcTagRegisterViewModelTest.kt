package br.com.gabrielnovaes.lockthinksapp.presentation.viewmodel

import app.cash.turbine.test
import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.AddNfcTagUseCase
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.GetNfcTagUseCase
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.GetPreferencesNfcStatusUseCase
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.SaveNfcPreferencesStatusUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NfcTagRegisterViewModelTest {
    private lateinit var viewModel: NfcTagRegisterViewModel
    private lateinit var addNfcTagUseCase: AddNfcTagUseCase
    private lateinit var getNfcTagUseCase: GetNfcTagUseCase
    private lateinit var getPreferencesNfcStatusUseCase: GetPreferencesNfcStatusUseCase
    private lateinit var saveNfcPreferencesStatusUseCase: SaveNfcPreferencesStatusUseCase

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        addNfcTagUseCase = mockk(relaxed = true)
        getNfcTagUseCase = mockk(relaxed = true)
        getPreferencesNfcStatusUseCase = mockk(relaxed = true)
        saveNfcPreferencesStatusUseCase = mockk(relaxed = true)

        viewModel = NfcTagRegisterViewModel(
            addNfcTagUseCase,
            getNfcTagUseCase,
            getPreferencesNfcStatusUseCase,
            saveNfcPreferencesStatusUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `addText should call addNfcTagUseCase and update tag`() = runTest {
        val sampleTag = "37:C6:F8:5D"
        val nfcTags = listOf(NfcTag(tag = sampleTag))

        coEvery { getNfcTagUseCase.invoke() } returns nfcTags

        viewModel.addText(sampleTag)

        coVerify { addNfcTagUseCase.invoke(sampleTag) }

        viewModel.tag.test {
            assertEquals(nfcTags, awaitItem())
        }
    }

    @Test
    fun `loadTexts should update tag`() = runTest {
        val nfcTags = listOf(NfcTag(tag = "37:C6:F8:5D"))

        coEvery { getNfcTagUseCase.invoke() } returns nfcTags

        viewModel.loadTexts()

        viewModel.tag.test {
            assertEquals(nfcTags, awaitItem())
        }
    }

    @Test
    fun `getStatus should update isClosed`() = runTest {
        coEvery { getPreferencesNfcStatusUseCase.invoke() } returns true

        viewModel.getStatus()

        viewModel.isClosed.test {
            assertEquals(true, awaitItem())
        }
    }

    @Test
    fun `setStatus should call saveNfcPreferencesStatusUseCase and update isClosed`() = runTest {
        val status = true

        viewModel.setStatus(status)

        verify { saveNfcPreferencesStatusUseCase.invoke(status) }

        viewModel.isClosed.test {
            assertEquals(status, awaitItem())
        }
    }

    @Test
    fun `getStatusChanged should toggle and save new status`() = runTest {
        viewModel.getStatusChanged()

        verify { saveNfcPreferencesStatusUseCase.invoke(true) }

        viewModel.isClosed.test {
            assertEquals(true, awaitItem())
        }
    }
}