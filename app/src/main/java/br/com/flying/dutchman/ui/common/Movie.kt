package br.com.flying.dutchman.ui.common

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val backdrop: String,
    val posterPath: String,
    val releaseData: String,
    val runtime: Int,
    val voteAverage: Float,
    val voteCount: Int,
    var isFavourite: Boolean = false,
    var inWatchList: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(backdrop)
        parcel.writeString(posterPath)
        parcel.writeString(releaseData)
        parcel.writeInt(runtime)
        parcel.writeFloat(voteAverage)
        parcel.writeInt(voteCount)
        parcel.writeByte(if (isFavourite) 1 else 0)
        parcel.writeByte(if (inWatchList) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}