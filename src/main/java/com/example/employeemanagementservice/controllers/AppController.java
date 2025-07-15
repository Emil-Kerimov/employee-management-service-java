package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.models.AppConfiguration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@Tag(name = "Application Info", description = "API for fetching application settings")
public class AppController {
    private final AppConfiguration appConfiguration;
    public AppController(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }
    @Operation(
            summary = "Get application info",
            description = "Returns application configuration details including version and test mode status."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved application info"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @GetMapping("info")
    public AppConfiguration getInfo() {
        return this.appConfiguration;
    }
}
