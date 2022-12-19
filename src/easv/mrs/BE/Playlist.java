package easv.mrs.BE;

public class
Playlist {
    private String playlistTitle;
    private int id;

    public Playlist(String playlistTitle, int id)
    {

        this.playlistTitle = playlistTitle;
        this.id = id;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }



    @Override
    public String toString() {
        return playlistTitle;
    }
}
