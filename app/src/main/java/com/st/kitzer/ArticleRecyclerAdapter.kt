package com.st.kitzer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.st.kitzer.models.Article

class ArticleRecyclerAdapter(private val context: Context, private val articles: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val articleTitle = itemView?.findViewById<TextView?>(R.id.articleTitle)
        val articleContent = itemView?.findViewById<TextView?>(R.id.articleSubtitle)
        val articleNewsSite = itemView?.findViewById<TextView?>(R.id.articleNewsSite)
        val articleDate = itemView?.findViewById<TextView?>(R.id.articleDate)
        val articleNewsSiteIcon = itemView?.findViewById<ImageView?>(R.id.articleNewsSiteIcon)
        var articlePosition = 0

//        init {
//            itemView?.setOnClickListener {
//                val intent = Intent(context, NoteActivity::class.java)
//                intent.putExtra(NOTE_POSITION, notePosition)
//                context.startActivity(intent)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_article_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]

        holder.articleTitle?.text = article.title
        holder.articleContent?.text = article.subtitle
        holder.articleNewsSite?.text = article.newsSite.name
        if (article.date.isNullOrEmpty())
            holder.itemView.findViewById<TextView>(R.id.siteNameSeparator).visibility = TextView.GONE
        else
            holder.itemView.findViewById<TextView>(R.id.siteNameSeparator).visibility = TextView.VISIBLE
        holder.articleDate?.text = article.date
        if (article.newsSite.encodedName == "bhol")
            holder.articleNewsSiteIcon?.setImageResource(R.drawable.bhol)
        if (article.newsSite.encodedName == "mako")
            holder.articleNewsSiteIcon?.setImageResource(R.drawable.mako)
        holder.articlePosition = position
    }
}
