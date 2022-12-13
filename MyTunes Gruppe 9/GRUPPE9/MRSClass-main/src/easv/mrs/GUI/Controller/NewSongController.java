package easv.mrs.GUI.Controller;

import easv.mrs.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewSongController extends BaseController{
    @FXML
    public TextField artistTextfield, songTextfield,albumTextfield, yearTextfield, gengreTextfield, filePathTextfield;
    @FXML
    public Button addSongButton;
    private SongModel model;



    @Override
    public void setup()
    {
        model = getModel().getSongModel();
    }

    public void handleAddSong(ActionEvent actionEvent)
    {



    }
}

/*
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
                /*
 */