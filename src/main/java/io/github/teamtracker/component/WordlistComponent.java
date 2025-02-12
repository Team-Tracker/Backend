package io.github.teamtracker.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.teamtracker.misc.WordFilter;
import io.github.teamtracker.model.misc.Wordlist;
import io.github.teamtracker.repository.WordlistRepository;
import jakarta.annotation.PostConstruct;

@Component
public class WordlistComponent {

    @Autowired
    private WordlistRepository wordlistRepository;

    @PostConstruct
    public void init() {
        for (Wordlist wordlistItem : this.wordlistRepository.findAll()) {
            WordFilter.addWord(wordlistItem.getWord());
        }
    }
}