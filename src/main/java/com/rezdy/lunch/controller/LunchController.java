package com.rezdy.lunch.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rezdy.lunch.entity.RecipeEntity;
import com.rezdy.lunch.exception.LunchExceptionHandler;
import com.rezdy.lunch.service.LunchService;

/**
 * Handles the REST requests.
 *
 */
@RestController
public class LunchController {

    private final LunchService lunchService;

    @Autowired
    public LunchController(LunchService lunchService) {
        this.lunchService = lunchService;
    }

    /**
     * Gets a prepare date on which a list of relevant recipes is assembled and returned. Incorrect formatted date parameter are handled in {@linkplain LunchExceptionHandler}
     * @param date The prepare date given to collect and provide relevant recipes, allowed format: YYYY-MM-DD.
     */
    @GetMapping(value = "/lunch", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeEntity> getRecipes(@RequestParam(value = "date", required = true) String date) {
    	return lunchService.getNonExpiredRecipesOnDate(LocalDate.parse(date));
    }
}
