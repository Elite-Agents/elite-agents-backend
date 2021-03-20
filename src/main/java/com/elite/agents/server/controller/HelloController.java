package com.elite.agents.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public Map<String, String> getSlash() {
        Map<String, String> response = new HashMap<>();

        response.put("welcome", "StartHack21");
        response.put("team", "EliteAgents");
        response.put("case", "MercedesBenz");

        return response;
    }

    @GetMapping("video")
    public Map<String, String> getVideo() {
        Map<String, String> response = new HashMap<>();

        response.put("welcome", "StartHack21");
        response.put("team", "EliteAgents");
        response.put("case", "MercedesBenz");

        return response;
    }
}
