package io.github.teamtracker.misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.teamtracker.model.misc.Wordlist;

public class WordFilter {

    private List<String> wordlist = new ArrayList<>();

    public WordFilter(Iterable<Wordlist> wordlist) {
        for (Wordlist word : wordlist) {
            this.wordlist.add(word.getWord());
        }
    }

    public boolean isProfane(String text) {
        String lowerCaseText = text.toLowerCase();

        for (String word : this.wordlist) {
            if (lowerCaseText.contains(word.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public String filterText(String text) {
        Set<String> profaneWords = new HashSet<>(this.wordlist);

        String regex = "\\b(" + String.join("|", profaneWords) + ")\\b";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(text);

        StringBuffer filteredText = new StringBuffer();

        while (matcher.find()) {
            String matchedWord = matcher.group();

            String replacement = "*".repeat(matchedWord.length());

            matcher.appendReplacement(filteredText, replacement);
        }

        matcher.appendTail(filteredText);

        return filteredText.toString();
    }
}