package easv.mrs.BE;

/*
 *@author Magnus, Johnni & Jesper
*/
public class Playlist {

    // Declare variables for the Playlist
    private int tracknbr;

    private String artist;

    private String songtitle;

    private int id;

    private String filepath;


    public Playlist(int tracknbr, String artist, String songtitle,  int id, String filepath) {

        this.tracknbr = tracknbr;
        this.artist = artist;
        this.songtitle = songtitle;
        this.id = id;
        this.filepath = filepath;

    }

    // Getters and Setters for the Playlist.

    public int getTracknbr() {
        return tracknbr;
    }

    public void setTracknbr(int tracknbr) {
        this.tracknbr = tracknbr;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongtitle() {
        return songtitle;
    }

    public void setSongtitle(String songtitle) {
        this.songtitle = songtitle;
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getFilepath(){
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
