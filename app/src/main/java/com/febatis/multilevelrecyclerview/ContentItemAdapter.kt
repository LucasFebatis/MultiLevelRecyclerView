package com.febatis.multilevelrecyclerview

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.febatis.multilevelrecyclerview.MyAdapter.MyViewHolder


class ContentItemAdapter:
    RecyclerView.Adapter<MyViewHolder>() {

    class ContentItemViewHolder(view: View) : MyViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_content_multilevel, parent, false)

        return ContentItemViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

}