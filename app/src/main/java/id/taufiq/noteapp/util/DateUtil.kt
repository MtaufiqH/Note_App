package id.taufiq.noteapp.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created By Taufiq on 1/1/2021.
 *
 */


object DateUtil {

    fun dateFormat(date: Date) : String{
        val dateFormats = SimpleDateFormat("YYYY-MM-dd hh:mm:ss", Locale.US)
        return dateFormats.format(date)
    }

}