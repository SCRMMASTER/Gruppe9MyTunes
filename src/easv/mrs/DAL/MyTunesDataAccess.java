/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.DAL;

import easv.mrs.BE.Song;

import java.util.List;

public interface MyTunesDataAccess {
    public List<Song> getAllSongs() throws Exception;
    public Song createSong(int id, String artist, String songTitle, String album, int year, String genre, String filepath) throws Exception;
    public void updateSong(Song song) throws Exception;
    public void deleteSong(Song song) throws  Exception;

}
