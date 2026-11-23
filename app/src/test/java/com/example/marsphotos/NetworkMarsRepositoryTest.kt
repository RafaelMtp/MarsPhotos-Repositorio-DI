package com.example.marsphotos

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeMarsApiService
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class NetworkMarsRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = NetworkMarsPhotosRepository(FakeMarsApiService())
        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }

    @Test(expected = IOException::class)
    fun networkMarsPhotosRepository_networkFailure_propagatesException() = runTest {
        val api = object : MarsApiService {
            override suspend fun getPhotos(): List<MarsPhoto> {
                throw IOException("Sin conexion")
            }
        }
        NetworkMarsPhotosRepository(api).getMarsPhotos()
        Unit
    }
}
