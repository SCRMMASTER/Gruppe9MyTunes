package easv.mrs.GUI.Controller;

import easv.mrs.BE.Song;
import easv.mrs.DAL.db.SongDAO_DB;
import easv.mrs.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewSongController extends BaseController {
    @FXML
    public TextField artistTextfield, songTextfield, albumTextfield, yearTextfield, gengreTextfield, filePathTextfield;
    @FXML
    public Button addSongButton;
    private SongModel model;

    @Override
    public void setup() {
        model = getModel().getSongModel();
    }

    public void handleAddSong(ActionEvent actionEvent) {
        String artist = artistTextfield.getText();
        String songTitle = songTextfield.getText();
        String album = albumTextfield.getText();
        int year = Integer.parseInt(yearTextfield.getText());
        String genre = gengreTextfield.getText();
        String filepath = filePathTextfield.getText();
        try {
            model.createSong(artist, songTitle, album, year, genre,filepath);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}