package afomic.com.camfood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import afomic.com.camfood.ui.payment.PaymentView;

public class Test {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();

        System.out.println(findMaxGoalsProbability(list));
    }

    public static String findMaxGoalsProbability(List<Integer> teamGoals) {
        if(teamGoals.isEmpty()){
            return "0.00";
        }
        List<Integer> possibleMatches = new ArrayList<>();
        for (int i = 0; i < teamGoals.size(); i++) {
            for (int j = i+1; j < teamGoals.size(); j++) {
                possibleMatches.add((teamGoals.get(i) + teamGoals.get(j)));
            }
        }
        Collections.sort(possibleMatches);
        Integer heighestValue = possibleMatches.get(possibleMatches.size() - 1);
        double numberOfOccurence = getNumberOfoccurence(possibleMatches, heighestValue);
        double prob = numberOfOccurence / possibleMatches.size();
        return String.format(Locale.ENGLISH,"%.2f",prob) ;
    }

    public static int getNumberOfoccurence(List<Integer> integers, int value) {
        int count = 0;
        for (Integer integer : integers) {
            if (integer == value) {
                count++;
            }
        }
        return count;
    }

    static List<String> charityAllocation(List<Float> profits) {
        List<String> result = new ArrayList<>();
        double donationsToA = 0;
        double donationsToB = 0;
        double donationsToC = 0;

        for (int i = 0; i < profits.size(); i++) {

            double min = Math.min(donationsToA, Math.min(donationsToB, donationsToC));

            if (min == donationsToA) {
                donationsToA += profits.get(i);
                result.add("A");
            } else if (min == donationsToB) {
                donationsToB += profits.get(i);
                result.add("B");
            } else {
                donationsToC += profits.get(i);
                result.add("C");
            }
        }

        return result;

    }

    static List<Integer> oddNumbers(int l, int r) {
        List<Integer> result = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (i % 2 != 0) {
                result.add(i);
            }

        }
        return result;

    }

    static int alternatingCharacters(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                count++;
            }
        }
        return count;
    }

    static String isValid(String s) {
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            int index = (int) c - (int) 'a';
            ++array[index];
        }
        if (!isValid(array)) {
            return "NO";
        }
        return "YES";

    }

    public static boolean isValid(int[] array) {
        List<Integer> integerLis = trim(array);
        for (int i = 0; i < integerLis.size() - 1; i++) {
            int currentCount = integerLis.get(i);
            if (currentCount - integerLis.get(i + 1) > 1) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> trim(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int a : array) {
            if (a != 0) {
                list.add(a);
            }
        }
        return list;
    }

}
