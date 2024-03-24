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
        System.out.println(findSequenceWithTailRec(bothParts[0], bothParts[1], ""));
    }

    static List<String> findSequence(String left, String right, String result) {
        if (left.isEmpty()) {
            var retVal = new ArrayList<String>();
            retVal.add(result);
            return retVal;
        } else {
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

    private static String findSequenceWithTailRec(String left, String right, String result) {
        if (left.isEmpty() || right.isEmpty())
            return result;
        else {
            var positionInRight = right.lastIndexOf(left.substring((left.length() - 1)));
            if (positionInRight == -1)
                return findSequenceWithTailRec(left.substring(0, left.length() - 1), right, result);
            else
                return findSequenceWithTailRec(left.substring(0, left.length() - 1), right.substring(0, positionInRight),
                        left.substring(left.length() - 1) + result);
        }
    }
}
