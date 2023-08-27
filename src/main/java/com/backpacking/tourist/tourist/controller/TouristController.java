package com.backpacking.tourist.tourist.controller;

import com.backpacking.tourist.tourist.dto.CreateTourist;
import com.backpacking.tourist.tourist.service.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tourist")
@RequiredArgsConstructor
public class TouristController {

    private final TouristService touristService;

    @PostMapping("/create")
    public CreateTourist.Response register(
            @RequestBody CreateTourist.Request request){
        return CreateTourist.Response.fromEntity(
                touristService.register(request));
    }
    

}
