package com.example.henry.gwenthelpmate.Containers

/**
 * Created by henry on 3/7/18.
 */
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import com.example.henry.gwenthelpmate.Adapters.GridAdapter
import com.example.henry.gwenthelpmate.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.InputStream

class TabCard : Fragment() {
    public var listOfCards: ArrayList<GwentCard>?=null
    lateinit var gv: GridView
    var adapter: GridAdapter?= null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater!!.inflate(R.layout.card_tab, container, false)
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
            gv = activity.findViewById(R.id.gridview)
            adapter = GridAdapter(activity, listOfCards!!)
            gv.adapter = adapter
            //      layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
            //    adapter = MainListAdapter(listOfCards!!, this )
            //  MainRecyclerView.layoutManager = layoutManager
            //MainRecyclerView.adapter = adapter
        }
                .addOnFailureListener { exception ->
                    Toast.makeText(activity, ":(", Toast.LENGTH_LONG).show()
                }

    }
}