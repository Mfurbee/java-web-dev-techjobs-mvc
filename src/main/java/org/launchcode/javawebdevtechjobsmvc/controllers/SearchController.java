package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
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

    // TODO #3 - Create a handler to process a search request and render the updated search view.
//I added 3 parameters for the model and search parameters.  I also displayed an array list of jobs.
    @RequestMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
         // If the input is blank or they type all, then all jobs are displayed.
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
         // Otherwise pass the search parameters to the find by column and value method
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        }
       //Without the column choices as attributes, the view will not display.
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);
        return "search";
    }

}
