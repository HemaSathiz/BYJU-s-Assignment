package com.sample.headlinesbyjusassignment.ui.headlines

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.headlinesbyjusassignment.R
import com.sample.headlinesbyjusassignment.model.Article
import com.sample.headlinesbyjusassignment.ui.headlinesdetail.HeadlinesDetailActivity
import com.sample.headlinesbyjusassignment.util.ConstantHelper
import com.sample.headlinesbyjusassignment.util.DateFormatter
import kotlinx.android.synthetic.main.list_item_article.view.*

class HeadlinesAdapter(private val context: Context, private val list: ArrayList<Article>) :
    RecyclerView.Adapter<HeadlinesAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(private val context: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) {
            itemView.setOnClickListener {
                val intent = Intent(context, HeadlinesDetailActivity::class.java)
                intent.putExtra(ConstantHelper.HEADLINES_DETAIL, article)
                context.startActivity(intent)
            }
            itemView.txt_title.text = article.title
            itemView.txt_channel.text = article.source?.name
            itemView.txt_date.text = DateFormatter.dateFormatter(article.publishedAt!!)

            Glide.with(context).load(article.urlToImage)
                .apply(
                    RequestOptions().override(400, 400).centerInside()
                        .placeholder(R.drawable.ic_launcher_background)
                ).into(itemView.ivPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_article, parent, false)
        return ArticleViewHolder(context, view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateData(article: List<Article>) {
        list.clear()
        list.addAll(article)
        notifyDataSetChanged()
    }
}
