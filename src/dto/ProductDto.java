package dto;

import product.Product;

public class ProductDto {
    // Data Transfer Object
    private Integer id;
    private String strId;
    private String name;
    private String description;
    private boolean availability;
    private Integer count;

    public ProductDto(Integer id, Product p, Integer count) {
        this.id = id;
        this.strId = p.getStringId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.availability = p.isAvailability();
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProduct() {
        // id; product: id, name, availability; count
        return id + ";product:" + strId + "," + name + "," + description + "," + availability + ";" + count;
    }
}
