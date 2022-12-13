package easv.mrs.BE;
public class Playlist {

    private int tracknbr;

    private String artist;

    private String songtitle;

    private float duration;

    public Playlist(int tracknbr, String artist, String songtitle, float duration)
    {
        this.tracknbr = tracknbr;
        this.artist = artist;
        this.songtitle = songtitle;
        this.duration = duration;
    }

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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
