package easv.mrs.BE;

/*
 *@author Magnus, Johnni & Jesper
*/
public class Playlist {

    // Declare variables for the Playlist
    private int nbrOfTracks;

    private String playlistTitle;

    private int id;



    public Playlist(int nbrOfTracks, String playlistTitle, int id) {

        this.nbrOfTracks = nbrOfTracks;
        this.playlistTitle = playlistTitle;
        this.id = id;


    }

    // Getters and Setters for the Playlist.

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
