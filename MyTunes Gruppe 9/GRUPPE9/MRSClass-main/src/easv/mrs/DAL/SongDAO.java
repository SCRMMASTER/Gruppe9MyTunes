package easv.mrs.DAL;

import easv.mrs.BE.Song;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SongDAO implements MyTunesDataAccess {

        private static final String SONGS_FILE = "data/song_titles.txt";
        private Path pathToFile = Path.of(SONGS_FILE);

        /**
         * Retrieve all songs from the data source
         * @return
         * @throws IOException
         */
        public List<Song> getAllSongs() throws Exception {

            try {
                // Read all lines from file
                List<String> lines = Files.readAllLines(pathToFile);
                List<Song> songs = new ArrayList<>();

                // Parse each line
                for (String line : lines) {
                    String[] separatedLine = line.split(",");

                    // Map each separated line to Song object
                    int id = Integer.parseInt(separatedLine[0]);
                    String artist = separatedLine(2);
                    String songtitle = separatedLine(2);
                    String album = separatedLine(2);
                    //int albumtrack = Integer.parseInt(separatedLine[0]);
                    int year = Integer.parseInt(separatedLine[1]);
                    //float duration = Integer.parseInt(separatedLine[2]);
                    String genre = separatedLine(2);

                    // Create
                    // Songobject
                    Song newSong = new Song(id, artist, songtitle, album, year, genre);
                    songs.add(newSong);

                    //System.out.println(separatedLine);
                }
                songs.sort(Comparator.comparingInt(Song::getId));

                return songs;
            }
            catch (IOException e) {
                // Log specifics about IOException and re-throw up the layers...
                throw e;
            }
        }

    private String separatedLine(int i) {
        return null;
    }

    /**
         * Create a new movie
         * @param songtitle
         * @param year
         * @return
     */
        @Override
        public Song createSong(String artist, String songtitle, String album, int year, String genre) throws Exception {

            int nextId = getNextID();
            String newLine = nextId + ","+ artist + "," + songtitle + "," + album + "," +  year + "," + genre;

            // Append new line using Java NIO
            //Files.write(pathToFile, ("\r\n" + newLine).getBytes(), APPEND);

            // Append new line using BufferedWriter
            try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardOpenOption.SYNC, StandardOpenOption.APPEND, StandardOpenOption.WRITE)) {
                bw.newLine();
                bw.write(nextId + ","+ artist + "," + songtitle + "," + album + "," +  year + "," + genre);
            }

            return new Song(nextId, artist, songtitle, album, year, genre);
        }

        /**
         * Update a song with param movie
         * @param song
         * @throws Exception
         */
        @Override
        public void updateSong(Song song) throws Exception {
            try {
                File tmp = new File(song.hashCode() + ".txt"); //Creates a temp file for writing to.
                List<Song> allSongs = getAllSongs();
                allSongs.removeIf((Song t) -> t.getId() == song.getId());
                allSongs.add(song);

                //I'll sort the songs by their ID's
                allSongs.sort(Comparator.comparingInt(Song::getId));

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp))) {
                    for (Song sng : allSongs) {
                        bw.write(sng.getId() + "," + sng.getYear() + "," + sng.getSongtitle());
                        bw.newLine();
                    }
                }

                //Overwrite the movie file wit the tmp one.
                Files.copy(tmp.toPath(), new File(SONGS_FILE).toPath(), StandardCopyOption.REPLACE_EXISTING);
                //Clean up after the operation is done (Remove tmp)
                Files.delete(tmp.toPath());

            } catch (IOException ex) {
                throw new Exception("Could not update movie.", ex);
            }
        }

        /**
         * Delete a movie from the collection
         * @param song
         * @throws Exception
         */
        @Override
        public void deleteSong(Song song) throws Exception {

            try {
                File file = new File(SONGS_FILE);
                if (!file.canWrite()) {
                    throw new Exception("Can't write to song storage");
                }
                List<Song> songs = getAllSongs();
                songs.remove(song);
                OutputStream os = Files.newOutputStream(file.toPath());
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os))) {
                    for (Song sng : songs) {
                        String line = sng.getId() + "," + sng.getYear() + "," + sng.getSongtitle();
                        bw.write(line);
                        bw.newLine();
                    }
                }
            } catch (IOException ex) {
                throw new Exception("Could not delete song.", ex);
            }
        }


        /**
         *
         * @param id
         * @return
         * @throws Exception
         */
        public Song getSong(int id) throws Exception {
            List<Song> all = getAllSongs();

            int index = Collections.binarySearch(all, new Song(id), Comparator.comparingInt(Song::getId));

            if (index >= 0) {
                return all.get(index);
            } else {
                throw new IllegalArgumentException("No song with ID: " + id + " is found.");
            }
        }



        /**
         * Get the next ID in our collection
         * @return
         */
        private int getNextID() throws Exception {
            List<Song> songs = getAllSongs();

            Song lastSong = songs.get(songs.size()- 1);
            return lastSong.getId() + 1;
        }





    /*
    public List<Movie> getAllMovies() {
        List<Movie> allMovieList = new ArrayList<>();

        File moviesFile = new File(MOVIES_FILE);


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(moviesFile))) {
            boolean hasLines = true;
            while (hasLines) {
                String line = bufferedReader.readLine();
                hasLines = (line != null);
                if (hasLines && !line.isBlank())
                {
                    String[] separatedLine = line.split(",");

                    int id = Integer.parseInt(separatedLine[0]);
                    int year = Integer.parseInt(separatedLine[1]);
                    String title = separatedLine[2];
                    if(separatedLine.length > 3)
                    {
                        for(int i = 3; i < separatedLine.length; i++)
                        {
                            title += "," + separatedLine[i];
                        }
                    }
                    Movie movie = new Movie(id, title, year);
                    allMovieList.add(movie);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMovieList;
    }
    */



    }
