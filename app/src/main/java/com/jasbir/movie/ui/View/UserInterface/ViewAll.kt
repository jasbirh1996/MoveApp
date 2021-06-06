package com.jasbir.movie.ui.View.UserInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.GridLayoutManager
import com.creativaties.modelfactory.Networking.Networking
import com.jasbir.movie.data.dataSourcepaging.ViewmorePaging
import com.jasbir.movie.R
import com.jasbir.movie.ui.View.Adapter.ViewMoreAdapter
import kotlinx.android.synthetic.main.activity_view_all.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewAll : AppCompatActivity() {
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)

        setupAdapter()
        backarrow.setOnClickListener {
            onBackPressed()
        }
    }

    @InternalCoroutinesApi
    private fun setupAdapter() {

        var viewAdapter = ViewMoreAdapter()
        rv_viewmore.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = viewAdapter
        }

        var data = Pager(PagingConfig(pageSize = 10)) {
            ViewmorePaging(Networking.create(), this)
        }.flow

        lifecycleScope.launch {
            data.collect {
                viewAdapter.submitData(it)
            }
        }


    }

}