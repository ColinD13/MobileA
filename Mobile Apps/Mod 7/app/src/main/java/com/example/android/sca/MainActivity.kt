package com.example.android.sca

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

public class RouteTrackingService : Service(){
    override fun onBind(intent: Intent): IBinder? = null

    companion object {
        const val NOTIFICATION_ID = 0xCA7
        const val EXTRA_SECRET_CAT_AGENT_ID = "scaId"
        private val mutableTrackingCompletion = MutableLiveData<String>()
        val trackingCompletion: LiveData<String> = mutableTrackingCompletion
    }

    private fun getPendingIntent() =
        PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val channelId = "routeTracking"
        val channelName = "Route Tracking"
        val channel =
            NotificationChannel(channelId, channelName,           NotificationManager.IMPORTANCE_DEFAULT)
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as       NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }

    private fun getNotificationBuilder(pendingIntent: PendingIntent, channelId: String) =
        NotificationCompat.Builder(this, channelId)
            .setContentTitle("Agent approaching destination")
            .setContentText("Agent dispatched")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setTicker("Agent dispatched, tracking movement")

    private fun startForegroundService(): NotificationCompat.Builder {
        val pendingIntent = getPendingIntent()
        val channelId =       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        } else {
            ""
        }
        val notificationBuilder = getNotificationBuilder(pendingIntent,       channelId)
        startForeground(NOTIFICATION_ID, notificationBuilder.build())
        return notificationBuilder
    }

    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var serviceHandler: Handler

    override fun onCreate() {
        super.onCreate()
        notificationBuilder = startForegroundService()
        val handlerThread = HandlerThread("RouteTracking").apply {        start()
        }
        serviceHandler = Handler(handlerThread.looper)

        workManager.getWorkInfoByIdLiveData(catSuitUpRequest.id)
            .observe(this, Observer { info ->
                if (info.state.isFinished) {
                    showResult("Agent done suiting up. Ready to go!")
                    launchTrackingService()
                }
            })
    }

    private fun trackToDestination(notificationBuilder:   NotificationCompat.Builder) {
        for (i in 10 downTo 0) {
            Thread.sleep(1000L)
            notificationBuilder           .setContentText("$i seconds to destination")
            startForeground(NOTIFICATION_ID,           notificationBuilder.build())
        }
    }

    private fun notifyCompletion(agentId: String) {
        Handler(Looper.getMainLooper()).post {
            mutableTrackingCompletion.value = agentId
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int,   startId: Int): Int {
        val returnValue = super.onStartCommand(intent, flags, startId)
        val agentId =       intent?.getStringExtra(EXTRA_SECRET_CAT_AGENT_ID)
            ?: throw IllegalStateException("Agent ID must be provided")
        serviceHandler.post {
            trackToDestination(notificationBuilder)
            notifyCompletion(agentId)
            stopForeground(true)
            stopSelf()
        }
        return returnValue
    }

    private fun launchTrackingService() {
        RouteTrackingService.trackingCompletion.observe(this, Observer {       agentId ->
            showResult("Agent $agentId arrived!")
        })
        val serviceIntent = Intent(this,       RouteTrackingService::class.java).apply {
            putExtra(EXTRA_SECRET_CAT_AGENT_ID, "007")
        }
        ContextCompat.startForegroundService(this, serviceIntent)
    }


}

