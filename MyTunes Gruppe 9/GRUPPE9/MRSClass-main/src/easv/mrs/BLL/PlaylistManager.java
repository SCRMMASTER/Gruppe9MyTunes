package easv.mrs.BLL;

import easv.mrs.BE.Playlist;
import easv.mrs.BLL.util.PlaylistSearcher;
import easv.mrs.DAL.MyTunesDataAccess;
import easv.mrs.DAL.MyTunesPlaylistAccess;
import easv.mrs.DAL.db.PlaylistDAO_DB;

import java.util.List;

public class PlaylistManager {
    private PlaylistSearcher playlistSearcher = new PlaylistSearcher();

    private MyTunesPlaylistAccess playlistDAO;

    public PlaylistManager() {
        playlistDAO = new PlaylistDAO_DB();
    }

    public List<Playlist> getAllSongsPl() throws Exception {
        return playlistDAO.getAllSongsPl();
    }

    public List<Playlist> searchSongsPl(String query) throws Exception {
        List<Playlist> allSongsPl = getAllSongsPl();
        List<Playlist> searchResult = playlistSearcher.search(allSongsPl, query);
        return searchResult;
    }

    public Playlist createNewPlaylist(String artist, String songtitle, float duration, String filepath) throws Exception {
        return playlistDAO.createPlaylist(artist, songtitle, duration, filepath);
    }

    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        playlistDAO.updatePlaylist(updatedPlaylist);
    }
}
