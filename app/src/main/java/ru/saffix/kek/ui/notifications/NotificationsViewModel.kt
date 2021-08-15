package ru.saffix.kek.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = getText()
    }

    private fun getText(): String {
        val tr = Transliterator()
        val ruText = "Я очень люблю котов"
        val result = tr.transliterate(ruText)
        return result
    }
    val text: LiveData<String> = _text



    companion object {

        private fun mathematics(): Double {
            val x = 3
            val y = 12


            return Math.pow(x.toDouble(), y.toDouble())
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val x = mathematics()
            println(x.toLong())

            val vichest = 34433 - 32
            println(vichest)
        }
    }
}