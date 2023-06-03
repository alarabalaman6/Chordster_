package com.alarabalaman.chordster_

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alarabalaman.chordster_.databinding.FragmentDisplaySongsBinding

class FilteredSongsActivity : AppCompatActivity(){

    private lateinit var binding: FragmentDisplaySongsBinding
    private lateinit var adapter: SongAdapter
    private val songList = ArrayList<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDisplaySongsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // using a lambda function to start the ChordSheetActivity
        // when a song is clicked, and passes the song as intent
        adapter = SongAdapter(songList) { song ->
            val intent = Intent(this, ChordSheetActivity::class.java)
            intent.putExtra("song", song)
            startActivity(intent)
        }

        binding.recyclerViewSongs.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSongs.adapter = adapter


        val songs = intent.getParcelableArrayListExtra<Song>("songs")
        if (songs != null) {
            setSongList(songs)
        }



    }


    fun setSongList(songs: List<Song>) {
        songList.clear()

        // Sorting the songs alphabetically
        val sortedSongs = songs.sortedBy { it.name }
        songList.addAll(sortedSongs)
        adapter.notifyDataSetChanged()
    }


}