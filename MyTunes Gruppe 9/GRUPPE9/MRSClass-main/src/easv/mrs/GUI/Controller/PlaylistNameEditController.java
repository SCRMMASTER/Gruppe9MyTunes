package easv.mrs.GUI.Controller;

import easv.mrs.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PlaylistNameEditController extends BaseController {

    @FXML
    public TextField renamePlaylistTextfield;
    @FXML
    public Button executeRenameButton;

    private SongModel model;

    public void renamePlaylist(ActionEvent actionEvent)
    {

    }

    @Override
    public void setup()
    {
        model = getModel().getSongModel();
        renamePlaylistTextfield.setText("new Playlist name");
    }
}
