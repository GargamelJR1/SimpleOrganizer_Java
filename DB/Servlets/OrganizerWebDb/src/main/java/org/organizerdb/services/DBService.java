package org.organizerdb.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.organizerdb.models.Category;
import org.organizerdb.models.Item;

import java.util.List;

public class DBService
{
    private static DBService instance = null;

    private DBService() {
        setEntityManager();
    }

    public static DBService getInstance() {
        if (instance == null)
            instance = new DBService();
        return instance;
    }

    private EntityManager entityManager = null;

    private void setEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void addItem(String name, String description, String categoryName) {
        entityManager.getTransaction().begin();
        Category category = entityManager.find(Category.class, categoryName);
        if (category == null) {
            category = new Category();
            category.setCategoryName(categoryName);
            entityManager.persist(category);
        }
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setCategory(category);
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }

    public boolean deleteItem(Long id) {
        entityManager.getTransaction().begin();
        Item item = entityManager.find(Item.class, id);
        if (item == null) {
            entityManager.getTransaction().commit();
            return false;
        }
        entityManager.remove(item);
        entityManager.getTransaction().commit();
        return true;
    }

    public Item getItem(Long id) {
        entityManager.getTransaction().begin();
        Item item = entityManager.find(Item.class, id);
        entityManager.getTransaction().commit();
        return item;
    }

    public boolean updateItem(Long id, String name, String description, String categoryName) {
        entityManager.getTransaction().begin();
        Item item = entityManager.find(Item.class, id);
        if (item == null) {
            entityManager.getTransaction().commit();
            return false;
        }
        Category category = entityManager.find(Category.class, categoryName);
        if (category == null) {
            category = new Category();
            category.setCategoryName(categoryName);
            entityManager.persist(category);
        }
        item.setName(name);
        item.setDescription(description);
        item.setCategory(category);
        entityManager.getTransaction().commit();
        return true;
    }

    public List<Item> getAllItems() {
        entityManager.getTransaction().begin();
        List<Item> items = entityManager.createQuery("SELECT i FROM Item i", Item.class).getResultList();
        entityManager.getTransaction().commit();
        return items;
    }

    public void deleteAllItems() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Item").executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void addCategory(String categoryName) {
        entityManager.getTransaction().begin();
        Category category = entityManager.find(Category.class, categoryName);
        if (category == null) {
            category = new Category();
            category.setCategoryName(categoryName);
            entityManager.persist(category);
        }
        entityManager.getTransaction().commit();
    }

    public boolean deleteCategory(String categoryName) {
        entityManager.getTransaction().begin();
        Category category = entityManager.find(Category.class, categoryName);
        if (category == null) {
            entityManager.getTransaction().commit();
            return false;
        }
        entityManager.remove(category);
        entityManager.getTransaction().commit();
        return true;
    }

    public List<Category> getAllCategories() {
        entityManager.getTransaction().begin();
        List<Category> categories = entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        entityManager.getTransaction().commit();
        return categories;
    }

}
