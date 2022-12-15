package easv.mrs.GUI.Controller;

import easv.mrs.BE.Song;
import easv.mrs.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SongDetailsViewController extends BaseController {

    @FXML
    public Button btnUpdate;
    @FXML
    private TextField txtArtist, txtSongTitle, txtAlbum, txtYear, txtGenre, txtFilepath;
    @FXML
    private SongModel model;

    /**
     *
     * @param actionEvent
     * @throws Exception
     */
    public void handleUpdate(ActionEvent actionEvent) throws Exception
    {

        String updatedartist = txtArtist.getText();
        String updatedsongtitle = txtSongTitle.getText();
        String updatedalbum = txtAlbum.getText();
        int updatedyear = Integer.parseInt(txtYear.getText());
        String updatedgenre = txtGenre.getText();
        String updatedfilepath = txtFilepath.getText();

        model.updateSong(new Song(model.getSelectedSong().getId(), updatedartist, updatedsongtitle, updatedalbum, updatedyear, updatedgenre, updatedfilepath));


        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void setup()
    {
        model = getModel().getSongModel();
        txtArtist.setText(model.getSelectedSong().getArtist());
        txtSongTitle.setText(String.valueOf(model.getSelectedSong().getSongtitle()));
        txtAlbum.setText(model.getSelectedSong().getAlbum());
        txtYear.setText(String.valueOf(model.getSelectedSong().getYear()));
        txtGenre.setText(model.getSelectedSong().getGenre());
        txtFilepath.setText((model.getSelectedSong().getFilepath()));
    }
}
