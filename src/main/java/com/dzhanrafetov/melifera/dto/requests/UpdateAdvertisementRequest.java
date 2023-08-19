package com.dzhanrafetov.melifera.dto.requests;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class UpdateAdvertisementRequest {
    @NotBlank
    @Pattern(
            regexp = "([A-Za-z0-9-\\s]{10,}+)",
            message = "Error Advertisement title format")
    private String title;


    @NotBlank
    @Pattern(
            regexp = "([A-Za-z0-9-\\s]{10,}+)",
            message = "Error Advertisement description format")
    private String description;

    @NotNull
    @DecimalMin("0")
    private BigDecimal price;

    public UpdateAdvertisementRequest() {
    }

    public UpdateAdvertisementRequest(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
