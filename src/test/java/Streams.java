import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.*;

public class Streams {



    @Test
    public void streamFromList(){
        // Skapa en stream från en lista

        Stream<String> stream = loremIpsum.stream();


    }








    @Test
    public void infiniteStream(){
        // Strömmar kan vara "oändliga"

        Stream<Integer> streamOfEvenNumbers =
                Stream.iterate(0, i -> i + 1)
                        .filter(i -> i % 2 == 0);

        System.out.println("Even numbers:");
        streamOfEvenNumbers.limit(10).forEach(System.out::println);

        Random random = new Random();
        IntStream ints = random.ints(0, 100);
        System.out.println("\nRandom numbers:");
        ints
                .limit(1000)
                .forEach(System.out::println);

    }





    @Test
    public void forEach(){
        // forEach - iterera över alla element och utför en operation för varje element

        loremIpsum.stream()
                .forEach(word -> System.out.print(word + " "));
    }





    @Test
    public void filter(){
        // Filter - filtrera ut element

        loremIpsum.stream()
                .filter(word -> (word.startsWith("a")))
                .forEach(word -> System.out.print(word + " "));
    }





    @Test
    public void optional(){
        // Optional - istället för null!

        System.out.println(loremIpsum.stream().findFirst());
        System.out.println(loremIpsum.stream().findFirst());
        System.out.println(Collections.emptyList().stream().findFirst().get());
    }





    @Test
    public void skipAndLimit(){
        loremIpsum.stream()
                .skip(10)
                .limit(2).forEach(System.out::println);
    }





    @Test
    public void map(){
        // Map - transformera från en typ till en annan

        System.out.println(loremIpsum.stream()
                .map(word -> word.length())
                .findFirst().get());
    }





    @Test
    public void reduce(){
        // Reduce - koka ner många värden till ett resultat av samma typ


        // summera tal

        int sum = aBunchOfNumbers.stream()
                .reduce(0, (num1, num2) -> num1 + num2);


        System.out.println("Sum = " + sum);

        // Sammanfoga ord med en avdelare
        String firstSentence = loremIpsum.stream()
                .limit(8)
                .reduce((word1, word2) -> word1 + ", " + word2)
                .get();

        System.out.println("First sentence: \"" + firstSentence + "\"");

        firstSentence = loremIpsum.stream()
                .limit(8)
                .collect(joining(" "));

        System.out.println("First sentence, again: \"" + firstSentence + "\"");
    }





    @Test
    public void distinct(){
        // Distinct - ta bort dubletter

        List<Integer> distinctNumbers = aBunchOfNumbers.stream()
                .distinct().collect(toList());

        System.out.println("Original contained " + aBunchOfNumbers.size() + " numbers");
        System.out.println("Removed " + (aBunchOfNumbers.size() - distinctNumbers.size()) + " duplicates");
    }





    @Test
    public void flatMap(){
        // FlatMap - generera en ny ström för varje värde

        List<Integer> boundryValues = aBunchOfNumbers.stream()
                .limit(3)
                .flatMap(n -> Stream.of(n - 1, n + 1))
                .collect(toList());
        boundryValues.forEach(System.out::println);
    }





    @Test
    public void collectors(){
        Double averageNrOfChars = loremIpsum.stream()
                .map(s -> s.replaceAll("\\.", ""))
                .collect(averagingInt(String::length));
        System.out.println("Average number of chars per word = " + averageNrOfChars);

        Map<String, Long> nrOfWordsByFirstLetter = loremIpsum.stream()
                .map(s -> s.toLowerCase())
                .map(s -> s.replaceAll("\\.", ""))
                .collect(groupingBy((String s) -> s.substring(0, 1), counting()));

        System.out.println("Number of words per by first letter: " + nrOfWordsByFirstLetter);
    }





    @Test
    public void sorting(){
        List<String> sorted = loremIpsum.stream()
                .sorted()
                .collect(toList());

        System.out.println("Unsorted first word is " + loremIpsum.get(0));
        System.out.println("Sorted first word is " + sorted.get(0));

        List<String> byLengthAndLexical = loremIpsum.parallelStream()
                .map(s -> s.toLowerCase())
                .sorted(comparingInt(String::length).reversed().thenComparing(naturalOrder()))
                .limit(10)
                .collect(toList());

        System.out.println("Top ten words by length and then lexical order: " + byLengthAndLexical);
    }

    private static final List<String> loremIpsum = Arrays.asList(
            "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit.",
            "Suspendisse", "nec", "facilisis", "mi", "id", "scelerisque", "erat.",
            "Ut", "dignissim", "ligula", "sed", "vulputate", "egestas.", "Class", "aptent",
            "taciti", "sociosqu", "ad", "litora", "torquent", "per", "conubia", "nostra",
            "per", "inceptos", "himenaeos.", "Phasellus", "eu", "mi", "libero.", "Sed", "ac",
            "turpis", "eu", "nunc", "dignissim", "gravida", "in", "eget", "augue.", "Curabitur",
            "eget", "congue", "dui.", "Etiam", "facilisis", "lectus", "neque,", "sed", "fermentum",
            "purus", "vulputate", "sit", "amet.", "In", "erat", "diam", "molestie", "eu", "nunc",
            "at", "adipiscing", "adipiscing", "libero.", "Donec", "laoreet", "nisi", "a", "lobortis",
            "commodo.", "Cum", "sociis", "natoque", "penatibus", "et", "magnis", "dis", "parturient",
            "montes,", "nascetur", "ridiculus", "mus.", "Nam", "fermentum", "non", "ante", "at",
            "dictum.", "Sed", "sit", "amet", "nulla", "mi"
    );

    private static final List<Integer> aBunchOfNumbers = Arrays.asList(
            884, 82, 574, 970, 818, 133, 950, 950, 790, 652, 365, 295, 219, 845, 316, 39, 763,
            973, 492, 418, 840, 107, 254, 220, 315, 27, 369, 417, 374, 988, 710, 968, 717, 23,
            744, 29, 374, 868, 424, 489, 169, 539, 20, 801, 643, 267, 567, 944, 93, 722, 644,
            130, 785, 258, 484, 86, 148, 204, 679, 406, 479, 572, 247, 214, 144, 129, 232, 695,
            813, 511, 539, 271, 912, 91, 546, 87, 670, 722, 294, 497, 770, 574, 491, 355, 967,
            823, 758, 884, 450, 514, 693, 34, 949, 720, 940, 454, 368, 213, 480, 405
    );
}
