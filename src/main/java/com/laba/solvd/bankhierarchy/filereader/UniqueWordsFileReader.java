package com.laba.solvd.bankhierarchy.filereader;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UniqueWordsFileReader {

    private static final Logger LOGGER = LogManager.getLogger(UniqueWordsFileReader.class);

    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);
        LOGGER.info("Enter input file path");
        String pathIn = scan.next();//src/main/resources/fileInput.txt

        LOGGER.info("Enter output file path");
        String pathOut = scan.next();//src/main/resources/fileOutput.txt
        File inFile = new File(pathIn);
        File outFile = new File(pathOut);

        try (FileInputStream fis=new FileInputStream(inFile)) {
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);

            String text = "";
            String line = br.readLine();
            while (line != null) {
                text = text.concat(line + " ");
                line = br.readLine();
            }

            List<String> words = Arrays.asList(text.split(" "));

            HashMap<String, Integer> uniqueWords = new HashMap<>();
            words.stream().forEach(word -> {
                uniqueWords.putIfAbsent(word, 0);
                uniqueWords.put(word, uniqueWords.get(word) + 1);
            });

            final String[] results = {"Number of unique words :: " + uniqueWords.size() + "\n\n"};
            uniqueWords.keySet().stream().forEach(key -> {
                results[0] = results[0].concat(key + " -> " + uniqueWords.get(key).toString() + "\n");
            });
            FileUtils.writeStringToFile(outFile, results[0], StandardCharsets.UTF_8, false);
            LOGGER.info("Count of unique words successfully written in fileOutput file." );
        } catch (IOException e) {
            LOGGER.warn("File not found ");
            LOGGER.info("File not found");
        }
    }
    }
