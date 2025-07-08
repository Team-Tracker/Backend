package io.github.teamtracker.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.teamtracker.misc.WordFilter;
import io.github.teamtracker.model.misc.Wordlist;
import io.github.teamtracker.repository.WordlistRepository;
import jakarta.annotation.PostConstruct;

@Component
public class WordlistComponent {

    private static final String RESOURCE_PATH = "src/main/resources/wordlists";

    @Autowired
    private WordlistRepository wordlistRepository;

    @PostConstruct
    public void init() {
        for (Wordlist wordlistItem : this.wordlistRepository.findAll()) {
            WordFilter.addWord(wordlistItem.getWord());
        }

        this.read();
    }

    private void read() {
        this.readAllFilesLineByLine();
    }

    public void readAllFilesLineByLine() {
        try (Stream<Path> paths = Files.walk(Paths.get(RESOURCE_PATH))) {
            paths.filter(Files::isRegularFile).forEach(this::readFile);
        } catch (IOException e) {
            throw new RuntimeException("Error accessing files in resources directory", e);
        }
    }

    private void readFile(Path filePath) {
        System.out.println("Reading file: " + filePath);
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                WordFilter.addWord(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
        }
    }
}