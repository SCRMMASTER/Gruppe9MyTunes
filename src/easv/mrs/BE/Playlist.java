/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.BE;

//Create the playlist class.
public class Playlist {
    private String playlistTitle;
    private int id;

    //Create the Constructor
    public Playlist(int id, String playlistTitle)
    {
        this.id = id;
        this.playlistTitle = playlistTitle;

    }
    // Create the getter's and setter's

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
