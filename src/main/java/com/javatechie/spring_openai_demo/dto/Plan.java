package com.javatechie.spring_openai_demo.dto;

public record Plan(String fromLocation,
                   String toLocation,
                   String transport,
                   String activities,
                   String accommodation,
                   String dateOfVisit) {
}
