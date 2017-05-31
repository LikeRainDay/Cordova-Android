package org.yunwen.bigscreen

import android.content.Intent
import android.util.Log
import org.apache.cordova.CallbackContext
import org.apache.cordova.CordovaArgs
import org.apache.cordova.CordovaPlugin

/**
 * Created by housh on 2017/5/31.
 */
class MyPlugin : CordovaPlugin() {

    val ACTION_ADD_CALENDAR_ENTRY: String = "addCalendarEntry"

    override fun execute(action: String?, args: CordovaArgs?, callbackContext: CallbackContext?): Boolean {
        try {
            Log.e("AA","我走了")
            if (ACTION_ADD_CALENDAR_ENTRY.equals(action)) {
                val jsonObject = args?.getJSONObject(0)
                val intent = Intent(Intent.ACTION_EDIT)
                intent.setType("vnd.android.cursor.item/event")
                        .putExtra("beginTime", jsonObject?.getLong("startTimeMillis"))
                        .putExtra("endTime", jsonObject?.getLong("endTimeMillis"))
                        .putExtra("title", jsonObject?.getLong("title"))
                        .putExtra("description", jsonObject?.getLong("description"))
                        .putExtra("eventLocation", jsonObject?.getLong("eventLocation"))

                this.cordova.activity.startActivity(intent)

                callbackContext?.success()
                return true

            }
            callbackContext?.error("Invalid action")
            return false
        } catch (e: Exception) {
            System.err.println("Exception: " + e.message)
            callbackContext?.error(e.message)
            return false
        }
    }

}