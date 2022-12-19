package easv.mrs.GUI.Controller;

import easv.mrs.BE.Playlist;
import easv.mrs.GUI.Model.PlaylistModel;
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
    @FXML
    private PlaylistModel model;



    public void handleNewPlaylist(ActionEvent actionEvent) throws Exception {
        String newName = newPlaylistTextfield.getText();
        model.createNewPlaylist(newName,-1);

    }

    @Override
    public void setup()
    {
        model = getModel().getPlaylistModel();
        newPlaylistTextfield.setText("Name...");
    }
}
