package com.example.marsphotos

import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.example.marsphotos.rules.TestDispatcherRule
import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val viewModel = MarsViewModel(FakeNetworkMarsPhotosRepository())
        assertEquals(MarsUiState.Loading, viewModel.marsUiState)
        advanceUntilIdle()
        assertEquals(
            MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars photos retrieved"),
            viewModel.marsUiState
        )
    }

    @Test
    fun marsViewModel_networkFailure_showsError() = runTest {
        val repository = FakeNetworkMarsPhotosRepository()
        repository.exception = IOException("Sin conexion")
        val viewModel = MarsViewModel(repository)
        advanceUntilIdle()
        assertEquals(MarsUiState.Error, viewModel.marsUiState)
    }

    @Test
    fun marsViewModel_httpFailure_showsError() = runTest {
        val repository = FakeNetworkMarsPhotosRepository()
        repository.exception = HttpException(Response.error<String>(503, "Error".toResponseBody()))
        val viewModel = MarsViewModel(repository)
        advanceUntilIdle()
        assertEquals(MarsUiState.Error, viewModel.marsUiState)
    }

    @Test
    fun marsViewModel_newRequestAfterFailure_showsSuccess() = runTest {
        val repository = FakeNetworkMarsPhotosRepository()
        repository.exception = IOException("Sin conexion")
        val viewModel = MarsViewModel(repository)
        advanceUntilIdle()
        assertEquals(MarsUiState.Error, viewModel.marsUiState)
        repository.exception = null
        viewModel.getMarsPhotos()
        advanceUntilIdle()
        assertEquals(
            MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars photos retrieved"),
            viewModel.marsUiState
        )
    }
}
