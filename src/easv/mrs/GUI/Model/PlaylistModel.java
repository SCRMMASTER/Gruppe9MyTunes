package easv.mrs.GUI.Model;

import easv.mrs.BE.Playlist;
import easv.mrs.BLL.PlaylistManager;
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

    public void createNewPlaylist(String playlistTitle, int id) throws Exception
    {
        // Create Song in data storage
        Playlist p = playlistManager.createNewPlaylist(id, playlistTitle);

        // Add song to observable list (gui)
        playlistsToBeHeard.add(p);
    }

    public void renamePlaylist(Playlist selectedPlaylist) throws Exception
    {
        // Call BLL
        // update song in DB
        playlistManager.renamePlaylist(selectedPlaylist);

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

    public void deletePlaylist(Playlist selectedPlaylist) throws Exception {
        playlistManager.deletePlaylist(selectedPlaylist);

        playlistsToBeHeard.clear();
        playlistsToBeHeard.addAll(playlistManager.getAllSongsPl());
    }
}
