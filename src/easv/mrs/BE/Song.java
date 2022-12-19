/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.BE;

//Create the song Class.
public class Song {
    private int id;
    private String artist;
    private String songtitle;
    private String album;

    private int year;
    private String genre;
    private String filepath;

    //Create the Constructor/

    public Song(int id, String artist, String songtitle, String album, int year, String genre, String filepath) {

        this.id = id;
        this.artist = artist;
        this.songtitle = songtitle;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.filepath = filepath;
    }

// Create the getter's and setter's

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

     public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilepath(){
        return filepath;
    }
    public void setFilepath(String filepath){
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return artist + ", " + songtitle;
    }
}
