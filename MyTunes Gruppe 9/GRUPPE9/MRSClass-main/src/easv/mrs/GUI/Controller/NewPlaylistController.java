package easv.mrs.GUI.Controller;

import easv.mrs.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewPlaylistController extends BaseController{
    @FXML
    public TextField newPlaylistTextfield;
    @FXML
    public Button newPlaylistButton;

    private SongModel model;

    @Override
    public void setup()
    {
        model = getModel().getSongModel();

        newPlaylistTextfield.setText("Name...");

    }




    public void handleNewPlaylist(ActionEvent actionEvent)
    {


    }
}
