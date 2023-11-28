package com.laba.solvd.bankhierarchy.filereader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Files {
    private static final Logger LOGGER = LogManager.getLogger(Files.class);

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            LOGGER.info("Enter your location here:");
            String locationOfFile = input.nextLine();
            File file = new File(locationOfFile);
            LOGGER.info("Write your text here: ");
            String text = input.nextLine();
            FileUtils.writeStringToFile(file, text, "UTF-8");
            LOGGER.info("Words written to file successfully.");
            String[] words = StringUtils.split(text);
            Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
            int count = uniqueWords.size();
            File outputFile = new File("fileOutputText.txt");
            FileUtils.writeStringToFile(outputFile, Integer.toString(count));
            LOGGER.info("Count of unique words written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("An error occurred!", e);
        }
    }
}
