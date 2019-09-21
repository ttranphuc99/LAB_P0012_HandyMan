/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Thien Phuc
 */
public class ServiceDTO implements Serializable {
    private int id;
    private String name, image, description;
    private float price;
    private Timestamp publishDate;

    public ServiceDTO() {
    }

    public ServiceDTO(int id, String name, String image, String description, float price, Timestamp publishDate) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.publishDate = publishDate;
    }

    public ServiceDTO(String name, String image, String description, float price, Timestamp publishDate) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.publishDate = publishDate;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }
    
    
}
