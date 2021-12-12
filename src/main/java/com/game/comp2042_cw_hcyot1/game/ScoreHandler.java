package com.game.comp2042_cw_hcyot1.game;

import javafx.application.Platform;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Handles dealing with the file system to store/retrieve the list of high scores.
 * The high scores are stored in a .csv file. This class uses a {@link LinkedHashMap}
 * to store the high scores because it stores a key-value pair (level-highscore in this case),
 * but still keeps the original order of entries as they came in (unlike other Map implementations).
 */
public class ScoreHandler {
    private final static String HIGHSCORES_FILE_LOCATION =
            "src/main/resources/com/game/comp2042_cw_hcyot1/game/highscores.csv";
    private LinkedHashMap<Integer, Integer> highscores;

    public ScoreHandler() {
        highscores = loadScores();
    }

    /**
     * Static method so that any class can retrieve the list of high scores from storage.
     * @return A {@link LinkedHashMap} of the high scores stored in storage.
     */
    public static LinkedHashMap<Integer, Integer> loadScores() {
        LinkedHashMap<Integer, Integer> tempScores = new LinkedHashMap<>();

        try (Reader in = new FileReader(HIGHSCORES_FILE_LOCATION)) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
            for (CSVRecord record : records)
                tempScores.put(Integer.valueOf(record.get("Level")), Integer.valueOf(record.get("Highscore")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempScores;
    }

    /**
     * Take the new score and compare it with the current high score for a specific level.
     * If it is greater than the current high score, it is set as the new high score for that level.
     * @param level The current level.
     * @param newScore The new score
     * @return If the new score is greater than the current high score, then return true, otherwise return false.
     */
    public boolean checkAndSetHighscore(int level, int newScore) {
        // if this runs for the first time currentHighscore will be 0
        int currentHighscore = highscores.getOrDefault(level, 0);

        if (newScore > currentHighscore) {
            highscores.put(level, newScore);
            return true;
        }

        return false;
    }

    /**
     * Store the current list of high scores to the file of high scores in storage.
     */
    public void saveScores() {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(HIGHSCORES_FILE_LOCATION), CSVFormat.EXCEL)) {
            printer.printRecord("Level", "Highscore");
            for (var entry : highscores.entrySet())
                printer.printRecord(entry.getKey(), entry.getValue());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
