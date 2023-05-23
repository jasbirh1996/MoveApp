package com.jasbir.movieapp.ui.View.UserInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.GridLayoutManager
import com.creativaties.modelfactory.Networking.Networking
import com.jasbir.movieapp.data.dataSourcepaging.ViewmorePaging
import com.jasbir.movieapp.R
import com.jasbir.movieapp.databinding.ActivityViewAllBinding
import com.jasbir.movieapp.ui.View.Adapter.ViewMoreAdapter
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewAll : AppCompatActivity() {

    private lateinit var binding : ActivityViewAllBinding

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        binding.backarrow.setOnClickListener {
            onBackPressed()
        }
    }

    @InternalCoroutinesApi
    private fun setupAdapter() {

        var viewAdapter = ViewMoreAdapter()
       binding.rvViewmore.apply {
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