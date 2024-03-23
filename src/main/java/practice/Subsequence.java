package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Subsequence {

    public static void main(String[] args) {
        var line = "MKHKGJJGGH;XMKGJGRJH";
        var bothParts = line.split(";");
        System.out.println(findSequence(bothParts[0], bothParts[1], "").getFirst());
    }

    static List<String> findSequence(String left, String right, String result) {
        return left.chars().mapToObj(Character::toString).flatMap(c -> {
            var foundIndex = right.indexOf(c);
            if (foundIndex != -1) {
                return findSequence(left.substring(1), right.substring(foundIndex + 1), result + c).stream();
            } else {
                var retVal = new ArrayList<String>();
                retVal.add(result);
                return retVal.stream();
            }
        }).sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
    }
}
