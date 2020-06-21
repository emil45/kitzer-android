package com.st.kitzer.models

import org.jsoup.nodes.Element

class MakoParser(newsSite: NewsSite) :
    NewsSiteParser(newsSite) {

    override fun getArticles(): ArrayList<Article> {

        val articles = ArrayList<Article>()
        val doc = AsyncHtmlFetcherFromURL().execute(newsSite.url).get()

        doc.select(".main1 li").forEach { element ->
            val article = Article(newsSite = newsSite)
            article.title = element.getElementsByAttributeValue("data-type", "title").text()
            if (article.title.isNotEmpty()) {
                article.subtitle =
                    element.getElementsByAttributeValue("data-type", "subtitle").text()
                article.date = element.select("small span").getOrNull(1)?.text()
                articles.add(article)
            }
        }

        return articles
    }
}

class BholParser(newsSite: NewsSite) :
    NewsSiteParser(newsSite) {

    override fun getArticles(): ArrayList<Article> {
        val articles = ArrayList<Article>()
        val doc = AsyncHtmlFetcherFromURL().execute(newsSite.url).get()
        var article = Article(newsSite = newsSite)

        article.title = doc.select(".video-title").text()
        article.subtitle = doc.select(".video-caption h1").text()
        articles.add(article)

        doc.select(".widget-title").forEach { element ->
            article = Article(newsSite = newsSite)
            article.title = element.text()
            var subtitleElement: Element = element.nextElementSibling()
            while (subtitleElement.tagName() != "figcaption" || subtitleElement == null) {
                subtitleElement = subtitleElement.nextElementSibling()
            }
            article.subtitle = subtitleElement.text()
            article.newsSite = newsSite
            articles.add(article)
        }

        return articles
    }
}

//class YnetParser(newsSite: NewsSite) : NewsSiteParser(newsSite) {
//    override fun getArticles(): ArrayList<Article> {
//        val articles = ArrayList<Article>()
//        val doc = AsyncHtmlFetcherFromURL().execute(newsSite.url).get()
//
//        doc.select(".area.content").select()
//    }
//}