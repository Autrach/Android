package com.example.henry.gwenthelpmate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.henry.gwenthelpmate.Containers.GwentCard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_card.*

class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        var card: GwentCard = getIntent().getSerializableExtra("Card") as GwentCard
        Type.setText(card.cardType)
        Faction.setText(card.cardFaction)
        Power.setText(card.cardStrength)
        Rarity.setText(card.cardRarity)
        Row.setText(card.cardPosition)
        CardEffect.setText(card.cardEffects)
        FlavorText.setText(card.cardFlavorText)
        cardNameText.setText(card.cardName)
        Picasso.with(this).load(card.cardURL).into(imageView)
    }
}
