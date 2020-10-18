package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

//    // TODO #3 - Create a handler to process a search request and render the updated search view.
//    @RequestMapping(value="search/results", method = {RequestMethod.GET, RequestMethod.POST})
//    @PostMapping(value="search/results")
    @PostMapping(value="results")
    public String displaySearchResults(String searchType, String searchTerm, Model model) {
        ArrayList<Job> jobs;
        if (searchTerm.contentEquals("all") || searchTerm.contentEquals("") || searchTerm.isEmpty()) {
            jobs = JobData.findAll();
        }else{
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchType", searchType);
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search/results";
    }

}
