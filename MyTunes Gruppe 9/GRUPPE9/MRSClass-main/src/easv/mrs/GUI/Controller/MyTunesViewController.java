package easv.mrs.GUI.Controller;

import easv.mrs.BE.Song;
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
    public Text currentSongDisplay;
    @FXML
    public TableView playlistsView;
    public Slider volumeSlider;
    @FXML
    private TextField txtSongSearch,searchSongField,txtArtist,txtSongTitle,txtAlbum,txtAlbumTrack,txtYear,txtDuration,txtGenre,
            txtFilepath;
    @FXML
    private Button editSongButton, closeAppButton,previousSongButton,playPauseButton,nextSongButton,toPlaylistButton,
            newPlaylistButton,editPlaylistButton,deletePlaylistButton,songUpSort,songDownSort,deleteSongFromPlaylistButton,
            newSongButton,deleteSongButton;
    @FXML
    private ListView songsOnPlaylistView;
    @FXML
    private ListView<Song> lstSongs;
    @FXML
    private TableView songsView;
    @FXML
    private SongModel songModel;

    private File directory;
    private File[]files;
    private ArrayList<File> songs;
    private int songNumber;
    private boolean isPlaying;
    private Media media;
    private MediaPlayer mediaPlayer;


    public MyTunesViewController()  {
        try {
            songModel = new SongModel();
        } catch (Exception e) {
            displayError(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        songs = new ArrayList<File>();
        directory = new File("songsFolder");
        files=directory.listFiles();

        if (files != null)
        {
            for(File file : files)
            {
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

        editSongButton.setDisable(true);

        songsOnPlaylistView.setItems(songModel.getObservableSongs());
/*
        txtSongSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                displayError(e);
                //e.printStackTrace();
            }
        });
        */

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

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    public void handleAddNewSongNew(ActionEvent actionEvent)
    {

        String artist = txtArtist.getText();
        String songtitle = txtSongTitle.getText();
        String album = txtAlbum.getText();
        int year = Integer.parseInt(txtYear.getText());
        String genre = txtGenre.getText();
        float duration = Float.parseFloat(txtDuration.getText());
        String filepath = txtFilepath.getText();

        try
        {
            songModel.createNewSong(artist, songtitle, album, year, genre, duration, filepath);
        }
        catch (Exception e)
        {
        }
    }

    public void handleEdit(ActionEvent actionEvent) throws IOException
    {

        Song selectedSong = lstSongs.getSelectionModel().getSelectedItem();
        songModel.setSelectedSong(selectedSong);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/easv/mrs/GUI/View/SongDetailsView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        SongDetailsViewController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        // Create the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Edit Song");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);


        // Show the dialog and wait until the user closes it
        dialogWindow.showAndWait();
    }

        //Closes the application
    public void handleCloseApplication(ActionEvent actionEvent)
    {
        Platform.exit();
    }

    public void handleSearchSong(ActionEvent actionEvent)
    {

    }

    public void handlePreviousSong(ActionEvent actionEvent)
    {
        if(songNumber > 0)
        {
            songNumber--;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            currentSongDisplay.setText(songs.get(songNumber).getName());

            mediaPlayer.play();
        }
        else{
            songNumber = songs.size() - 1;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            currentSongDisplay.setText(songs.get(songNumber).getName());

            mediaPlayer.play();
        }
    }

        //Plays or pauses the track.
    public void handlePlaySong(ActionEvent actionEvent)
    {
        if(isPlaying)
        {
            mediaPlayer.pause();
            isPlaying = false;
        }
        else
        {
            mediaPlayer.play();
            isPlaying = true;
        }
    }

    public void handleNextSong(ActionEvent actionEvent)
    {
        if(songNumber < songs.size()-1)
        {
            songNumber++;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            currentSongDisplay.setText(songs.get(songNumber).getName());

            mediaPlayer.play();
        }
        else
        {
            songNumber = 0;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            currentSongDisplay.setText(songs.get(songNumber).getName());

            mediaPlayer.play();
        }
    }

    public void handleAddSongToPlaylist(ActionEvent actionEvent)
    {

    }

    public void handleNewPlaylist(ActionEvent actionEvent)
    {

    }

    public void handleEditPlaylist(ActionEvent actionEvent)
    {

    }

    public void handleDeletePlaylist(ActionEvent actionEvent)
    {

    }

    public void deleteSongOnPlaylist(ActionEvent actionEvent)
    {

    }

    public void handleDeleteSong(ActionEvent actionEvent)
    {

    }
}

