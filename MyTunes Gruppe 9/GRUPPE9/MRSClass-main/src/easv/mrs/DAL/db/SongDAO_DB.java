package easv.mrs.DAL.db;

import easv.mrs.BE.Song;
import easv.mrs.DAL.MyTunesDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements MyTunesDataAccess {
    private MyDatabaseConnector databaseConnector;

    public SongDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }

    public List<Song> getAllSongs() throws Exception {

        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM Songs;";


            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Song object

                int id = rs.getInt("ID");
                String artist = rs.getString("Artist");
                String songtitle = rs.getString("SongTitle");
                String album = rs.getString("Album");
                //int albumtrack = rs.getInt("AlbumTrack");
                int year = rs.getInt("Year");
                //float duration = rs.getFloat("Duration");
                String genre = rs.getString("Genre");

                Song song = new Song(id, artist, songtitle, album, year, genre );
                allSongs.add(song);
            }
            System.out.println(allSongs.size());

            return allSongs;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }

    public Song createSong(String artist, String songtitle, String album, int year, String genre) throws Exception {

        // Dynamic SQL


        String sql = "INSERT INTO Songs (artist,songtitle, album, year, genre) VALUES (?,?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, artist);
            stmt.setString(2, songtitle);
            stmt.setString(3, album);
            stmt.setString(4, String.valueOf(year));
            stmt.setString(5, genre);


            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create song object and send up the layers
            Song song = new Song(id, artist, songtitle, album, year, genre);
            return song;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create movie", ex);
        }

    }

    public void updateSong(Song song) throws Exception {

        try (Connection conn = databaseConnector.getConnection()) {

            String sql = "UPDATE Songs SET artist = ?, songtitle = ?, album = ?, Year = ?, genre = ? WHERE Id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, song.getArtist());
            stmt.setString( 2, song.getSongtitle());
            stmt.setString(3, song.getAlbum());
            stmt.setInt(4, song.getYear());
            stmt.setString(5, song.getGenre());
            stmt.setInt(6, song.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }


        // UPDATE Movie SET Title = 'Terminator 1', Year = 1990
        //WHERE Id = 1

    }

    public void deleteSong(Song song) throws Exception {
        //TODO Do this
        throw new UnsupportedOperationException();
    }

    public List<Song> searchSongs(String query) throws Exception {

        //TODO Do this
        throw new UnsupportedOperationException();
    }

}
