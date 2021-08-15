package ru.saffix.kek.ui.game

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import ru.saffix.kek.R
import ru.saffix.kek.ui.App

class GameViewModel : ViewModel() {
    private val prefs = App.instance.getSharedPreferences(MONEY_KEY, Context.MODE_PRIVATE)
    private var mediaPlayer = MediaPlayer.create(App.instance, R.raw.morg_rich)


    private fun playAudio(newMoney: Int) {
        if (newMoney > 100) {
            if (newMoney % 20 == 0) {
                mediaPlayer = MediaPlayer.create(App.instance, R.raw.morg_rich)
                mediaPlayer.start()
            }
        }

    }

    fun incrementMoney() {
        val newMoney = 1 + (_money.value ?: 0)
        _money.postValue(newMoney)
        prefs.edit().putInt(MONEY_KEY, newMoney).apply()
        playAudio(newMoney)
    }

    private val _money = MutableLiveData<Int>().apply {
        value = prefs.getInt(MONEY_KEY, 0) ?: 0
    }
    val money: LiveData<String> = _money.map { it.toString() }

    companion object {
        private const val MONEY_KEY = "money"
    }
}