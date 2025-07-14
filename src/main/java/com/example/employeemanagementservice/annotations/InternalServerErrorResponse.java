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
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@ApiResponses({
        @ApiResponse(responseCode = "500", description = "Internal server error(Invalid data)",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class),
                        examples = @ExampleObject(
                                name = "Internal server error",
                                summary = "Example of a 500 error response",
                                value = "{\"error\": \"Invalid data \"}"
                        )))
})
public @interface InternalServerErrorResponse {
}
