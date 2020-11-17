package com.sofiamed.algorithm.dnacode.controller;

import com.sofiamed.algorithm.dnacode.model.DNA;
import com.sofiamed.algorithm.dnacode.service.DNAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dna-code-compute")
public class DnaCodeComputationController {
    @Autowired
    private DNAService dnaService;

    @PostMapping(value = "/disorder-possibility")
    public Double computeDna(@RequestBody DNA dna) {
        return dnaService.getGeneticDisorderPossibility(dna);
    }
}
