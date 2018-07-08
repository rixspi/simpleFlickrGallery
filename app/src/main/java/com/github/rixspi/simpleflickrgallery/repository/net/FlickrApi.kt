package com.github.rixspi.simpleflickrgallery.repository.net

import com.github.rixspi.simpleflickrgallery.repository.net.model.ImagesApiResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface FlickrApi {
    @GET("services/feeds/photos_public.gne?format=json")
    fun getImages(): Observable<ImagesApiResponse>
}