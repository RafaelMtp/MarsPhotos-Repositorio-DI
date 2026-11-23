package com.example.marsphotos.fake

import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.model.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    var exception: Exception? = null

    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        exception?.let { throw it }
        return FakeDataSource.photosList
    }
}
