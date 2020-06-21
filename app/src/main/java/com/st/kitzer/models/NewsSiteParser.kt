package com.st.kitzer.models

abstract class NewsSiteParser(val newsSite: NewsSite) {
    abstract fun getArticles(): ArrayList<Article>
}