/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.BLL;

import easv.mrs.BE.Playlist;
import easv.mrs.BLL.util.PlaylistSearcher;
import easv.mrs.DAL.MyTunesDataAccess;
import easv.mrs.DAL.MyTunesPlaylistAccess;
import easv.mrs.DAL.db.PlaylistDAO_DB;
import java.util.List;
//Create the class Playlistmanager
public class PlaylistManager {
    private PlaylistSearcher playlistSearcher = new PlaylistSearcher();
    private MyTunesPlaylistAccess playlistDAO_DB;

    public PlaylistManager() {

        playlistDAO_DB = new PlaylistDAO_DB();
    }


    //Gets all the songs on the playlist.

    public List<Playlist> getAllSongsPl() throws Exception
    {
        return playlistDAO_DB.getAllSongsPl();
    }

    //Search the playlist.

    public List<Playlist> searchSongsPl(String query) throws Exception
    {
        List<Playlist> allSongsPl = getAllSongsPl();
        List<Playlist> searchResult = playlistSearcher.search(allSongsPl, query);
        return searchResult;

    }
    //Sends information to create a new playlist

    public Playlist createNewPlaylist(int id, String playlistTitle) throws Exception
    {
        return playlistDAO_DB.createPlaylist(id, playlistTitle);
    }

    //Sends the information to Rename a Playlist.

    public void renamePlaylist(Playlist selectedPlaylist) throws Exception
    {
        playlistDAO_DB.renamePlaylist(selectedPlaylist);
    }

    //Sends the information to Delete a playlist.

    public void deletePlaylist(Playlist selectedPlaylist) throws Exception {

        playlistDAO_DB.deletePlaylist(selectedPlaylist);
    }
}
