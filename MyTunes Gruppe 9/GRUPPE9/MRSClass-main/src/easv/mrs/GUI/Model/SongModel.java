package easv.mrs.GUI.Model;

// Java imports
import easv.mrs.BE.Song;
import easv.mrs.BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

// Project imports


/**
 * @author smsj
 */
public class SongModel {

    private ObservableList<Song> songsToBeHeard;
    private SongManager songManager;
    private Song selectedSong;

    /**
     * Constructor
     * @throws Exception
     */
    public SongModel() throws Exception {
        songManager = new SongManager();
        songsToBeHeard = FXCollections.observableArrayList();
        songsToBeHeard.addAll(songManager.getAllSongs());
        System.out.println("obslist:" + songsToBeHeard.size());
    }

    public ObservableList<Song> getObservableSongs() {
        return songsToBeHeard;
    }

    public void searchSong(String query) throws Exception {
        List<Song> searchResults = songManager.searchSongs(query);
        songsToBeHeard.clear();
        songsToBeHeard.addAll(searchResults);
    }


    public void createNewSong(String artist, String songtitle, String album, int year, String genre, float duration, String filepath) throws Exception{
        // Create movie in data storage
        Song s = songManager.createNewSong(artist, songtitle, album, year, genre, duration, filepath);

        // Add movie to observable list (gui)
        songsToBeHeard.add(s);
    }

    public void updateSong(Song updatedSong) throws Exception {
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
}
