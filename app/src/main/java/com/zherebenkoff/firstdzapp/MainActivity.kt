package com.zherebenkoff.firstdzapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NumberAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private var itemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)

        // Восстанавливаем состояние при повороте
        if (savedInstanceState != null) {
            itemCount = savedInstanceState.getInt("itemCount", 0)
        }

        setupRecyclerView()
        setupAddButton()
    }

    private fun setupRecyclerView() {
        adapter = NumberAdapter()
        recyclerView.adapter = adapter

        updateLayoutManager()

        // Восстанавливаем элементы
        for (i in 1..itemCount) {
            adapter.addItem()
        }
    }

    private fun setupAddButton() {
        addButton.setOnClickListener {
            adapter.addItem()
            itemCount++

            recyclerView.post {
                val lastPosition = adapter.itemCount - 1
                recyclerView.smoothScrollToPosition(lastPosition)
            }
        }
    }

    private fun updateLayoutManager() {
        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 3

        recyclerView.layoutManager = GridLayoutManager(this, spanCount)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateLayoutManager()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("itemCount", itemCount)
    }
}