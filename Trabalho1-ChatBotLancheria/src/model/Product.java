package model;

/**
 *
 * @author alcsaw
 */
public class Product {
    private int id;
    private Category category;
    private String description;
    private String imagePath;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(int id, Category category, String description, String imagePath, double price) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return this.description;
    }
    
    
}
