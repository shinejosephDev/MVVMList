package com.test.mvvmlist.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.loadimage.LoadImage
import com.test.loadimage.cache.DoubleCache
import com.test.mvvmlist.databinding.ActivityMainBinding
import com.test.mvvmlist.domain.model.ResultData
import com.test.mvvmlist.presentation.adapter.ItemsAdapter
import com.test.mvvmlist.presentation.viewmodel.MainViewModel
import com.test.mvvmlist.util.Constants.INTENT_KEY_DETAIL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //setup adapter and image cache
        mAdapter = ItemsAdapter()
        LoadImage.setCache(DoubleCache(applicationContext))

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }

        //setup recyclerview item click
        mAdapter.onItemClick = { resultsItem ->
            startActivity(
                Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(INTENT_KEY_DETAIL, resultsItem)
                }
            )
        }

        //Observe in livedata for response and update list based data
        viewModel.response.observe(this, {
            when (it) {
                is ResultData.Success -> {
                    binding.progressBar.visibility = View.GONE
                    mAdapter.submitList(it.data?.results)
                }
                is ResultData.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
                is ResultData.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        LoadImage.clearCache()
    }
}