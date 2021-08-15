package ru.saffix.kek.ui.bonus

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.saffix.kek.R

class BonusFragment : Fragment() {

    private lateinit var bonusViewModel: BonusViewModel
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bonusViewModel =
            ViewModelProvider(this).get(BonusViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bonus, container, false)
        recycler = root.findViewById(R.id.bonusRecycler)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInit()
    }

    private fun onInit() {
        val data: List<String> = generateTexts()
        recycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MorgenBonusAdapter()
        recycler.adapter = adapter

        val tracker1 = SelectionTracker.Builder<Long>(
            "mySelection",
            recycler,
            StableIdKeyProvider(recycler),
            MyItemDetailsLookup(recycler),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        adapter.tracker = tracker1


        bonusViewModel.morgenBonus.observe(viewLifecycleOwner) { bonusList ->
            adapter.submitList(bonusList)
        }

        Handler().postDelayed({
            showCurrentSelectedItems(tracker1, adapter)

        }, 10000)
    }

    private fun showCurrentSelectedItems(
        tracker1: SelectionTracker<Long>,
        adapter: MorgenBonusAdapter
    ) {
        tracker1.selection
        val item = adapter.getItemAt(tracker1.selection.firstOrNull()?.toInt())
        Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_LONG).show()
    }

    private fun generateTexts(): List<String> {
        return (1..40).map { "Morgen $it" }
    }
}