package easv.mrs.GUI.Model;

import easv.mrs.BE.Playlist;
import easv.mrs.BE.Song;
import easv.mrs.BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class SongModel {

    private ObservableList<Song> songsToBeHeard;
    private SongManager songManager;
    private Song selectedSong;

    /**
     * Constructor
     * @throws Exception
     */
    public SongModel() throws Exception
    {
        songManager = new SongManager();
        songsToBeHeard = FXCollections.observableArrayList();
        songsToBeHeard.addAll(songManager.getAllSongs());
    }

    public ObservableList<Song> getObservableSongs() {
        return songsToBeHeard;
    }

    public void searchSong(String query) throws Exception
    {
        List<Song> searchResults = songManager.searchSongs(query);
        songsToBeHeard.clear();
        songsToBeHeard.addAll(searchResults);
    }


    //public void createSong(String artist, String songTitle, String album, int year, String genre, String filepath) throws Exception
    //{
        // Create Song in data storage
      //&  Song s = songManager.createSong(artist, songTitle, album, year, genre, filepath);

        // Add song to observable list (gui)
        //songsToBeHeard.add(s);
   // }

    public void updateSong(Song updatedSong) throws Exception
    {
        // Call BLL
        // update song in DB
        songManager.updateSong(updatedSong);

        // update ListView
        songsToBeHeard.clear();
        songsToBeHeard.addAll(songManager.getAllSongs());
    }

    public Song getSelectedSong() {

                return selectedSong;
    }

    public void setSelectedSong(Song selectedSong) {

        this.selectedSong = selectedSong;

    }

    public void deleteSong(Song selectedSong) throws Exception {
        songManager.deleteSong(selectedSong);

        songsToBeHeard.clear();
        songsToBeHeard.addAll(songManager.getAllSongs());
        
    }


}
