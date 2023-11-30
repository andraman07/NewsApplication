package com.example.newsapplication1.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication1.R
import com.example.newsapplication1.models.Articles
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class ContentAdapter(private val context: Context, private val newsList:List<Articles>)
    : RecyclerView.Adapter<ContentAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val newsTitle: TextView =view.findViewById(R.id.title)
        val newsDescription: TextView =view.findViewById(R.id.description)
        val newsImage: ImageView =view.findViewById(R.id.image)
        val publisherName: TextView =view.findViewById(R.id.publisher_name)
        val authorName: TextView =view.findViewById(R.id.author_name)
        val publishTime: TextView =view.findViewById(R.id.publish_time)
        val contentCard: CardView =view.findViewById(R.id.content_card)
        val shareBtn: ImageButton =view.findViewById(R.id.share_news_btn)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val article: Articles =newsList[position]

        holder.newsTitle.text=article.title ?: ""
        holder.newsDescription.text=article.description ?: ""
        holder.publisherName.text= article.source.name ?: ""
        holder.authorName.text=article.author ?: ""

        holder.shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, article.url)
            context.startActivity(Intent.createChooser(intent, "Share via"))
        }


        holder.publishTime.text= article.publishedAt?.let { calculateNewsPublishTime(it) }

        holder.contentCard.setOnClickListener {
            val intent= Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(article.url)
            context.startActivity(intent)
        }

        if(!article.urlToImage.isNullOrEmpty()) {

            try {
                Glide.with(context)
                    .load(article.urlToImage)
                    .into(holder.newsImage)

            }catch (e:java.lang.Exception){
                Log.d("Glide failed",e.message.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val contentObject= LayoutInflater.from(parent.context)
            .inflate(R.layout.content_item_view,parent,false)
        return ItemViewHolder(contentObject)
    }


    override fun getItemCount(): Int=newsList.size

    private fun calculateNewsPublishTime(publishedTime: String):String {

        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            val publishedAt = dateFormat.parse(publishedTime)

// Calculate the time elapsed since the article was published
            val currentTimeMillis = System.currentTimeMillis()
            val publishedAtMillis = publishedAt?.time
            val elapsedMillis = currentTimeMillis - publishedAtMillis!!

// Display the update time in a user-friendly format
            val elapsedSeconds=elapsedMillis/1000
            val elapsedMinutes = elapsedSeconds/60
            val elapsedHours= elapsedMinutes/60
            val elapsedDays=elapsedHours/24

            return if(elapsedSeconds<60)
                "$elapsedSeconds seconds ago"
            else if(elapsedMinutes<60)
                "$elapsedMinutes minutes ago"
            else if (elapsedHours<24)
                "$elapsedHours hours ago"
            else
                "$elapsedDays days ago"


        }catch (e:java.lang.Exception){
            Log.d("DateTimeException",e.message.toString())
        }
        return ""
    }



}