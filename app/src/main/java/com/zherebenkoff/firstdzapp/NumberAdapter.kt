package com.zherebenkoff.firstdzapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter : RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    private val items = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItem() {
        items.add(items.size + 1)
        notifyItemInserted(items.size - 1)
    }

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val numberTextView: TextView = itemView.findViewById(R.id.numberTextView)

        fun bind(number: Int) {
            numberTextView.text = number.toString()

            // Устанавливаем цвет фона в зависимости от четности
            val backgroundColor = if (number % 2 == 0) {
                R.color.even_color
            } else {
                R.color.odd_color
            }

            numberTextView.setBackgroundColor(
                ContextCompat.getColor(itemView.context, backgroundColor)
            )

            // Делаем квадрат
            numberTextView.post {
                val width = numberTextView.width
                numberTextView.layoutParams.height = width
                numberTextView.requestLayout()
            }
        }
    }
}