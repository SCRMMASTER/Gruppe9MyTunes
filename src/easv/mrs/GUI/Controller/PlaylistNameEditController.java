package easv.mrs.GUI.Controller;

import easv.mrs.BE.Playlist;
import easv.mrs.BE.Song;
import easv.mrs.DAL.db.PlaylistDAO_DB;
import easv.mrs.GUI.Model.PlaylistModel;
import easv.mrs.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlaylistNameEditController extends BaseController {

    @FXML
    public TextField renamePlaylistTextfield;
    @FXML
    public Button executeRenameButton;
    private PlaylistModel model;

    //Renames a Playlist.
    public void renamePlaylist(ActionEvent actionEvent) throws Exception
    {
        String updatedTitle = renamePlaylistTextfield.getText();

        model.renamePlaylist(new Playlist(model.getSelectedPlaylist().getId(),updatedTitle));
        System.out.println(model.getSelectedPlaylist().getId());

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void setup()
    {
        model = getModel().getPlaylistModel();
        renamePlaylistTextfield.setText("new Playlist name");
    }
}
