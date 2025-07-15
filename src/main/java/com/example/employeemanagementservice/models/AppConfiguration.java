package com.example.employeemanagementservice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "settings")
@Schema(description = "App version and test data initialization")
public class AppConfiguration {
    @Schema(example = "1.0.0")
    private  String applicationVersion;
    private TestMode testMode = new TestMode();
    public String getApplicationVersion() {
        return applicationVersion;
    }
    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }
    public TestMode getTestMode() {
        return testMode;
    }
    public void setTestMode(TestMode value) {
        this.testMode = value;
    }
    @Schema(description = "Represents status of test data initialization")
    public static class TestMode
    {
        @Schema(example = "true")
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean value) {
            this.enabled = value;
        }
    }
}