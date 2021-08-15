package ru.saffix.kek.ui.game

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ru.saffix.kek.R

class GameFragment : Fragment() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_game, container, false)

        val moneyTextView: TextView = root.findViewById(R.id.money)
        gameViewModel.money.observe(viewLifecycleOwner, Observer {
            moneyTextView.text = it
        })
        setupClicks(root)
        return root
    }

    private fun setupClicks(root: View) {
        val moneyTextView: TextView = root.findViewById(R.id.money)
        val morgenImage = root.findViewById<ImageFilterView>(R.id.morgen)
        morgenImage.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.click_image));
            gameViewModel.incrementMoney()
        }
    }

    private fun TextView.blink() {
        val backgroundOld = background
        setBackgroundColor(resources.getColor(R.color.design_default_color_on_primary, requireActivity().theme))
        handler.postDelayed({
          setBackgroundColor(resources.getColor(R.color.purple_200, requireActivity().theme))
        }, 500)
    }


}