package com.gmail.sas.newsdigitalnomads.presentation.screen.wallNews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.sas.newsdigitalnomads.R
import com.gmail.sas.newsdigitalnomads.presentation.extensions.loadGlideImgUrl
import com.gmail.sas.newsdigitalnomads.presentation.model.WallItem
import com.gmail.sas.newsdigitalnomads.presentation.model.WallNewsItem
import kotlinx.android.synthetic.main.recycler_item_news.view.*
import kotlinx.android.synthetic.main.recycler_item_repeat_button.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class WallAdapter(private val onLoadNews: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var onClickNews: (NewsHolder) -> Unit
    private lateinit var onClickRepeatButton: (RepeatButtonHolder) -> Unit

    private companion object {
        const val NEWS_POST = 1
        const val REPEAT_BUTTON = 2
        const val LOADING_BAR = 3
    }

    private var listItems = mutableListOf<WallItem>()
    private var isRepeat = false
    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            NEWS_POST -> NewsHolder.createItemInstance(parent)
            REPEAT_BUTTON -> RepeatButtonHolder.createItemInstance(parent)
            LOADING_BAR -> LoadingHolder.createItemInstance(parent)

            else -> RepeatButtonHolder.createItemInstance(parent)
        }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (listItems.size - position == 5) {
            onLoadNews()
        }

        when (holder) {
            is NewsHolder -> {
                holder.bindView(listItems[position] as WallNewsItem)
                initListeners(holder)
            }
            is RepeatButtonHolder -> {
                initListeners(holder)
            }
            is LoadingHolder -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == listItems.size - 1 && isRepeat && !isLoading) {
            REPEAT_BUTTON
        } else if (position == listItems.size - 1 && isLoading && !isRepeat) {
            LOADING_BAR
        } else {
            NEWS_POST
        }
    }


    fun addOnClickNews(consumer: (NewsHolder) -> Unit) {
        onClickNews = consumer
    }

    fun addOnClickRepeat(consumer: (RepeatButtonHolder) -> Unit) {
        onClickRepeatButton = consumer
    }

    fun showPageProgressBar() {
        isLoading = true
        isRepeat = false

    }

    fun showRepeatButton() {
        isLoading = false
        isRepeat = true
        notifyDataSetChanged()
    }

    fun setItems(listItems: List<WallItem>) {
        isLoading = false
        isRepeat = false
        this.listItems.addAll(listItems)
        notifyDataSetChanged()
    }

    fun getLinkToNews(position: Int): String? =

        when (val wallItem = listItems[position]) {
            is WallNewsItem -> {
                wallItem.linkToNews
            }
            else -> ""
        }

    fun clearList() {
        listItems.clear()
    }

    private fun initListeners(holder: RecyclerView.ViewHolder) {
        when (holder) {
            is NewsHolder -> {
                if (::onClickNews.isInitialized) {
                    holder.addOnClickListener(onClickNews)
                }
            }
            is RepeatButtonHolder -> {
                if (::onClickRepeatButton.isInitialized) {
                    holder.addOnClickListener(onClickRepeatButton)
                }
            }
        }

    }
}

class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun createItemInstance(parent: ViewGroup) = NewsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item_news,
                parent, false
            )
        )
    }

    fun bindView(news: WallNewsItem) {
        itemView.itemTitle.text = news.title
        itemView.itemDate.text = bindDateToString(news.datePublished)
        itemView.itemDesc.text = news.desc
        news.urlImage?.let { itemView.itemImage.loadGlideImgUrl(it) }
    }

    fun addOnClickListener(consumer: (NewsHolder) -> Unit) {
        itemView.setOnClickListener { consumer(this) }
    }

    private fun bindDateToString(date: Date): String = SimpleDateFormat.getDateInstance(
        DateFormat.LONG,
        Locale.getDefault()
    ).format(date)

}

class RepeatButtonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun createItemInstance(parent: ViewGroup) = RepeatButtonHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item_repeat_button,
                parent, false
            )
        )
    }

    fun addOnClickListener(consumer: (RepeatButtonHolder) -> Unit) {
        itemView.wallRepeatButton.setOnClickListener { consumer(this) }
    }
}

class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    companion object {
        fun createItemInstance(parent: ViewGroup) = LoadingHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item_progress_page,
                parent, false
            )
        )
    }

}