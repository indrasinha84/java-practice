package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Subsequence {

    public static void main(String[] args) {
        var line = "MKHKGJJGGH;XMKGJJGRJGH";
        var bothParts = line.split(";");
        System.out.println(findSequence(bothParts[0], bothParts[1], "").getFirst());
    }

    static List<String> findSequence(String left, String right, String result) {
        if (left.isEmpty()) {
            var retVal = new ArrayList<String>();
            retVal.add(result);
            return retVal;
        }
        else {
            return left.chars().mapToObj(Character::toString).flatMap(c -> {
                var splitRight = right.split(c, 2);
                if (splitRight.length > 1) {
                    return findSequence(left.split(c, 2)[1], splitRight[1], result + c).stream();
                } else {
                    var retVal = new ArrayList<String>();
                    retVal.add(result);
                    return retVal.stream();
                }
            }).sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
        }
    }
}
