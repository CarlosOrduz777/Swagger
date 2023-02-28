package org.adaschool.api.repository.product;

import java.util.List;

public class ProductDto {

    private final String description;
    private final String category;
    private final List<String> tags;
    private final double price;
    private final String imageUrl;
    private final String name;

    public ProductDto() {
        this.name = "";
        this.description = "";
        this.category = "";
        this.tags = null;
        this.price = 0.0;
        this.imageUrl = "";
    }

    public ProductDto(String name, String description, String category, List<String> tags, double price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.tags = tags;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getTags() {
        return tags;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
