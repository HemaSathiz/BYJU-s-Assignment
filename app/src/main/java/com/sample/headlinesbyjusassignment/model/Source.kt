package com.sample.headlinesbyjusassignment.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Source(
    var name: String? = null
) : Parcelable
