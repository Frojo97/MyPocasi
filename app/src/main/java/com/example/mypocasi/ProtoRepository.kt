package com.example.mypocasi

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoRepository(context: Context){

    private val dataStore: DataStore<SearchHistory> = context.createDataStore(
            fileName = "search_hist.pb",
            serializer = HistoryLookSerializer()
    )

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

   suspend fun update(mesto: String, stat: String, teplota: String){
       /* dataStore.updateData { preference->
            preference.toBuilder().setNazevMesta(mesto).setZeme(stat).setTeplota(teplota).build()
        }*/
       dataStore.updateData { /*currentSettings->
           currentSettings.toBuilder().setNazevMesta(mesto).setZeme(stat).setTeplota(teplota).build()*/
           weather -> weather.toBuilder().setNazevMesta(mesto).setZeme(stat).setTeplota(teplota).build()
       }
   }
}

