package com.example.mypocasi

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class HistoryLookSerializer : Serializer<SearchHistory> {
    override fun readFrom(input: InputStream): SearchHistory{
        try {
            return SearchHistory.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: SearchHistory, output: OutputStream) = t.writeTo(output)
}