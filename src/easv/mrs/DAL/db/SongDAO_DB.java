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

    //Get all Songs
    public List<Song> getAllSongs() throws Exception
    {

        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM Songs;";

            ResultSet rs = stmt.executeQuery(sql);

            // Loop through all the rows of the database
            while (rs.next())
            {
                //Map the Database roes to the Song object

                int id = rs.getInt("ID");
                String artist = rs.getString("Artist");
                String songtitle = rs.getString("SongTitle");
                String album = rs.getString("Album");
                int year = rs.getInt("Year");
                String genre = rs.getString("Genre");
                //float duration = rs.getFloat("Duration");
                String filepath = rs.getString("Filepath");

                Song song = new Song(id, artist, songtitle, album, year, genre, filepath );
                allSongs.add(song);
            }

            System.out.println(allSongs.size());
            //Return all songs
            return allSongs;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }

    //Create a new Song object.
    public Song createSong(String artist, String songtitle, String album, int year, String genre, String filepath) throws Exception
    {
        // Sql Command
        String sql = "INSERT INTO Songs (artist,songtitle, album, year, genre, filepath) VALUES (?,?,?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind the parameters
            stmt.setString(1, artist);
            stmt.setString(2, songtitle);
            stmt.setString(3, album);
            stmt.setString(4, String.valueOf(year));
            stmt.setString(5, genre);
            stmt.setString(6, filepath);


            // Run the SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create song object and send up the layers
            Song song = new Song(id, artist, songtitle, album, year, genre, filepath);
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

        int updatedRows = stmt.executeUpdate();{

            }

            //Run the SQL Command
            //stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }

    }

    //Delete selected song

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
