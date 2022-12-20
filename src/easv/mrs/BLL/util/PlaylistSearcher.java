/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.BLL.util;

import easv.mrs.BE.Playlist;
import java.util.ArrayList;
import java.util.List;

//Create the song searcher class.
public class PlaylistSearcher {
    public List<Playlist> search(List<Playlist> searchBase, String query)
    {
        List<Playlist> searchResult = new ArrayList<>();

        for (Playlist playlist : searchBase) {
            if(compareToPlaylistArtist(query, playlist) || compareToPlaylistSongTitle(query, playlist))
            {
                searchResult.add(playlist);
            }
        }
        return searchResult;
    }

    //Sort input to Artist name.
    private boolean compareToPlaylistArtist(String query, Playlist playlist)
    {
        return playlist.getPlaylistTitle().toLowerCase().contains(query.toLowerCase());
    }

    //Sort input to album name.
    private boolean compareToPlaylistSongTitle(String query, Playlist playlist)
    {
        return playlist.getPlaylistTitle().toLowerCase().contains(query.toLowerCase());
    }
}
