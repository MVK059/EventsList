package com.mvk.events.data.remote

import com.mvk.events.data.model.EventList
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET(Endpoints.EVENT)
    fun doEventCall(
        @Query("norm") norm: Int? = 1,
        @Query("filterBy") filterBy: String? = "go-out",
        @Query("city") city: String? = "mumbai"
    ): Single<EventList>

    /*
     * Example to add other headers
     *
     *  @POST(Endpoints.DUMMY_PROTECTED)
        fun sampleDummyProtectedCall(
            @Body request: DummyRequest,
            @Header(Networking.HEADER_USER_ID) userId: String, // pass using UserRepository
            @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String, // pass using UserRepository
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
        ): Single<DummyResponse>
     */
}