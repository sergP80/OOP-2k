package ua.edu.chmnu.fks.oop.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListStreamProcessing {
    public static final char SHADOW_CHAR = '\u2593';

    public static void main(String[] args) {

        List<String> list = Arrays.asList(
                " Hello guys",
                null,
                "           Enter password ang get hello",
                "      Access       is denied. Retype your login or password        ",
                null,
                "Wrong login or      password on the form. Retype them      "
        );

        System.out.println("================ Source sentences ================");
        list.forEach(s -> System.out.println(s));//list.forEach(System.out::println)

        System.out.println("================ Capitalized sentences ================");
        List<String> lenOfString = list.stream()
                .filter(s -> Objects.nonNull(s))//Objects::nonNull
                .map(s -> s.trim())
                .map(s -> s.toUpperCase())//map(String::toUpperCase)
                .collect(Collectors.toList());
        lenOfString.forEach(System.out::println);

        System.out.println("================ Sentences with at least 3 words ================");
        List<String> stringsWithMore3Words = list.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> {
                    String[] words = s.split("([\\s]+)|(\\.!\\?)");
                    return words.length > 3;
                })
                .collect(Collectors.toList());
        stringsWithMore3Words.forEach(System.out::println);

        System.out.println("================ Frequencies of words ================");

        String text = list.stream()
                .filter(s -> Objects.nonNull(s) && !s.trim().isEmpty())
                .collect(Collectors.joining("\n"));
        Map<String, Long> wordFrequency = Arrays.stream(text.split("[\\s\\.!\\?\n]+"))
                .filter(s -> Objects.nonNull(s) && !s.trim().isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(wordFrequency);

        System.out.println("================ Frequencies of words #2 ================");

        Map<String, Integer> wordFrequency2 = Arrays.stream(text.split("[\\s\\.!\\?\n]+"))
                .filter(s -> Objects.nonNull(s) && !s.trim().isEmpty())
                .collect(Collectors.toMap(w -> w, w-> 1, Integer::sum));
        System.out.println(wordFrequency2);

        System.out.println("================ Frequencies of char ================");

        Map<Character, Integer> charFrequencies = list.stream()
                .filter(Objects::nonNull)
                .flatMapToInt(CharSequence::chars)
                .filter(Character::isAlphabetic)
                .sorted()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
        System.out.println(charFrequencies);

        System.out.println("================ Frequencies of char histogram ================");

        charFrequencies.forEach((c, count) -> {
            System.out.print(c + ": ");
            for (int i = 0; i < count; ++i) {
                System.out.print(SHADOW_CHAR);
            }
            System.out.println();
        });
    }
}
