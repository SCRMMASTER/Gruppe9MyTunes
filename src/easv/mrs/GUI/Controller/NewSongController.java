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
    @FXML
    private SongModel model;


    public void handleAddSong(ActionEvent actionEvent) {

        int id = -1;
        String artist = artistTextfield.getText();
        String songTitle = songTextfield.getText();
        String album = albumTextfield.getText();
        int year = Integer.parseInt(yearTextfield.getText());
        String genre = gengreTextfield.getText();
        String filepath = filePathTextfield.getText();
        try {

            model.createSong(id, artist, songTitle, album, year, genre,filepath);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void setup()
    {
        model = getModel().getSongModel();
    }
}