package com.st.kitzer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.st.kitzer.models.BholParser
import com.st.kitzer.models.MakoParser
import com.st.kitzer.models.NewsSiteParser
import kotlinx.android.synthetic.main.activity_articles_list.*


class MainActivity : AppCompatActivity() {
    private lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeSwipeContainer()

        fetchArticles()

        articlesList.layoutManager = LinearLayoutManager(this)
        articlesList.adapter = ArticleRecyclerAdapter(this, DataManager.articles)
    }

    private fun initializeSwipeContainer() {
        swipeContainer = findViewById(R.id.swipeContainer)
        swipeContainer.setOnRefreshListener {
            // call swipeContainer.setRefreshing(false) once network request completed successfully.
            updateView()
        }
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
    }

    private fun fetchArticles() {
        val parsers = getParsers()
        parsers.forEach { parser ->
            val articles = parser.getArticles()
            DataManager.articles += articles
        }
    }

    private fun getParsers() : ArrayList<NewsSiteParser> {
        val parsers = ArrayList<NewsSiteParser>()
        parsers.add(MakoParser(DataManager.newsSites["mako"]!!))
        parsers.add(BholParser(DataManager.newsSites["bhol"]!!))
        return parsers
    }

    private fun updateView() {
        DataManager.articles.clear()
        fetchArticles()
        articlesList.adapter?.notifyDataSetChanged()
        swipeContainer.isRefreshing = false
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }
}
