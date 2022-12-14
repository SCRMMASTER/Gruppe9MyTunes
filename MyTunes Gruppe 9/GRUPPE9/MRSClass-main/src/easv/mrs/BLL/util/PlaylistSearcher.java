package easv.mrs.BLL.util;

import easv.mrs.BE.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistSearcher {

    public List<Playlist> search(List<Playlist> searchBase, String query) {
        List<Playlist> searchResult = new ArrayList<>();

        for (Playlist playlist : searchBase) {
            if(compareToPlaylistArtist(query, playlist) || compareToPlaylistSongTitle(query, playlist))
            {
                searchResult.add(playlist);
            }
        }

        return searchResult;
    }
    private boolean compareToPlaylistArtist(String query, Playlist playlist) {
        return playlist.getArtist().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToPlaylistSongTitle(String query, Playlist playlist) {
        return playlist.getSongtitle().toLowerCase().contains(query.toLowerCase());
    }
}
