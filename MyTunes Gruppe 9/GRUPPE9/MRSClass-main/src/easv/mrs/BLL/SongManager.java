package easv.mrs.BLL;

import easv.mrs.BE.Song;
import easv.mrs.BLL.util.SongSearcher;
import easv.mrs.DAL.MyTunesDataAccess;
import easv.mrs.DAL.db.SongDAO_DB;
import java.util.List;

public class SongManager {
    private SongSearcher songSearcher = new SongSearcher();

    private MyTunesDataAccess songDAO;

    public SongManager() {
        songDAO = new SongDAO_DB();
    }

    public List<Song> getAllSongs() throws Exception {
        return songDAO.getAllSongs();
    }

    public List<Song> searchSongs(String query) throws Exception
    {
        List<Song> allSongs = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSongs, query);
        return searchResult;
    }

    public Song createNewSong(String artist, String songtitle, String album, int year, String genre, String filepath) throws Exception
    {
        return songDAO.createSong(artist, songtitle, album, year, genre, filepath);
    }

    public void updateSong(Song updatedSong) throws Exception
    {
        songDAO.updateSong(updatedSong);
    }
}
