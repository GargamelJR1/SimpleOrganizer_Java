package org.organizerwebdb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category
{
    @Id
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String name) {
        this.categoryName = name;
    }
}
