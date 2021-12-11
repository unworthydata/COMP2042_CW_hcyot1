package com.game.comp2042_cw_hcyot1.game;

import javafx.application.Platform;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Objects;

public class ScoreHandler {
    private final static String HIGHSCORES_FILE_LOCATION = "src/main/resources/com/game/comp2042_cw_hcyot1/game/highscores.csv";
    private LinkedHashMap<Integer, Integer> highscores;
    private static CSVFormat csvFormat;

    public ScoreHandler() {
        highscores = loadScores();
    }

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

    public void checkAndSetHighscore(int level, int newScore) {
        // if this runs for the first time currentHighscore will be 0
        int currentHighscore = highscores.getOrDefault(level, 0);

        if (newScore > currentHighscore)
            highscores.put(level, newScore);
    }

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
