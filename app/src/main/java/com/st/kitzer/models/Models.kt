package com.st.kitzer.models

class Article (var title: String = "",
               var subtitle: String = "",
               var date: String? = "",
               var newsSite: NewsSite)

class NewsSite(var name: String, var url: String, var encodedName: String)