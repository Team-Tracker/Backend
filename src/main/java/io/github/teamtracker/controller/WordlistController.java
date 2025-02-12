package io.github.teamtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.model.misc.Wordlist;
import io.github.teamtracker.repository.WordlistRepository;

@Controller
@RequestMapping(path = "/wordlist")
public class WordlistController {

    @Autowired
    private WordlistRepository wordlistRepository;

    @GetMapping(path = "/")
    public @ResponseBody Iterable<Wordlist> getWords() {
        return this.wordlistRepository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody Integer addWord(@RequestParam String word) {
        Wordlist newWord = new Wordlist(word);

        this.wordlistRepository.save(newWord);

        return newWord.getId();
    }
}