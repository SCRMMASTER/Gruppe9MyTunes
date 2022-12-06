package easv.mrs.GUI.Controller;

import easv.mrs.BE.Song;
import easv.mrs.GUI.Model.SongModel;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyTunesViewController extends BaseController implements Initializable {


    public TextField txtSongSearch;
    public ListView<Song> lstSongs;
    public Button editSongButton;
    public ListView songsOnPlaylistView;

    @FXML
    private TableView songsView;

    @FXML
    private TextField txtArtist;

    @FXML
    private TextField txtSongTitle;

    @FXML
    private TextField txtAlbum;

    @FXML
    private TextField txtAlbumTrack;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtGenre;
    @FXML
    private TextField txtFilepath;

    private SongModel songModel;

    public MyTunesViewController()  {

        try {
            songModel = new SongModel();
        } catch (Exception e) {
            displayError(e);
            //e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
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


    public void handleAddNewSongNew(ActionEvent actionEvent) {

        String artist = txtArtist.getText();
        String songtitle = txtSongTitle.getText();
        String album = txtAlbum.getText();
        int year = Integer.parseInt(txtYear.getText());
        String genre = txtGenre.getText();
        float duration = Float.parseFloat(txtDuration.getText());
        String filepath = txtFilepath.getText();

        try {
            songModel.createNewSong(artist, songtitle, album, year, genre, duration, filepath);
        } catch (Exception e) {
        }
    }

    public void handleEditNew(ActionEvent actionEvent) throws IOException {

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
        /*



         */
    }
}

