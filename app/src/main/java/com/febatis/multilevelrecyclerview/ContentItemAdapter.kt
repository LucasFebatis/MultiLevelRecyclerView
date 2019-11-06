package com.febatis.multilevelrecyclerview

import android.util.Log
import android.view.*
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.febatis.multilevelrecyclerview.MainAdapter.MyViewHolder


class ContentItemAdapter:
    RecyclerView.Adapter<ContentItemAdapter.ContentItemViewHolder>() {

    class ContentItemViewHolder(view: View) : MyViewHolder(view) {
        val btnTeste: Button? = view.findViewById(R.id.btn_teste)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_content_multilevel, parent, false)

        return ContentItemViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ContentItemViewHolder, position: Int) {


        holder.btnTeste!!.setOnClickListener {
            Log.d("Teste", "Index: " + position)
        }

    }

}