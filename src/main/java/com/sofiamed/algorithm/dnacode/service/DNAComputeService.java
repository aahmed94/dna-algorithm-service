package com.sofiamed.algorithm.dnacode.service;

import com.sofiamed.algorithm.dnacode.model.DNA;
import com.sofiamed.algorithm.dnacode.validator.DNAValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class DNAComputeService implements DNAService {

    private static final DecimalFormat RESULT_FORMAT = new DecimalFormat("#.##");

    @Autowired
    private DNAValidator dnaValidator;

    @Override
    public Double getGeneticDisorderPossibility(DNA dna) {
        dnaValidator.validate(dna);

        String originalString = dna.getCode();
        String reversedString = new StringBuilder(originalString).reverse().toString();

        /*
        If the original dna code and the reversed one match exactly,
        then there is a 100% percent chance of illness, hence 1 is returned
        */
        if (originalString.equals(reversedString)) {
            return 1d;
        }

        Set<String> originalStringSet = buildOriginalSet(originalString);
        Set<String> reversedStringSet = buildSet(reversedString);

        originalStringSet.retainAll(reversedStringSet);

        // Finding the longest length of a matching string from both sets
        double longestStringLength = originalStringSet
                .stream().mapToDouble(String::length).filter(string -> string >= 0).max().orElse(0);

        return percentage(longestStringLength, originalString.length());
    }

    /** Calculates the disorder probability by diving
     * the longest found match from the reversed and original dna code
     * to the original dna code string length
     *
     * @param longestSubstringLength The longest found match from the reversed and original dna code
     * @param originalStringLength The original dna code string length
     * @return double The probability of genetic disorder represented in a number between 0 and 1
     * */
    public double percentage(double longestSubstringLength, double originalStringLength) {
        return Double.parseDouble(RESULT_FORMAT.format(longestSubstringLength / originalStringLength));
    }

    /** Getting every substring with the first character of the original dna.
     *  A substring is considered a string with a minimum of 2 characters.
     *
     * @param originalDnaString The dna code
     * @return Set<String> Return a set with substrings starting with the first character
     * */
    public Set<String> buildOriginalSet(String originalDnaString) {
        Set<String> resultSet = new TreeSet<>();
        int i = 0;
        for (int j = i + 1; j < originalDnaString.length(); j++) {
            String temp = originalDnaString.substring(i, j);
            if (temp.length() >= 2) {
                resultSet.add(temp);
            }
        }

        return resultSet;
    }

    /** Getting every substring of the reversed dna.
     *  A substring is considered a string with a minimum of 2 characters.
     *
     * @param reversedDnaString The reversed dna code
     * @return Set<String> Return a set with every substring of the reversed DNA code.
     * */
    public Set<String> buildSet(String reversedDnaString) {
        Set<String> resultSet = new HashSet<>();
        for (int i = 0; i < reversedDnaString.length(); i++) {
            for (int j = i + 1; j <= reversedDnaString.length(); j++) {
                String temp = reversedDnaString.substring(i, j);
                if (temp.length() >= 2) {
                    resultSet.add(temp);
                }
            }
        }
        return resultSet;
    }
}
