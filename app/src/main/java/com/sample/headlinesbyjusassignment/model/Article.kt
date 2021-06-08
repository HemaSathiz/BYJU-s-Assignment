package com.sample.headlinesbyjusassignment.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    @NonNull var id: Int,
    var author: String? = null,
    var content: String? = null,
    var description: String? = null,
    var publishedAt: String? = null,
    var title: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var source: Source? = null
):Parcelable
