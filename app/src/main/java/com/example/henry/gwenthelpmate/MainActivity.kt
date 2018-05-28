package com.example.henry.gwenthelpmate


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.InputStream
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.widget.GridView
import com.example.henry.gwenthelpmate.Containers.GwentCard
import com.example.henry.gwenthelpmate.Containers.TabCard
import com.example.henry.gwenthelpmate.Containers.TabDeck
import com.example.henry.gwenthelpmate.Containers.TabNews
import com.google.firebase.storage.FileDownloadTask
import com.google.android.gms.tasks.OnSuccessListener
import java.io.IOException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {
    //private var adapter: MainListAdapter? = null
    public var listOfCards: ArrayList<GwentCard>?=null
    lateinit var gv: GridView
    var adapter: GridAdapter ?= null
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    //private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFromFirebase()
        mainRegisterButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        mainLoginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            when(position){
                0-> {
                    var tab1: TabCard = TabCard()
                    return tab1
                }
                1-> {
                    var tab2: TabDeck = TabDeck()
                    return tab2
                }
                2-> {
                    var tab3: TabNews = TabNews()
                    return tab3
                }
                else -> {
                    return null
                }
            }
        }
        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }
    fun getFromFirebase(){
        var storage: FirebaseStorage = FirebaseStorage.getInstance()
        var storageRef: StorageReference = storage.getReferenceFromUrl("gs://gewnt-helpmate.appspot.com/JSON.json")

        var downloadedJson: File = File.createTempFile("Json2", "json")
        storageRef.getFile(downloadedJson).addOnSuccessListener { taskSnapshot ->
            val inputStream: InputStream = downloadedJson.inputStream()
            val inputString = inputStream.bufferedReader().use { it.readText() }
            val gson = Gson()
            listOfCards = gson.fromJson(inputString, object : TypeToken<ArrayList<GwentCard>>() {}.type)
            gv = findViewById(R.id.gridview)
            adapter = GridAdapter(this, listOfCards!!)
            gv.adapter = adapter
      //      layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        //    adapter = MainListAdapter(listOfCards!!, this )
          //  MainRecyclerView.layoutManager = layoutManager
            //MainRecyclerView.adapter = adapter
        }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, ":(", Toast.LENGTH_LONG).show()
                }

    }
}
