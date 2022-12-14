package easv.mrs.DAL;

import easv.mrs.BE.Song;

import java.util.List;

public interface MyTunesDataAccess {
    public List<Song> getAllSongs() throws Exception;
    public Song createSong(String artist, String songtitle, String album, int year, String genre, float duration, String filepath) throws Exception;
    public void updateSong(Song song) throws Exception;
    public void deleteSong(Song song) throws  Exception;

}
