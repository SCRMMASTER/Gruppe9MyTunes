package easv.mrs.DAL.db;

import easv.mrs.BE.Playlist;
import easv.mrs.BE.Song;
import easv.mrs.DAL.MyTunesDataAccess;
import easv.mrs.DAL.MyTunesPlaylistAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements MyTunesPlaylistAccess {

    private MyDatabaseConnector databaseConnector;

    public PlaylistDAO_DB() {

        databaseConnector = new MyDatabaseConnector();
    }


      //Get all Songs
    public List<Playlist> getAllSongsPl() throws Exception {

        ArrayList<Playlist> allSongsPl = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM Playlist;";


            ResultSet rs = stmt.executeQuery(sql);

            // Loop through all the rows of the database
            while (rs.next()) {

                //Map the Database roes to the Playlist object

                int nbrOfTracks = rs.getInt("nbrOfTracks");
                String playlistTitle = rs.getString("playlistTitle");
                int id = rs.getInt("ID");
                //String filepath = rs.getString("Filepath");

                Playlist playlist = new Playlist(nbrOfTracks, playlistTitle, id);
                allSongsPl.add(playlist);
            }

            System.out.println(allSongsPl.size());
            //Return all songs from playlist
            return allSongsPl;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }


    @Override
    public Playlist createPlaylist(int nbrOfTracks, String playlistTitle) throws Exception {
        return null;
    }

    //Create a new Playlist object.
    public Playlist createPlaylist(int nbrOfTracks, String playlistTitle, int id) throws Exception {

        // Sql Command


        String sql = "INSERT INTO Playlist (artist,songtitle, duration, filepath, id) VALUES (?,?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind the parameters
            stmt.setString(1, String.valueOf(nbrOfTracks));
            stmt.setString(2, playlistTitle);
            stmt.setString(4, String.valueOf(id));


            // Run the SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int tracknbr = 0;

            if (rs.next()) {
                tracknbr = rs.getInt(1);
            }

            // Create playlist object and send up the layers
            Playlist playlist = new Playlist(nbrOfTracks, playlistTitle, id);
            return playlist;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create movie", ex);
        }

    }

    //Update a Song
    public void updatePlaylist(Playlist playlist) throws Exception {

        try (Connection conn = databaseConnector.getConnection()) {

            String sql = "UPDATE Playlist SET nbrOfTracks = ?, playlistTitle = ?";

            //den gamle SQL statement
            //String sql = "UPDATE Playlist SET artist = ?, songtitle = ?, filepath =?, WHERE tracknbr = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind the parameters
            stmt.setString(1, String.valueOf(playlist.getNbrOfTracks()));
            stmt.setString( 2, playlist.getPlaylistTitle());
            stmt.setString(4, String.valueOf(playlist.getId()));

            //Run the SQL Command
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }

    }

    //Delete selected song

    public void deletePlaylist(Playlist selectedPlaylist) throws Exception {
        try (Connection conn = databaseConnector.getConnection()){

            //SQL Command
            String sql = "DELETE FROM Playlist WHERE nbrOfTracks = ?;";

            //Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, selectedPlaylist.getNbrOfTracks());

            //Run the statement
            stmt.executeUpdate();

        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete Song...", ex);
        }
    }

   // public List<Song> searchSongs(String query) throws Exception {

        //TODO Do this
     //   throw new UnsupportedOperationException();
    //}

}
