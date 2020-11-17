package com.sofiamed.algorithm.dnacode.service;

import com.sofiamed.algorithm.dnacode.model.DNA;

public interface DNAService {
    /**
     * Calculates the likelihood of human to have genetic disorder based on
     the DNA. *
     * @param dna – human dna to be checked
     * @return a number between 0 and 1 with the probability for disorder */
    Double getGeneticDisorderPossibility(DNA dna);
}
