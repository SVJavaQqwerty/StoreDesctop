package product;

import java.util.Objects;

public class Product implements Comparable<Product> {
    private String id; // максимально 999_999_999
    private String name;
    private String description;
    private boolean availability;

    public Product() {
    }

    public Product(Integer id) {
        this.id = parsId(String.valueOf(id));
    }

    public Product(Integer id, String name, String description, boolean availability) {
        this.id = parsId(String.valueOf(id));
        this.name = name;
        this.description = description;
        this.availability = availability;
    }

    public void setId(Integer id) {
        this.id = parsId(String.valueOf(id));
    }

    public Integer getId() {
        return Integer.parseInt(id);
    }

    public String getStringId() {
        return id;
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

    public String getPreview(){
        return id + ": " + name;
    }

    @Override
    public String toString() {
        return "product.Tovar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", availability=" + availability +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return availability == product.availability && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, availability);
    }

    @Override
    public int compareTo(Product o) {
        return Integer.parseInt(id) - Integer.parseInt(o.id);
    }


    private String parsId(String id) {
        StringBuilder str = new StringBuilder();
        int count = id.length();
        if(count < 9) {
            int delta = 9 - count;
            for(int i = delta; i > 0; i--){
                str.append("0");
            }
            str.append(id);
        } else {
            str.append(id);
        }
        return str.toString();
    }
}
