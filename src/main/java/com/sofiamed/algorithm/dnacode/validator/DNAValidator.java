package com.sofiamed.algorithm.dnacode.validator;

import com.sofiamed.algorithm.dnacode.model.DNA;
import com.sofiamed.algorithm.dnacode.model.ECodeDna;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DNAValidator {

    public void validate(DNA dna) {
        if (dna == null || dna.getCode() == null) {
            throw new NullPointerException("DNA cannot be null.");
        }

        String dnaCode = dna.getCode();
        List<String> permittedCodes = Arrays.stream(ECodeDna.values())
                .map(eCodeDna -> eCodeDna.name())
                .collect(Collectors.toList());


        for (int i = 0; i < dnaCode.length(); i++) {
            String current = String.valueOf(dnaCode.charAt(i));
            if (!permittedCodes.contains(current)) {
                throw new IllegalArgumentException("The DNA contains wrong character");
            }
        }
    }
}
