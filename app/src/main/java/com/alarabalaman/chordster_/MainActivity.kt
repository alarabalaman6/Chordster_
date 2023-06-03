package com.alarabalaman.chordster_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.alarabalaman.chordster_.R
import com.alarabalaman.chordster_.databinding.ActivityMainBinding
import org.json.JSONArray
import java.io.IOException
import com.alarabalaman.chordster_.JsonParser

class MainActivity : AppCompatActivity() {

    private val selectedChords: MutableList<String> = mutableListOf()

    private lateinit var binding: ActivityMainBinding

    private lateinit var songs: List<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Setting up CheckBox listeners
        setupCheckBox(binding.chkA, "A")
        setupCheckBox(binding.chkAm, "Am")
        setupCheckBox(binding.chkAm7, "Am7")
        setupCheckBox(binding.chkB, "B")
        setupCheckBox(binding.chkBm, "Bm")
        setupCheckBox(binding.chkC, "C")
        setupCheckBox(binding.chkD, "D")
        setupCheckBox(binding.chkDm, "Dm")
        setupCheckBox(binding.chkE, "E")
        setupCheckBox(binding.chkEm, "Em")
        setupCheckBox(binding.chkF, "F")
        setupCheckBox(binding.chkF7, "F7")
        setupCheckBox(binding.chkG, "G")
        setupCheckBox(binding.chkG7, "G7")

        songs = JsonParser.parseSongs(this)

        binding.buttonProceed.setOnClickListener {
            // Get the filtered songs based on the selected chords
            val filteredSongs = getSongsWithChords(selectedChords)
            showSongs(filteredSongs) // pass the filtered songs
        }

        val buttonSeeAllSongs: Button = findViewById(R.id.buttonSeeAllSongs)
        buttonSeeAllSongs.setOnClickListener {
            showSongs(songs) // pass all songs to the showSongs function
        }
    }

    private fun setupCheckBox(checkBox: CheckBox, chord: String) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedChords.add(chord)
            } else {
                selectedChords.remove(chord)
            }
        }
    }

    fun getSongsWithChords(chords: List<String>): List<Song> {
        val filteredSongs = mutableListOf<Song>()

        for (song in songs) {
            val songChords = song.chords.split(",")
            var allChordsPresent = true

            for (songChord in songChords) {
                if (songChord !in chords) {
                    allChordsPresent = false
                    break
                }
            }

            if (allChordsPresent) {
                filteredSongs.add(song)
            }
        }

        return filteredSongs
    }
    fun showSongs(songs: List<Song>) {

        // Sort the songs alphabetically
        val sortedSongs = songs.sortedBy { it.name }

        // Start the new screen and pass the songs
        val intent = Intent(this, FilteredSongsActivity::class.java)
        intent.putParcelableArrayListExtra("songs", ArrayList(songs))
        startActivity(intent)
    }

    }



