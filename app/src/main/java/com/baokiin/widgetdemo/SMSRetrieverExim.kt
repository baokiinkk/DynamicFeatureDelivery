package com.baokiin.widgetdemo

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.provider.Telephony
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*

object SMSRetrieverExim {
    var mActivity: AppCompatActivity? = null
    var sms : SmsReceiver? = null
    fun setActivity(activity: AppCompatActivity) {
        mActivity = activity
    }

    fun startSmsRetriever() {
//        try {
//            if (Build.MODEL.toLowerCase(Locale.ROOT) == "huawei") return
//
//            mActivity?.let { activity ->
//                sms = SmsReceiver()
//                val intentFilter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
//                activity.registerReceiver(
//                    sms,
//                    intentFilter
//                )
//            }
//        } catch (e: Exception) {
//        }
    }


}
class SmsReceiver : android.content.BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
//        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
//            val bundle = intent.extras
//            if (bundle != null) {
//                val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
//                for (sms in smsMessages) {
//                    val messageBody = sms.messageBody
//                    val senderAddress = sms.originatingAddress
//                    Log.d("quocbao",senderAddress.toString()+"-"+messageBody.toString())
//                }
//            }
//        }
    }
}