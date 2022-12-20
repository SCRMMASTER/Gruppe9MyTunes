package easv.mrs.GUI.Model;

public class MyTunesModel {

    private SongModel songModel;
    private PlaylistModel playlistModel;

    public MyTunesModel() throws Exception {
        songModel = new SongModel();
        playlistModel = new PlaylistModel();

    }

    public SongModel getSongModel() {
        return songModel;
    }

    public void setSongModel(SongModel songModel) {

        this.songModel = songModel;
    }

    public PlaylistModel getPlaylistModel(){

        return playlistModel;
    }

    public void setPlaylistModel(PlaylistModel playlistModel)
    {
        this.playlistModel = playlistModel;
    }




}
