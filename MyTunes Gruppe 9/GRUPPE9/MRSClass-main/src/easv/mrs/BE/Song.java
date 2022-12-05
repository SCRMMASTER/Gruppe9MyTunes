package easv.mrs.BE;

public class Song {

    private int id;
    private String artist;
    private String songtitle;
    private String album;
    private int albumtrack;
    private int year;
    //private float duration;
    private String genre;

    public Song(int id, String artist, String songtitle, String album, int year, String genre) {

        this.id = id;
        this.artist = artist;
        this.songtitle = songtitle;
        this.album = album;
        this.year = year;
        this.genre = genre;

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
}
