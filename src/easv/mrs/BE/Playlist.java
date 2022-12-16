package easv.mrs.BE;

public class
Playlist {
    private int nbrOfTracks;
    private String playlistTitle;
    private int id;

    public Playlist(int nbrOfTracks, String playlistTitle, int id)
    {
        this.nbrOfTracks = nbrOfTracks;
        this.playlistTitle = playlistTitle;
        this.id = id;
    }

    public int getNbrOfTracks() {
        return nbrOfTracks;
    }
    public void setNbrOfTracks(int nbrOfTracks) {
        this.nbrOfTracks = nbrOfTracks;
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
        return id + ": " + nbrOfTracks + ": " + playlistTitle + ": ";
    }
}
