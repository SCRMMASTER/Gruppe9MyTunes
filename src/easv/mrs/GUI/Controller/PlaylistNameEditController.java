package easv.mrs.GUI.Controller;

import easv.mrs.BE.Playlist;
import easv.mrs.DAL.db.PlaylistDAO_DB;
import easv.mrs.GUI.Model.PlaylistModel;
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

    PlaylistDAO_DB playlistDAO_db = new PlaylistDAO_DB();


    public void renamePlaylist(ActionEvent actionEvent) throws Exception {

    }

    @Override
    public void setup()
    {
        model = getModel().getSongModel();
        renamePlaylistTextfield.setText("new Playlist name");
    }
}
