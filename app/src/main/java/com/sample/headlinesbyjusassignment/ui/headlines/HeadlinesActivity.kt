package com.sample.headlinesbyjusassignment.ui.headlines

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sample.headlinesbyjusassignment.databinding.ActivityMainBinding
import com.sample.headlinesbyjusassignment.model.Article
import com.sample.headlinesbyjusassignment.model.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class HeadlinesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Article>()
    @InternalCoroutinesApi
    private val viewModel by viewModels<HeadlinesViewModel>()
    private lateinit var headlinesAdapter: HeadlinesAdapter

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        subscribeHeadlines()
    }

    private fun init() {
        rvHeadlines.layoutManager = LinearLayoutManager(this)
        headlinesAdapter = HeadlinesAdapter(this, list)
        rvHeadlines.adapter = headlinesAdapter
    }

    @InternalCoroutinesApi
    private fun subscribeHeadlines() {
        viewModel.headlinesList.observe(
            this,
            Observer { result ->

                when (result.status) {
                    Result.Status.SUCCESS -> {
                        result.data?.articles?.let { list ->
                            headlinesAdapter.updateData(list)
                        }
                        loading.visibility = View.GONE
                    }

                    Result.Status.ERROR -> {
                        result.message?.let {
                            showError(it)
                        }
                        loading.visibility = View.GONE
                    }

                    Result.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                }
            }
        )
    }

    private fun showError(msg: String) {
        Snackbar.make(vParent, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }
}
