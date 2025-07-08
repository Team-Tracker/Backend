package io.github.teamtracker.misc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFilter {

    private static final Set<String> wordlist = Collections.synchronizedSet(new HashSet<>());

    public static void addWord(String word) {
        WordFilter.wordlist.add(word.toLowerCase());
    }

    public boolean isProfane(String text) {
        String lowerCaseText = text.toLowerCase();

        for (String word : WordFilter.wordlist) {
            if (lowerCaseText.contains(word)) {
                return true;
            }
        }

        return false;
    }

    public String filterText(String text) {
        Set<String> profaneWords = new HashSet<>(WordFilter.wordlist);

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