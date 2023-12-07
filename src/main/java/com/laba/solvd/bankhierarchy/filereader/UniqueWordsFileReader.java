package com.laba.solvd.bankhierarchy.filereader;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniqueWordsFileReader {

    private static final Logger LOGGER = LogManager.getLogger(UniqueWordsFileReader.class);

    public static void main(String[] args) {
        InputStream inputStream = UniqueWordsFileReader.class.getClassLoader().getResourceAsStream("inputFile.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String text = "";
            String line = br.readLine();
            while (line != null) {
                text = text.concat(line + " ");
                line = br.readLine();
            }

            Set<String> words = new HashSet<>(Arrays.asList(text.split(" ")));
            FileUtils.writeStringToFile(new File("outputFile.txt"), "Number of unique words : " + words.size());
            LOGGER.info("Number of unique words successfully written in file.");
        } catch (IOException e) {
            LOGGER.warn("File not found ", e.getMessage());
        }
    }
}
