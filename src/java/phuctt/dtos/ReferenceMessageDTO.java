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
public class ReferenceMessageDTO implements Serializable {
    private int id;
    private String name, email, website, message, image;
    private Timestamp createdTime;

    public ReferenceMessageDTO() {
    }

    public ReferenceMessageDTO(int id, String name, String email, String website, String message, String image, Timestamp createdTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.website = website;
        this.message = message;
        this.image = image;
        this.createdTime = createdTime;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
    
    
}
