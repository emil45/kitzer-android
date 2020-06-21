package com.st.kitzer

import com.st.kitzer.models.Article
import com.st.kitzer.models.NewsSite

object DataManager {
    val articles = ArrayList<Article>()
    val newsSites = HashMap<String, NewsSite>()

    init {
        initNewsSites()
    }

    private fun initNewsSites() {
        newsSites["mako"] = NewsSite("ערוץ 2", "https://www.n12.co.il/", "mako")
        newsSites["bhol"] = NewsSite("בחדרי חרדים","https://www.bhol.co.il/", "bhol")
        newsSites["yet"] = NewsSite("Ynet","https://www.ynet.co.il/", "ynet")
    }
}