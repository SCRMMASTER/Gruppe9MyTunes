/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.BLL.util;

import easv.mrs.BE.Song;
import java.util.ArrayList;
import java.util.List;

//Create the song searcher class.
public class SongSearcher {
    public List<Song> search(List<Song> searchBase, String query)
    {
        List<Song> searchResult = new ArrayList<>();

        for (Song song : searchBase) {
            if(compareToSongArtist(query, song) || compareToSongSongTitle(query, song))
            {
                searchResult.add(song);
            }
        }

        return searchResult;
    }
    //Sort input to Artist name.
    private boolean compareToSongArtist(String query, Song song)
    {
        return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }
    //Sort input to album name.
    private boolean compareToSongSongTitle(String query, Song song)
    {
        return song.getSongtitle().toLowerCase().contains(query.toLowerCase());
    }
}
