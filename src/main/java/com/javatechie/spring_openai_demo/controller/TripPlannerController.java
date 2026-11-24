package com.javatechie.spring_openai_demo.controller;

import com.javatechie.spring_openai_demo.dto.TripPlan;
import com.javatechie.spring_openai_demo.service.TripPlannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trips")
public class TripPlannerController {
    private final TripPlannerService tripPlannerService;

    public TripPlannerController(TripPlannerService tripPlannerService) {
        this.tripPlannerService = tripPlannerService;
    }

    @GetMapping("/plan-trip")
    public TripPlan getTripPlans(@RequestParam String message) {
        return tripPlannerService.getTripPlans(message);
    }

    @GetMapping("/trip-spots")
    public List<String> getTripSports(@RequestParam String message) {
        return tripPlannerService.getTripSports(message);
    }

    @GetMapping("/trip-guide")
    public Map<String, Object> getTripGuide(@RequestParam String message) {
        return tripPlannerService.getTripGuide(message);
    }

    @GetMapping("/complete-trip-plans")
    public List<TripPlan> completeTripPlans(@RequestParam String message) {
        return tripPlannerService.completeTripPlans(message);
    }

}
