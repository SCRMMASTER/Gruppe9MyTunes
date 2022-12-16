package easv.mrs.DAL;

import easv.mrs.BE.Playlist;

import java.util.List;

public interface MyTunesPlaylistAccess {
    public List<Playlist> getAllSongsPl() throws Exception;
    public Playlist createPlaylist(int nbrOfTracks, String playlistTitle) throws Exception;
    public void updatePlaylist(Playlist playlist) throws Exception;

    public void deletePlaylist(Playlist playlist) throws Exception;
}