package com.javatechie.spring_openai_demo.dto;

import java.util.List;

public record TripPlan(
        String destination,
        Integer totalDays,
        List<Plan> plans) {
}
