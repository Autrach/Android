package com.example.henry.gwenthelpmate.Containers

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.henry.gwenthelpmate.R

/**
 * Created by henry on 3/7/18.
 */
class TabDeck : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater!!.inflate(R.layout.deck_tab, container, false)
        return rootView
    }
}