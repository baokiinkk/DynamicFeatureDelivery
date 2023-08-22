package com.baokiin.widgetdemo

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus


class MainActivity : AppCompatActivity() {
    private val splitInstallManager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(this)
    }
    private var mySessionId = 0
    private val progressDialog by lazy {
        ProgressDialog(this)
    }
    private val listener by lazy {
        SplitInstallStateUpdatedListener label@{ state: SplitInstallSessionState ->
            if (state.status() == SplitInstallSessionStatus.FAILED
                && state.errorCode() == SplitInstallErrorCode.SERVICE_DIED
            ) {
                return@label
            }
            if (state.sessionId() == mySessionId) {
                when (state.status()) {
                    SplitInstallSessionStatus.DOWNLOADING -> {
                        val totalBytes = state.totalBytesToDownload().toInt()
                        val progress = state.bytesDownloaded()
                        Log.d("quocbao",progress.toString())
                        progressDialog.setMessage("Downloading $progress")
                        progressDialog.show()
                    }
                    SplitInstallSessionStatus.INSTALLED -> {
                        startActivity(
                            Intent(
                                this,
                                Class.forName("com.baokiin.dynamicfeature.ModuleActivity")
                            )
                        )
                    }
                    SplitInstallSessionStatus.CANCELING -> {
                        progressDialog.setMessage("Canceling")
                        progressDialog.show()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SMSRetrieverExim.setActivity(this)
        SMSRetrieverExim.startSmsRetriever()

        findViewById<Button>(R.id.btnTestModule).setOnClickListener {
            registerPlayCoreStore()
        }
        findViewById<Button>(R.id.btnModuleOnTime).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Class.forName("com.baokiin.ontime.OnTimeActivity")
                )
            )
        }
    }

    private fun registerPlayCoreStore() {
        val request = SplitInstallRequest.newBuilder()
            .addModule("dynamicfeature")
            .build()

        splitInstallManager.registerListener(listener)
        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener {
                mySessionId = it
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    override fun onDestroy() {
        splitInstallManager.unregisterListener(listener)
        super.onDestroy()
    }
}