/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.DAL.db;

import easv.mrs.BE.Song;
import easv.mrs.DAL.MyTunesDataAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements MyTunesDataAccess {
    private MyDatabaseConnector databaseConnector;

    public SongDAO_DB()
    {
        databaseConnector = new MyDatabaseConnector();
    }

    //Get all the songs from the database

    public List<Song> getAllSongs() throws Exception
    {

        ArrayList<Song> allSongs = new ArrayList<>();

        //Gets connection and creates the statement.
        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            //The SQL Command.

            String sql = "SELECT * FROM Songs;";

            ResultSet rs = stmt.executeQuery(sql);

            // Loop through all the rows of the database

            while (rs.next())
            {
                //Map the Database rows to the Song object

                int id = rs.getInt("ID");
                String artist = rs.getString("Artist");
                String songtitle = rs.getString("SongTitle");
                String album = rs.getString("Album");
                int year = rs.getInt("Year");
                String genre = rs.getString("Genre");
                String filepath = rs.getString("Filepath");

                Song song = new Song(id, artist, songtitle, album, year, genre, filepath );
                allSongs.add(song);
            }

            System.out.println(allSongs.size());

            //Return all the songs

            return allSongs;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }

    //Inserts a new song to the database.

    public Song createSong(int id, String artist, String songTitle, String album, int year, String genre, String filePath) throws Exception
    {
        // The SQL Command.

        String sql = "INSERT INTO Songs (artist, songTitle, album, year, genre, filePath) VALUES (?,?,?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind the parameters

            stmt.setString(1, artist);
            stmt.setString(2, songTitle);
            stmt.setString(3, album);
            stmt.setString(4, String.valueOf(year));
            stmt.setString(5, genre);
            stmt.setString(6, filePath);


            // Run the SQL statement

            stmt.executeUpdate();

            // Gets the generated ID from the DB

            ResultSet rs = stmt.getGeneratedKeys();
            int generatedKey = 0;

            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            // Creates song object and sends it up the layers.

            Song song = new Song(id, artist, songTitle, album, year, genre, filePath);
            return song;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create song", ex);
        }

    }

    //Update a Song
    public void updateSong(Song updatedSong) throws Exception
    {
        try (Connection conn = databaseConnector.getConnection()) {

            //The SQL Command.

            String sql = "UPDATE Songs SET artist = ?, songtitle = ?, album = ?, Year = ?, genre = ?, filepath =? WHERE Id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind the parameters

            stmt.setString(1, updatedSong.getArtist());
            stmt.setString( 2, updatedSong.getSongtitle());
            stmt.setString(3, updatedSong.getAlbum());
            stmt.setInt(4, updatedSong.getYear());
            stmt.setString(5, updatedSong.getGenre());
            stmt.setString(6, updatedSong.getFilepath());
            stmt.setInt(7, updatedSong.getId());

            //Run the SQL Command

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }

    }

    //Deletes a song from the database.

    public void deleteSong(Song selectedSong) throws Exception
    {
        try (Connection conn = databaseConnector.getConnection()){

            //SQL Command
            String sql = "DELETE FROM Songs WHERE id = ?;";

            //Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, selectedSong.getId());

            //Run the statement
            stmt.executeUpdate();

        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete Song...", ex);
        }
    }

    public List<Song> searchSongs(String query) throws Exception
    {
        //TODO Do this
        throw new UnsupportedOperationException();
    }
}
