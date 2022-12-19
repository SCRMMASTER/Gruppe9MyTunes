package easv.mrs.BE;

public class
Playlist {
    private String playlistTitle;
    private int id;

    public Playlist(int id, String playlistTitle)
    {
        this.id = id;
        this.playlistTitle = playlistTitle;

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
