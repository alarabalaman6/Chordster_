package com.alarabalaman.chordster_

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(val id: Int, val name: String, val chords: String, @DrawableRes val chordSheet: Int) : Parcelable {

}

