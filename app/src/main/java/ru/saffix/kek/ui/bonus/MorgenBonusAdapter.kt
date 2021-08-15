package ru.saffix.kek.ui.bonus

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.saffix.kek.R

class MorgenBonusAdapter() :
    RecyclerView.Adapter<MorgenBonusAdapter.MyViewHolder>() {
    private val bonuses: MutableList<MorgenBonus> = mutableListOf()

    init {
        setHasStableIds(true)
    }
    var tracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView?.text = bonuses[position].bonusName
        holder.smallTextView?.text = bonuses[position].quote
        holder.pictire?.also {
            Glide.with(it)
                .load(bonuses[position].pictureUrl)
                .into(it)
        }

        val number = bonuses[position]
        tracker?.let {
            holder.root?.isActivated = it.isSelected(position.toLong())
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount() = bonuses.size

    fun submitList(bonuses: List<MorgenBonus>) {
        this.bonuses.clear()
        this.bonuses.addAll(bonuses)
    }

    fun getItemAt(index: Int?): MorgenBonus? {
        if (index == null) return null
        return bonuses.getOrNull(index)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var largeTextView: TextView? = null
        var smallTextView: TextView? = null
        var pictire: ImageFilterView? = null
        var root: LinearLayout? = null

        init {
            largeTextView = itemView.findViewById(R.id.textViewLarge)
            smallTextView = itemView.findViewById(R.id.textViewSmall)
            pictire = itemView.findViewById(R.id.itemImage)
            root = itemView.findViewById(R.id.itemViewRoot)
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long = itemId
            }
    }

}


class MyItemDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as MorgenBonusAdapter.MyViewHolder)
                .getItemDetails()
        }
        return null
    }
}