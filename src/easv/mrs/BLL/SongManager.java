/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.BLL;

import easv.mrs.BE.Song;
import easv.mrs.BLL.util.SongSearcher;
import easv.mrs.DAL.MyTunesDataAccess;
import easv.mrs.DAL.db.SongDAO_DB;
import java.util.List;

//Creates the songManager Class.
public class SongManager {

    //Create a new songManager.
    private SongSearcher songSearcher = new SongSearcher();

    private MyTunesDataAccess songDAO_DB;

    public SongManager() {
        songDAO_DB = new SongDAO_DB();
    }

    // Gets all Songs from songsDAO_DB
    public List<Song> getAllSongs() throws Exception {
        return songDAO_DB.getAllSongs();
    }

    //Gets the search result from songsearcher.

    public List<Song> searchSongs(String query) throws Exception
    {
        List<Song> allSongs = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSongs, query);
        return searchResult;
    }

    //Sends the information to create a new song.

    public Song createSong(int id, String artist, String songTitle, String album, int year, String genre, String filepath) throws Exception
    {
        return songDAO_DB.createSong(id,artist, songTitle, album, year, genre, filepath);
    }

    //Sends the updated Song information.

    public void updateSong(Song updatedSong) throws Exception
    {
        songDAO_DB.updateSong(updatedSong);

    }

    //Sends the information to delete a song.

    public void deleteSong(Song selectedSong) throws Exception {

        songDAO_DB.deleteSong(selectedSong);
    }
}
