package com.example.henry.gwenthelpmate.Containers

import java.io.Serializable

class GwentCard(var cardName: String, val cardGroup: String, val cardRarity: String, val cardPosition: String, val cardType: String,
                val cardEffects: String, val cardFlavorText: String, val cardStrength: String, val cardURL: String, val cardFaction: String) :Serializable{

    /*
    var cardName: String? = null
    var cardGroup: String? = null
    var cardRarity: String? = null
    var cardPosition: String? = null
    var cardType: String? = null
    var cardEffects: String? = null
    var cardFlavorText: String? = null
    var cardStrength: String? = null
    var cardURL: String? = null
    */
}