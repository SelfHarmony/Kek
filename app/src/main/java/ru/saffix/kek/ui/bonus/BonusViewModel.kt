package ru.saffix.kek.ui.bonus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.saffix.kek.R
import java.util.*

class BonusViewModel : ViewModel() {

    private val _liveMorgenBonuses = MutableLiveData<List<MorgenBonus>>().apply {
        value = listOf(
            MorgenBonus(
                pictureUrl = "https://images.av.ru/av.ru/product/he1/h34/8968357445662.jpg",
                bonusName = "Ya Bogatyi",
                quote = "Это цитата",
                audioResource =  R.raw.morg_rich
            ),
            MorgenBonus(
                pictureUrl = "https://images11.esquire.ru/upload/img_cache/7d0/7d086c3a5256b1ecd10c397e6c6b64bb_ce_1080x673x0x140_cropped_960x600.jpg",
                bonusName = "Богатый",
                quote = "Я НЕ богатый",
                audioResource =  R.raw.morg_rich
            ),
            MorgenBonus(
                pictureUrl = "https://the-flow.ru/uploads/images/resize/830x0/adaptiveResize/13/06/36/58/37/86ff42440cd7.jpg",
                bonusName = "Я",
                quote = "Ненавижу Моргенштерна",
                audioResource =  R.raw.morg_rich
            )
        )
    }
    val morgenBonus: LiveData<List<MorgenBonus>> = _liveMorgenBonuses

}


data class MorgenBonus(
    val pictureUrl: String,
    val bonusName: String,
    val quote: String,
    val audioResource: Int,
)