package com.st.kitzer.models

import android.os.AsyncTask
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class AsyncHtmlFetcherFromURL() : AsyncTask<String, Void, Document>() {
    override fun doInBackground(vararg params: String): Document? {
        return Jsoup.connect(params.first()).get()
    }
}