package id.taufiq.noteapp.db.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created By Taufiq on 1/1/2021.
 *
 */

@Parcelize
data class DataNotes(val id: Int, var judul: String, var isi: String): Parcelable