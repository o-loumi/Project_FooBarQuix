package com.example.FooBarQuix.controller;

import com.example.FooBarQuix.service.NumberService;
import com.example.FooBarQuix.service.impl.NumberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NumberController {
     private final NumberService numberService;

    @Operation(summary = "Get transformed number", description = "Fetches the transformed number based on input.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the transformed number"),
            @ApiResponse(responseCode = "400", description = "Invalid number format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{number}")
    public ResponseEntity<String> transformNumber(@PathVariable int number) {
        if (number < 0 || number > 100) {
            return ResponseEntity.badRequest().body("Le nombre doit être compris entre 0 et 100.");
        }

        String result = numberService.transformNumber(number);
        return ResponseEntity.ok(result);
    }
}
