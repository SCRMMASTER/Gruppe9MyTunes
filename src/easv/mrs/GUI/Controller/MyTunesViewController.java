package easv.mrs.GUI.Controller;

import easv.mrs.BE.Playlist;
import easv.mrs.BE.Song;
import easv.mrs.GUI.Model.PlaylistModel;
import easv.mrs.GUI.Model.SongModel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyTunesViewController extends BaseController implements Initializable {
    @FXML
    private TextField txtSongSearch, searchSongField, txtArtist, txtSongTitle, txtAlbum, txtAlbumTrack, txtYear, txtGenre,
            txtFilepath;
    @FXML
    private Button editSongButton, closeAppButton, previousSongButton, playPauseButton, nextSongButton, toPlaylistButton,
            newPlaylistButton, editPlaylistButton, deletePlaylistButton, songUpSort, songDownSort, deleteSongFromPlaylistButton,
            newSongButton, deleteSongButton;
    @FXML
    private Text currentSongDisplay;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ListView allSongs, playlists, songsOnPlaylist;
    private SongModel songModel;
    private PlaylistModel playlistModel;
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private boolean isPlaying;
    private Media media;
    private MediaPlayer mediaPlayer;

    public MyTunesViewController() {
        try {
            songModel = new SongModel();

        } catch (Exception e) {
            displayError(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Creates a new ArrayList to hold our music library.
        songs = new ArrayList<File>();
        directory = new File("songsFolder");
        files = directory.listFiles();

        //Loops through files and adds them to the directory.
        if (files != null) {
            for (File file : files) {
                songs.add(file);
            }
        }

        //Creating the MediaPlayer
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        //Display Current track
        currentSongDisplay.setText(songs.get(songNumber).getName());

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
    }

    @Override
    public void setup() {
        //Getting models for Song and Playlist.
        songModel = getModel().getSongModel();
        playlistModel = getModel().getPlaylistModel();

        editSongButton.setDisable(false);

        //Displays data to listviews.
        allSongs.setItems(songModel.getObservableSongs());
        playlists.setItems(playlistModel.getObservablePlaylists());

        //Enables the search function.
        searchSongField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                displayError(e);
            }
        });
    }

        //Displays an error.
    private void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

        //Opens window for adding a New Song. Waits for input from the user.
    public void handleAddNewSongNew(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv/mrs/GUI/View/NewSongView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        NewSongController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        //Creates the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("New Song");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        //Opens window and waits for user input.
        dialogWindow.showAndWait();
    }
        //Opens window for user to edit details of chosen Song.
    public void handleEdit(ActionEvent actionEvent) throws IOException {
        Song selectedSong = (Song) allSongs.getSelectionModel().getSelectedItem();
        songModel.setSelectedSong(selectedSong);

        //Wrapped in if statement to avoid run-time error.
        if (selectedSong != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/easv/mrs/GUI/View/SongDetailsView.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            SongDetailsViewController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            //Creates the dialog Stage.
            Stage dialogWindow = new Stage();
            dialogWindow.setTitle("Edit Song Details");
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogWindow.setScene(scene);

            //Opens window and waits for user input.
            dialogWindow.showAndWait();
        }
    }

        //Closes the application.
    public void handleCloseApplication(ActionEvent actionEvent) {
        Platform.exit();
    }

        //Updates the views.
    public void handleSearchSong(ActionEvent actionEvent) throws Exception {
        SongModel model = new SongModel();
        model.getObservableSongs();
    }

        //Enables user to backtrack through the library.
    public void handlePreviousSong(ActionEvent actionEvent) {
        if (songNumber > 0) {
            songNumber--;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            currentSongDisplay.setText(songs.get(songNumber).getName());

            mediaPlayer.play();
        } else {
            songNumber = songs.size() - 1;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            currentSongDisplay.setText(songs.get(songNumber).getName());

            mediaPlayer.play();
        }
    }

        //Plays and pauses the track.
    public void handlePlaySong(ActionEvent actionEvent) {
        if (isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
        } else {
            mediaPlayer.play();
            isPlaying = true;
        }
    }

        //Enables the user to skip through tracks.
    public void handleNextSong(ActionEvent actionEvent) {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            currentSongDisplay.setText(songs.get(songNumber).getName());

            mediaPlayer.play();
        } else {
            songNumber = 0;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            currentSongDisplay.setText(songs.get(songNumber).getName());

            mediaPlayer.play();
        }
    }

    public void handleAddSongToPlaylist(ActionEvent actionEvent) {

    }

    //Opens window to create New Playlist
    public void handleNewPlaylist(ActionEvent actionEvent) throws IOException {

        Playlist selectedPlaylist = (Playlist) playlists.getSelectionModel().getSelectedItem();
        playlistModel.setSelectedPlaylist(selectedPlaylist);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv/mrs/GUI/View/NewPlaylistView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        NewPlaylistController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        //Creates the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("New Playlist");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        //Opens window and waits for user input.
        dialogWindow.showAndWait();
    }
        //Opens window for user to edit the name of A Playlist.
    public void handleEditPlaylist(ActionEvent actionEvent) throws IOException {

        Playlist selectedPlaylist = (Playlist) playlists.getSelectionModel().getSelectedItem();
        playlistModel.setSelectedPlaylist(selectedPlaylist);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv/mrs/GUI/View/PlaylistRenameView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        PlaylistNameEditController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        //Creates the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Rename Playlist");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        //Opens window and waits for user input.
        dialogWindow.showAndWait();
    }

        //Deletes a Playlist
    public void handleDeletePlaylist(ActionEvent actionEvent) throws Exception
    {
            Playlist selectedPlaylist = (Playlist) playlists.getSelectionModel().getSelectedItem();
            playlistModel.deletePlaylist(selectedPlaylist);
    }

        public void deleteSongOnPlaylist (ActionEvent actionEvent) throws Exception
        {

        }

        //Deletes a Song.
        public void handleDeleteSong (ActionEvent actionEvent) throws Exception
        {
            Song selectedSong = (Song) allSongs.getSelectionModel().getSelectedItem();
            songModel.deleteSong(selectedSong);
        }
}

