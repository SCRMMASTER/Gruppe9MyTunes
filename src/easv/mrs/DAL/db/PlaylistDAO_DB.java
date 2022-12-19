/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */

package easv.mrs.DAL.db;

import easv.mrs.BE.Playlist;
import easv.mrs.BE.Song;
import easv.mrs.DAL.MyTunesDataAccess;
import easv.mrs.DAL.MyTunesPlaylistAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Creates the PlaylistDAO_DB class.

public class PlaylistDAO_DB implements MyTunesPlaylistAccess {
    private MyDatabaseConnector databaseConnector;

    public PlaylistDAO_DB()
    {
        databaseConnector = new MyDatabaseConnector();
    }

    //retrive all songs from the playlist database.

    public List<Playlist> getAllSongsPl() throws Exception
    {
        ArrayList<Playlist> allSongsPl = new ArrayList<>();

        //Gets a connection and sends the prepared SQL statement.

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM Playlist;";


            ResultSet rs = stmt.executeQuery(sql);

            // Loop through all the rows of the database.

            while (rs.next()) {

                //Map the Database roes to the Playlist object.

                String playlistTitle = rs.getString("playlistTitle");
                int id = rs.getInt("ID");


                Playlist playlist = new Playlist(id,playlistTitle);
                allSongsPl.add(playlist);
            }

            //Return all songs from playlist.

            return allSongsPl;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }




    //Create a new Playlist object.
    public Playlist createPlaylist(int id, String playlistTitle) throws Exception
    {
        //SQL Statement.

        String sql = "INSERT INTO Playlist (playlistTitle) VALUES (?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind the parameters

            stmt.setString(1, playlistTitle);

            // Run the SQL statement

            stmt.executeUpdate();

            // Get the generated ID from the DB

            ResultSet rs = stmt.getGeneratedKeys();
            int generatedKey = 0;

            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            // Create playlist object and send up the layers

            Playlist playlist = new Playlist(id, playlistTitle);
            return playlist;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create playlist", ex);
        }

    }

    //rename a playlist
    public void renamePlaylist(Playlist selectedPlaylist) throws Exception
    {
        try (Connection conn = databaseConnector.getConnection()) {

            //SQL statement.

            String sql = "UPDATE Playlist SET playlistTitle = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind the parameters

            stmt.setString( 1, selectedPlaylist.getPlaylistTitle());
            stmt.setInt( 2, selectedPlaylist.getId());

            //Run the SQL Command

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }

    }
    //Delete a playlist.
    public void deletePlaylist(Playlist selectedPlaylist) throws Exception
    {
        try (Connection conn = databaseConnector.getConnection()){

            //SQL Command
            String sql = "DELETE FROM Playlist WHERE playlistTitle = ?;";

            //Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, (selectedPlaylist.getPlaylistTitle()));

            //Run the statement
            stmt.executeUpdate();

        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete Song...", ex);
        }
    }
}
