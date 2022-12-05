package easv.mrs.GUI.Controller;

import easv.mrs.BE.Song;
import easv.mrs.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SongDetailsViewController extends BaseController {

    @FXML
    private TextField txtArtist, txtSongTitle, txtAlbum, txtYear, txtGenre;

    private SongModel model;

    /**
     *
     * @param actionEvent
     * @throws Exception
     */
    public void handleUpdate(ActionEvent actionEvent) throws Exception {

        String updatedartist = txtArtist.getText();
        String updatedsongtitle = txtSongTitle.getText();
        String updatedalbum = txtAlbum.getText();
        //int updatedalbumtrack = Integer.parseInt(txtAlbumTrack.getText());
        int updatedyear = Integer.parseInt(txtYear.getText());
        //float updatedduration = Float.parseFloat(txtDuration.getText());
        String updatedgenre = txtGenre.getText();

        Song updatedSong = new Song(model.getSelectedSong().getId(), updatedartist, updatedsongtitle, updatedalbum, updatedyear, updatedgenre);

        model.updateSong(updatedSong);

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void setup() {

        model = getModel().getSongModel();

        txtArtist.setText(model.getSelectedSong().getArtist());
        txtSongTitle.setText(String.valueOf(model.getSelectedSong().getSongtitle()));
    }
}
