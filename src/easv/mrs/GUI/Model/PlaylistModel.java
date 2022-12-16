package easv.mrs.GUI.Model;

import easv.mrs.BE.Playlist;
import easv.mrs.BE.Song;
import easv.mrs.BLL.PlaylistManager;
import easv.mrs.BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class PlaylistModel {
    private ObservableList<Playlist> playlistsToBeHeard;
    private PlaylistManager playlistManager;
    private Playlist selectedPlaylist;

    public PlaylistModel() throws Exception
    {
        playlistManager = new PlaylistManager();
        playlistsToBeHeard = FXCollections.observableArrayList();
        playlistsToBeHeard.addAll(playlistManager.getAllSongsPl());
    }

    public ObservableList<Playlist> getObservablePlaylists()
    {
        return playlistsToBeHeard;
    }

    public void searchSong(String query) throws Exception
    {
        List<Playlist> searchResults = playlistManager.searchSongsPl(query);
        playlistsToBeHeard.clear();
        playlistsToBeHeard.addAll(searchResults);
    }

    public void createNewPlaylist(int nbrOfTracks, String playlistTitle, int id) throws Exception
    {
        // Create Song in data storage
        Playlist p = playlistManager.createNewPlaylist(nbrOfTracks, playlistTitle);

        // Add song to observable list (gui)
        playlistsToBeHeard.add(p);
    }

    public void updatePlaylist(Playlist updatedPlaylist) throws Exception
    {
        // Call BLL
        // update song in DB
        playlistManager.updatePlaylist(updatedPlaylist);

        // update ListView
        playlistsToBeHeard.clear();
        playlistsToBeHeard.addAll(playlistManager.getAllSongsPl());
    }

    public Playlist getSelectedPlaylist()
    {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(Playlist selectedPlaylist)
    {
        this.selectedPlaylist = selectedPlaylist;
    }
}
