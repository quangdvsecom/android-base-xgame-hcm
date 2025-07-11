package com.xgame.base.helpers.volume

import android.content.Context
import android.media.AudioManager
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class VolumeController(context: Context) {

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    var currentVolume by mutableIntStateOf(fetchCurrentVolume())
        private set

    val maxVolume: Int = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

    fun fetchCurrentVolume(): Int {
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    }

    fun increaseVolume() {
        if (currentVolume < maxVolume) {
            audioManager.adjustStreamVolume(
                AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_PLAY_SOUND
            )
            currentVolume = fetchCurrentVolume()
        }
    }

    fun decreaseVolume() {
        if (currentVolume > 0) {
            audioManager.adjustStreamVolume(
                AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_PLAY_SOUND
            )
            currentVolume = fetchCurrentVolume()
        }
    }
}
