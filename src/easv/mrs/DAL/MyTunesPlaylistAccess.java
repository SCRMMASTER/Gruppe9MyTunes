/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.DAL;

import easv.mrs.BE.Playlist;
import java.util.List;

public interface MyTunesPlaylistAccess {
    public List<Playlist> getAllSongsPl() throws Exception;
    public Playlist createPlaylist(int id, String playlistTitle) throws Exception;
    public void renamePlaylist(Playlist playlist) throws Exception;
    public void deletePlaylist(Playlist playlist) throws Exception;
}
