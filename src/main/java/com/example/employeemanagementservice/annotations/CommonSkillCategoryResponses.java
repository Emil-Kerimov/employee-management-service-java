package com.example.employeemanagementservice.annotations;

import com.example.employeemanagementservice.exceptions.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK. Operation executed successfully"),
        @ApiResponse(responseCode = "404", description = "No Skill Category with this ID was found.",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class),
                        examples = @ExampleObject(
                                name = "Not Found Error",
                                summary = "Example of a 404 error response",
                                value = "{\"error\": \"Skill Category with id 1 not found\"}"
                        )))
})
public @interface CommonSkillCategoryResponses {
}
