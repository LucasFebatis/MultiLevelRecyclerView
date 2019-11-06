package com.febatis.multilevelrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDataset = setupItems()

        viewManager = LinearLayoutManager(this)
        viewAdapter = MainAdapter(myDataset, ContentItemAdapter())

        recyclerView = findViewById<RecyclerView>(R.id.rv_items).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    private fun setupItems(): List<MultiLevelItem> {
        val retorno = ArrayList<MultiLevelItem>()

        val item1 = MultiLevelItem("Item 1", ArrayList(), false)
        item1.items?.add(MultiLevelItem("SubItem 1.1", ArrayList(), false))
        item1.items?.add(MultiLevelItem("SubItem 1.2", ArrayList(), false))
        item1.items?.add(MultiLevelItem("SubItem 1.1", ArrayList(), false))
        item1.items?.add(MultiLevelItem("SubItem 1.2", ArrayList(), true))
        item1.items?.add(MultiLevelItem("SubItem 1.1", ArrayList(), false))
        item1.items?.add(MultiLevelItem("SubItem 1.2", ArrayList(), false))

        val item2 = MultiLevelItem("Item 2", ArrayList(), true)
        item2.items?.add(MultiLevelItem("SubItem 2.1", ArrayList(), false))
        item2.items?.add(MultiLevelItem("SubItem 2.2", ArrayList(), false))

        retorno.add(item1)
        retorno.add(item2)

        return retorno
    }
}
