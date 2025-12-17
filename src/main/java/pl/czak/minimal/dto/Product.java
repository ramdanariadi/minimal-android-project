package pl.czak.minimal.dto;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private String img;
    private long price;

    public Product(String name, String img, long price) {
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public static List<Product> createDummy(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("Kawa", "https://i.imgur.com/1111111.jpg", 100));
        products.add(new Product("Kot", "https://i.imgur.com/2222222.jpg", 200));
        products.add(new Product("Pies", "https://i.imgur.com/3333333.jpg", 300));
        return products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
