package com.febatis.multilevelrecyclerview

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private var myDataset: List<MultiLevelItem>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ivExpandIcon: View = view.findViewById(R.id.iv_expand_icon)
        var llHeader: View = view.findViewById(R.id.ll_header)
        var tvTitle: TextView = view.findViewById(R.id.tv_title)
        var llItems: View = view.findViewById(R.id.ll_items)
        var rvItems: RecyclerView = view.findViewById(R.id.rv_items)
        var llContent: View = view.findViewById(R.id.ll_content)
        var isOpen = true
        var itemMeasuredHeight = 0
        lateinit var listener: ViewTreeObserver.OnGlobalLayoutListener

    }

    // Object Functions

    private fun slideDown(holder: MyViewHolder) {

        val anim = ValueAnimator.ofInt(0, holder.itemMeasuredHeight)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = holder.llItems.layoutParams
            layoutParams.height = `val`
            holder.llItems.layoutParams = layoutParams
        }

        anim.duration = 300
        anim.start()
    }

    private fun slideUp(holder: MyViewHolder) {

        val anim = ValueAnimator.ofInt(holder.itemMeasuredHeight, 0)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = holder.llItems.layoutParams
            layoutParams.height = `val`
            holder.llItems.layoutParams = layoutParams
        }
        anim.duration = 300
        anim.start()
    }

    private fun toggleContents(holder: MyViewHolder) {

        if (holder.itemMeasuredHeight == 0 ) {
            holder.itemMeasuredHeight = holder.llItems.measuredHeight
        }

        holder.ivExpandIcon.animate()
            .rotation((if (holder.isOpen) -180 else 0).toFloat())
            .start()

        if (holder.isOpen) {
            slideUp(holder)
            holder.isOpen = false
        } else {
            slideDown(holder)
            holder.isOpen = true
        }
    }

    private fun setupRv(holder: MyViewHolder, i: Int) {

        viewManager = LinearLayoutManager(holder.itemView.context)
        viewAdapter = MyAdapter(myDataset[i].items!!)

        recyclerView = holder.itemView.findViewById<RecyclerView>(R.id.rv_items).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

    }

    private fun setupGlobalLayoutListener(holder: MyViewHolder) {
        val observer = holder.llItems.viewTreeObserver
        holder.listener = ViewTreeObserver.OnGlobalLayoutListener {
            if (holder.llItems.measuredHeight != 0) {
                holder.itemMeasuredHeight = holder.llItems.measuredHeight

                toggleContents(holder)


                if (holder.llItems.viewTreeObserver.isAlive) {
                    holder.llItems.viewTreeObserver.removeOnGlobalLayoutListener(holder.listener)
                }
            }
        }

        observer.addOnGlobalLayoutListener(holder.listener)
    }

    // RecyclerView.Adapter Functions

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_multilevel, parent, false)

        val holder = MyViewHolder(view)

        setupGlobalLayoutListener(holder)

        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // Header

        holder.tvTitle.text = myDataset[position].title

        // Has Items

        if(myDataset[position].items!!.isNotEmpty()) {

            holder.isOpen = !myDataset[position].isOpen

            holder.ivExpandIcon.visibility = View.VISIBLE

            setupRv(holder, position)
            holder.llHeader.setOnClickListener {
                toggleContents(holder)
                myDataset[position].isOpen = holder.isOpen
            }

        }

    }

    override fun getItemCount() = myDataset.size
}