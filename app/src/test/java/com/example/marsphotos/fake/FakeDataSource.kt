package com.example.marsphotos.fake

import com.example.marsphotos.model.MarsPhoto

object FakeDataSource {
    val photosList = listOf(
        MarsPhoto(id = "1", imgSrc = "https://example.com/mars1.jpg"),
        MarsPhoto(id = "2", imgSrc = "https://example.com/mars2.jpg")
    )
}
