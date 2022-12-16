package easv.mrs.GUI.Controller;

import easv.mrs.BE.Playlist;
import easv.mrs.BE.Song;
import easv.mrs.DAL.db.PlaylistDAO_DB;
import easv.mrs.DAL.db.SongDAO_DB;
import easv.mrs.GUI.Model.MRSModel;
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
import java.io.InvalidClassException;
import java.io.WriteAbortedException;
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
    public Text currentSongDisplay;
    @FXML
    public ListView songsOnPlaylist;
    public Slider volumeSlider;
    @FXML
    private ListView allSongs;
    @FXML
    public ListView playlists;
    @FXML
    private SongModel songModel;
    @FXML
    private PlaylistModel playlistModel;
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private boolean isPlaying;
    private Media media;
    private MediaPlayer mediaPlayer;

    SongDAO_DB songDAO_db;

    public MyTunesViewController() {
        try {
            songModel = new SongModel();

        } catch (Exception e) {
            displayError(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songs = new ArrayList<File>();
        directory = new File("songsFolder");
        files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                songs.add(file);
                System.out.println(file);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

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
        songModel = getModel().getSongModel();
        playlistModel = getModel().getPlaylistModel();


        editSongButton.setDisable(false);

        allSongs.setItems(songModel.getObservableSongs());
        playlists.setItems(playlistModel.getObservablePlaylists());

        searchSongField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                displayError(e);
            }
        });
/*
        lstSongs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                editSongButton.setDisable(false);
                txtArtist.setText(newValue.getArtist());
                txtSongTitle.setText(newValue.getSongtitle());
                txtAlbum.setText(newValue.getAlbum());
                txtYear.setText(String.valueOf(newValue.getYear()));
                txtGenre.setText(newValue.getGenre());
            }
            else
                editSongButton.setDisable(true);
        });
*/
    }

    private void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }


    public void handleAddNewSongNew(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv/mrs/GUI/View/NewSongView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        NewSongController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        // Create the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("New Song");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        // Show the dialog and wait until the user closes it
        dialogWindow.showAndWait();
    }

    public void handleEdit(ActionEvent actionEvent) throws IOException {
        Song selectedSong = (Song) allSongs.getSelectionModel().getSelectedItem();
        songModel.setSelectedSong(selectedSong);

        if (selectedSong != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/easv/mrs/GUI/View/SongDetailsView.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            SongDetailsViewController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            // Create the dialog Stage.
            Stage dialogWindow = new Stage();
            dialogWindow.setTitle("Edit Song Details");
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogWindow.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogWindow.showAndWait();

        }

    }

    public void handleCloseApplication(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void handleSearchSong(ActionEvent actionEvent) throws Exception {
        SongModel model = new SongModel();
        model.getObservableSongs();
    }

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

    public void handlePlaySong(ActionEvent actionEvent) {
        if (isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
        } else {
            mediaPlayer.play();
            isPlaying = true;
        }
    }

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

    public void handleNewPlaylist(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv/mrs/GUI/View/NewPlaylistView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        NewPlaylistController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        // Create the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("New Playlist");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        // Show the dialog and wait until the user closes it
        dialogWindow.showAndWait();
    }

    public void handleEditPlaylist(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv/mrs/GUI/View/PlaylistRenameView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        PlaylistNameEditController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        // Create the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Rename Playlist");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        // Show the dialog and wait until the user closes it
        dialogWindow.showAndWait();
    }

    public void handleDeletePlaylist(ActionEvent actionEvent) throws Exception
    {
            Playlist selectedPlaylist = (Playlist) playlists.getSelectionModel().getSelectedItem();
            playlistModel.deletePlaylist(selectedPlaylist);
    }

        public void deleteSongOnPlaylist (ActionEvent actionEvent) throws Exception
        {

        }

        public void handleDeleteSong (ActionEvent actionEvent)
        {

        }

}

