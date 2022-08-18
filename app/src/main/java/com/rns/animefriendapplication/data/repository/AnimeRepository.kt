package com.rns.animefriendapplication.data.repository

import com.rns.animefriendapplication.data.Status
import com.rns.animefriendapplication.data.network.Client
import com.rns.animefriendapplication.data.response.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AnimeRepository {
    suspend fun fetchAnimeList(): Flow<Status<AnimeResponse>> = flow {
        emit(Status.Loading)
        emit(Client().getAnimeResponse())
    }.flowOn(Dispatchers.IO)
}