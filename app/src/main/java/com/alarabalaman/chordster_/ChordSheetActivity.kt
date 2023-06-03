package com.alarabalaman.chordster_

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ChordSheetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chord_sheet)

        // Get the song from the intent
        val song = intent.getParcelableExtra<Song>("song")

        // Now you have the song, you can use the chordSheet field to display the chord sheet image.
        // Assuming chordSheet is the resource ID of the drawable image.
        song?.chordSheet?.let { chordSheet ->
            val imageView: ImageView = findViewById(R.id.chordSheetImageView)
            imageView.setImageResource(chordSheet)
        }
    }
}

