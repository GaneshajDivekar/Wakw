package com.a.demoapplication.data.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlaceHolderModel(
    var albumId: Int=0,
    var id: Int=0,
    var title: String="",
    var url: String="",
    var thumbnailUrl: String=""

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(albumId)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaceHolderModel> {
        override fun createFromParcel(parcel: Parcel): PlaceHolderModel {
            return PlaceHolderModel(parcel)
        }

        override fun newArray(size: Int): Array<PlaceHolderModel?> {
            return arrayOfNulls(size)
        }
    }
}
