package com.example.newsapplication.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsapplication.domain.model.Source


@ProvidedTypeConverter
class NewsTypeConvertor {


    @TypeConverter
    fun sourceConvertToString(source: Source): String {
        return "${source.id},${source.name}"
    }

   @TypeConverter
    fun stringToSource(data:String):Source
    {
     return data.split(",").let { it-> Source(it[0],it[1]) }
    }
}