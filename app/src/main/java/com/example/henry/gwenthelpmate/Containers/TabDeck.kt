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
import com.example.henry.gwenthelpmate.R
import com.example.henry.gwenthelpmate.R.id.recyclerViewDeck
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.deck_tab.*
import java.io.File
import java.io.InputStream

/**
 * Created by henry on 3/7/18.
 */
class TabDeck : Fragment() {
    public var listOfDecks: ArrayList<GwentDeck>?= null
    public var listOfCards: ArrayList<GwentCard>?=null
    var adapter: DeckTabAdapter?= null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater!!.inflate(R.layout.deck_tab, container, false)
        getFromFirebase()
        return rootView
    }

    fun getFromFirebase(){
        var storage: FirebaseStorage = FirebaseStorage.getInstance()
        var storageRef: StorageReference = storage.getReferenceFromUrl("gs://gewnt-helpmate.appspot.com/JSON.json")

        var downloadedJson: File = File.createTempFile("Json", "json")
        storageRef.getFile(downloadedJson).addOnSuccessListener { taskSnapshot ->
            val inputStream: InputStream = downloadedJson.inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            val gson = Gson()
            listOfCards = gson.fromJson(inputString, object : TypeToken<ArrayList<GwentCard>>() {}.type)
            adapter = DeckTabAdapter(listOfCards!!, activity)
            layoutManager = LinearLayoutManager(activity)

            recyclerViewDeck.layoutManager = layoutManager
            recyclerViewDeck.adapter=adapter
            //      layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
            //    adapter = MainListAdapter(listOfCards!!, this )
            //  MainRecyclerView.layoutManager = layoutManager
            //MainRecyclerView.adapter = adapter
        }
                .addOnFailureListener { exception ->
                    Toast.makeText(activity, ":(", Toast.LENGTH_LONG).show()
                }

    }
    fun start () {
        listOfDecks = ArrayList<GwentDeck>()
        layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        //adapter = DeckTabAdapter(listOfDecks!!,activity)
        recyclerViewDeck.layoutManager = layoutManager
        recyclerViewDeck.adapter=adapter


        for (i in 0..16){
            var talia = GwentDeck("Talia"+ i, "fdsafds " +i , "https://www.jqueryscript.net/images/Simplest-Responsive-jQuery-Image-Lightbox-Plugin-simple-lightbox.jpg")
            listOfDecks!!.add(talia)
        }
        adapter!!.notifyDataSetChanged()
        //      layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        //    adapter = MainListAdapter(listOfCards!!, this )
        //  MainRecyclerView.layoutManager = layoutManager
        //MainRecyclerView.adapter = adapter
    }
        //var storage: FirebaseStorage = FirebaseStorage.getInstance()
        //listOfDecks!!.add(GwentDeck("Talia", "Super Talia", "https://www.codeproject.com/KB/GDI-plus/ImageProcessing2/img.jpg"))
        //listOfDecks!!.add(GwentDeck("Talia2", "Super Talia2", "https://www.codeproject.com/KB/GDI-plus/ImageProcessing2/img.jpg"))
        //listOfDecks!!.add(GwentDeck("Talia3", "Super Talia3", "https://www.codeproject.com/KB/GDI-plus/ImageProcessing2/img.jpg"))
        //layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        //adapter = DeckTabAdapter(listOfCards!!,activity)
        //recyclerViewDeck.adapter=adapter
        //recyclerViewDeck.layoutManager = layoutManager
        //      layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        //    adapter = MainListAdapter(listOfCards!!, this )
        //  MainRecyclerView.layoutManager = layoutManager
        //MainRecyclerView.adapter = adapter
    }
