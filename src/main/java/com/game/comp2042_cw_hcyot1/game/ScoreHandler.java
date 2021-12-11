package com.game.comp2042_cw_hcyot1.game;

import javafx.application.Platform;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Objects;

public class ScoreHandler {
    private final static String HIGHSCORES_FILE_NAME = "highscores.csv";
    private LinkedHashMap<Integer, Integer> highscores;
    private static CSVFormat csvFormat;

    public ScoreHandler() {
        highscores = loadScores();
    }

    public static LinkedHashMap<Integer, Integer> loadScores() {
        LinkedHashMap<Integer, Integer> tempScores = new LinkedHashMap<>();

        try {
            InputStreamReader in = new InputStreamReader(Objects.requireNonNull(
                    ScoreHandler.class.getResourceAsStream(HIGHSCORES_FILE_NAME)
            ));

            csvFormat = CSVFormat.EXCEL.withHeader();
            Iterable<CSVRecord> records = csvFormat.parse(in);
            for (CSVRecord record : records)
                tempScores.put(Integer.valueOf(record.get("Level")), Integer.valueOf(record.get("Highscore")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempScores;
    }

    public void checkAndSetHighscore(int level, int newScore) {
        // if this runs for the first time currentHighscore will be 0
        int currentHighscore = highscores.getOrDefault(level, 0);

        if (newScore > currentHighscore)
            highscores.put(level, newScore);
    }

    public void saveScores() {
    }
}
