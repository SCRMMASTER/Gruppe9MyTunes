package easv.mrs.BLL;

import easv.mrs.BE.Playlist;
import easv.mrs.BE.Song;
import easv.mrs.BLL.util.SongSearcher;
import easv.mrs.DAL.MyTunesDataAccess;
import easv.mrs.DAL.db.SongDAO_DB;
import java.util.List;

public class SongManager {
    private SongSearcher songSearcher = new SongSearcher();

    private MyTunesDataAccess songDAO_DB;

    public SongManager() {
        songDAO_DB = new SongDAO_DB();
    }

    public List<Song> getAllSongs() throws Exception {
        return songDAO_DB.getAllSongs();
    }

    public List<Song> searchSongs(String query) throws Exception
    {
        List<Song> allSongs = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSongs, query);
        return searchResult;
    }

    public Song createSong(String artist, String songTitle, String album, int year, String genre, String filepath) throws Exception
    {
        return songDAO_DB.createSong(artist, songTitle, album, year, genre, filepath);
    }

    public void updateSong(Song updatedSong) throws Exception
    {
        songDAO_DB.updateSong(updatedSong);
    }

    public void deleteSong(Song selectedSong) throws Exception {

        songDAO_DB.deleteSong(selectedSong);
    }

}
