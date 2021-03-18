package com.pharmhands.controllers;

import com.pharmhands.models.Drugs;
import com.pharmhands.repositories.DrugsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class aComplete {

    private final DrugsRepository drugDao;

    public aComplete(DrugsRepository drugDao) {
        this.drugDao = drugDao;
    }

    @GetMapping("/list-drugs")
    @ResponseBody
    public Stream listDrugs(@RequestParam(value="term", required=false, defaultValue = "")String term) {
        return drugDao.findAll().stream().map(drug ->drug.getDrug_name());
        }


    @GetMapping("/Autocomplete")
        public String aform(){return "views/AutoComplete";}


}
