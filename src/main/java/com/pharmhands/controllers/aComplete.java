package com.pharmhands.controllers;

import com.pharmhands.models.Drugs;
import com.pharmhands.repositories.DrugsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class aComplete {

    private final DrugsRepository drugDao;

    public aComplete(DrugsRepository drugDao) {
        this.drugDao = drugDao;
    }

    @GetMapping("/list-drugs")
    @ResponseBody
    public List<Drugs> listDrugs() {
        return drugDao.findAll();
        for (Drug:Drugs

             ) {

        }
    }


    @GetMapping("/Autocomplete")
        public String aform(){return "views/AutoComplete";}

//    @RequestMapping(value ="/DrugAutocomplete")
//    @ResponseBody
//    public List<String> DrugAutocomplete(@RequestParam (value="term",required =false,defaultValue="")String term){
//    List<String> suggestions = new ArrayList<>();
//    suggestions.add("abcerre");
//    suggestions.add("def");
//    suggestions.add("ghi");
//    suggestions.add("imn");
//    return suggestions;
//    }


}
