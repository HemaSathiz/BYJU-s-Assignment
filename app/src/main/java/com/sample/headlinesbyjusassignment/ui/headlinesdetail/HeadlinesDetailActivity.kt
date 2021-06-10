package com.sample.headlinesbyjusassignment.ui.headlinesdetail

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.headlinesbyjusassignment.R
import com.sample.headlinesbyjusassignment.databinding.ActivityDetailBinding
import com.sample.headlinesbyjusassignment.model.Article
import com.sample.headlinesbyjusassignment.util.ConstantHelper
import com.sample.headlinesbyjusassignment.util.DateFormatter

class HeadlinesDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        var article: Article =
            intent?.getParcelableExtra<Article>(ConstantHelper.HEADLINES_DETAIL)!!
        article.let { detail ->
            Glide.with(this).load(article.urlToImage)
                .apply(
                    RequestOptions().centerInside()
                        .placeholder(R.drawable.ic_launcher_background)
                ).into(binding.ivPoster)
            binding.txtContent.text = detail.description
            binding.txtChannel.text = detail.source?.name
            binding.txtTitle.text = detail.title
            binding.txtDate.text = DateFormatter.dateFormatter(article.publishedAt!!)
        }

        binding.imgBackArrow.setOnClickListener { finish() }
    }
}
