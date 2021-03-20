package com.kvsoftware.dependencyinjectionhilt.presentation.base

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(
    val context: Context,
    val items: MutableList<T>
) : RecyclerView.Adapter<VH>() {

    val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    override fun getItemCount(): Int = items.size

    open fun appendItem(item: T) {
        items.add(item)
        notifyDataSetChanged()
    }

    open fun appendItems(item: List<T>) {
        items.addAll(item)
        notifyDataSetChanged()
    }

    open fun appendNewItems(item: List<T>) {
        items.clear()
        items.addAll(item)
        notifyDataSetChanged()
    }

    open fun remove(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }

    open fun remove(t: T) {
        items.remove(t)
        notifyDataSetChanged()
    }

}