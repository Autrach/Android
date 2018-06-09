package com.example.henry.gwenthelpmate.Containers

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.henry.gwenthelpmate.Adapters.DeckTabAdapter
import com.example.henry.gwenthelpmate.Adapters.NewsAdapter
import com.example.henry.gwenthelpmate.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.news_tab.*
import java.io.File
import java.io.InputStream

/**
 * Created by henry on 3/7/18.
 */
class TabNews : Fragment() {
    public var listOfNews: ArrayList<News>?=null
    var adapter: NewsAdapter?= null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater!!.inflate(R.layout.news_tab, container, false)
        getFromFirebase()
        return rootView
    }

    fun getFromFirebase(){
        var storage: FirebaseStorage = FirebaseStorage.getInstance()
        var storageRef: StorageReference = storage.getReferenceFromUrl("gs://gewnt-helpmate.appspot.com/NewsJson.json")

        var downloadedJson: File = File.createTempFile("Json", "json")
        storageRef.getFile(downloadedJson).addOnSuccessListener { taskSnapshot ->
            val inputStream: InputStream = downloadedJson.inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            val gson = Gson()
            listOfNews = gson.fromJson(inputString, object : TypeToken<ArrayList<News>>() {}.type)
            adapter = NewsAdapter(listOfNews!!, activity)
            layoutManager = LinearLayoutManager(activity)

            newsRecyclerView.layoutManager = layoutManager
            newsRecyclerView.adapter=adapter
            //  new    layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
            //    adapter = MainListAdapter(listOfCards!!, this )
            //  MainRecyclerView.layoutManager = layoutManager
            //MainRecyclerView.adapter = adapter
        }
                .addOnFailureListener { exception ->
                    Toast.makeText(activity, ":(", Toast.LENGTH_LONG).show()
                }

    }
}