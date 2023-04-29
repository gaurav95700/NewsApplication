package com.example.newsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Long = 0,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String = "",

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String? = "",

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String = "",

    @SerializedName("urlToImage")
    @ColumnInfo(name = "urlToImage")
    val imageUrl: String? = "",

    @SerializedName("source")
    @ColumnInfo(name = "source")
    val source: Source,

    )