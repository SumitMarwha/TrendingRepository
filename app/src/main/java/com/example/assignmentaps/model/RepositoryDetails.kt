package com.example.assignmentaps.model;

import android.os.Parcel
import android.os.Parcelable

class RepositoryDetails() : Parcelable {

    var username: String? = null
    var name: String? = null
    var type: String? = null
    var url: String? = null
    var avatar: String? = null
    var repo: Repo? = null

    constructor(parcel: Parcel) : this() {
        username = parcel.readString()
        name = parcel.readString()
        type = parcel.readString()
        url = parcel.readString()
        avatar = parcel.readString()
        repo = parcel.readParcelable(Repo::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeString(avatar)
        parcel.writeParcelable(repo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoryDetails> {
        override fun createFromParcel(parcel: Parcel): RepositoryDetails {
            return RepositoryDetails(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryDetails?> {
            return arrayOfNulls(size)
        }
    }

}
