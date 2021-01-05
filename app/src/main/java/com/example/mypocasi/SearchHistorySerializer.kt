package com.example.mypocasi

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class SearchHistorySerializer : Serializer<SearchHistory>{
    override val defaultValue: SearchHistory = SearchHistory.getDefaultInstance()
    override fun readFrom(input: InputStream): SearchHistory{
        try {
            return SearchHistory.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    }

    override fun writeTo(t: SearchHistory, output: OutputStream) = t.writeTo(output)


}