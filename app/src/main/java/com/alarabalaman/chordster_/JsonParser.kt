package com.alarabalaman.chordster_

import android.content.Context
import android.util.Log
import org.json.JSONArray

class JsonParser {

    companion object {
        fun parseSongs(context: Context): List<Song> {
            // Read JSON file from assets
            val jsonString = context.assets.open("songs.json").bufferedReader().use { it.readText() }


            // Initialize an empty list to hold the songs
            val songs = mutableListOf<Song>()

            try {

                val jsonArray = JSONArray(jsonString)

                for (i in 0 until jsonArray.length()) {

                    val jsonObject = jsonArray.getJSONObject(i)

                    // Generate an auto-incrementing ID
                    val id = i + 1

                    val name = jsonObject.getString("name")
                    val chords = jsonObject.getString("chords")
                    val chordSheet = context.resources.getIdentifier(jsonObject.getString("chordSheet"), "drawable", context.packageName)

                    // Create a new Song object and add it to the list
                    val song = Song(id, name, chords, chordSheet)
                    songs.add(song)

                }


            }catch (e: Exception) {
                e.printStackTrace()
            }

            return songs
        }

    } }
