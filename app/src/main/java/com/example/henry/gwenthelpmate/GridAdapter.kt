package com.example.henry.gwenthelpmate

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.henry.gwenthelpmate.Containers.GwentCard
import com.squareup.picasso.Picasso

/**
 * Created by henry on 24/02/18.
 */
class GridAdapter(private val context: Context, private val listOfCards: ArrayList<GwentCard>) : BaseAdapter() {
    private var inflator: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        var holder: Holder = Holder()
        var rv: View = inflator.inflate(R.layout.custom_grid, null)
        holder.textViewH= rv.findViewById(R.id.textViewGrid)
        holder.imageViewH = rv.findViewById(R.id.imageViewGrid)
        holder.textViewH.setText(listOfCards[position].cardName)
        Picasso.with(context).load(listOfCards[position].cardURL).into(holder.imageViewH)
        rv.setOnClickListener {
            //Toast.makeText(context, holder.textViewH.text.toString(), Toast.LENGTH_LONG).show()
            //var intent: Intent = Intent(context, CardActivity::class.java)
            var intent: Intent = Intent(context, CardActivity::class.java)
            intent.putExtra("Card", listOfCards[position])
            context.startActivity(intent)
        }
        return rv
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listOfCards.size
    }
    public class Holder
    {
        lateinit var textViewH: TextView
        lateinit var imageViewH: ImageView
    }
}