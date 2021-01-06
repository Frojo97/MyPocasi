package com.example.mypocasi

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

/*
class ProtoRepository(context: Context){

    private val dataStore: DataStore<SearchHistory> = context.createDataStore(
                    fileName = "search_history",
                    serializer = SearchHistorySerializer)

    val searchHistoryRead: Flow<SearchHistory> = dataStore.data
            .catch { exception->
                if (exception is IOException){
                    Log.d("Error", exception.message.toString())
                    emit(SearchHistory.getDefaultInstance())
                }
                else {
                    throw exception
                }
            }

    suspend fun update(pocet: int){
        dataStore.updateData { preference->
            preference.toBuilder().setPocet(pocet).build()
        }
    }


}*/