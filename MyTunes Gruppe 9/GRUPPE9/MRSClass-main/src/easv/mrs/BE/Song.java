package easv.mrs.BE;

public class Song {

    private int id;
    private String artist;
    private String songtitle;
    private String album;
    private int albumtrack;
    private int year;
    private String genre;

    private float duration;

    private String filepath;

    public Song(int id, String artist, String songtitle, String album, int year, String genre, float duration, String filepath) {

        this.id = id;
        this.artist = artist;
        this.songtitle = songtitle;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
        this.filepath = filepath;

    }

    public Song(int id) {

    }

    public Song(Song selectedSong, int id, String updatedsongtitle, String updatedartist) {

    }

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

    public float getDuration() {
        return duration;
    }
    public void setDuration(float duration) {
        this.duration = duration;
    }
    public String getFilepath(){
        return filepath;
    }
    public void setFilepath(String filepath){
        this.filepath = filepath;
    }

}
