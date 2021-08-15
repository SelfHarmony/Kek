package ru.saffix.kek.ui.notifications

class Transliterator {

    private val alphabetMap = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "yo",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "y",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sch",
        "ъ" to "",
        "ы" to "y",
        "ь" to "'",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya",
        " " to "_",
        "А" to "A",
        "Б" to "B",
        "В" to "V",
        "Г" to "G",
        "Д" to "D",
        "Е" to "E",
        "Ё" to "Yo",
        "Ж" to "Zh",
        "З" to "Z",
        "И" to "I",
        "Й" to "Y",
        "К" to "K",
        "Л" to "L",
        "М" to "M",
        "Н" to "N",
        "О" to "O",
        "П" to "P",
        "Р" to "R",
        "С" to "S",
        "Т" to "T",
        "У" to "U",
        "Ф" to "F",
        "Х" to "H",
        "Ц" to "C",
        "Ч" to "Ch",
        "Ш" to "Sh",
        "Щ" to "Sch",
        "Ъ" to "",
        "Ы" to "Y",
        "Ь" to "'",
        "Э" to "E",
        "Ю" to "Yu",
        "Я" to "Ya"
    )

    fun transliterateAllEventParams(params: Map<String, Any>): Map<String, Any> {
        val newMap = mutableMapOf<String, Any>()

        params.keys.forEach {
            if (params[it] is String) {
                newMap[it] = transliterate(params[it] as String)
            } else {
                newMap[it] = params[it] as Any
            }
        }

        return newMap
    }

    fun transliterate(text: String): String {
        val strBldr = StringBuilder()

        text.forEach {
            kotlin.runCatching {
                alphabetMap[it.toString()]?.let {
                    strBldr.append(it)
                } ?: strBldr.append(it)
            }
        }

        return strBldr.toString()
    }

}