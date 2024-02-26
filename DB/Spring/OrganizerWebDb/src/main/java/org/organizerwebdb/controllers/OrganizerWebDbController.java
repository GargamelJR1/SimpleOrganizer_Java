package org.organizerwebdb.controllers;

import org.organizerwebdb.models.Category;
import org.organizerwebdb.models.Item;
import org.organizerwebdb.services.CategoryService;
import org.organizerwebdb.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class OrganizerWebDbController
{
    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/organizerwebdb")
    public String organizerWebDb() {
        return "organizerwebdb";
    }

    @GetMapping("/organizerwebdb/item-list")
    public String itemList() {
        return "item-list";
    }

    @GetMapping("/organizerwebdb/category-list")
    public String categoryList() {
        return "category-list";
    }

    @GetMapping("/organizerwebdb/item/{id:\\d*}")
    public ResponseEntity<Item> FindOne(@PathVariable("id") Long id) {
        Optional<Item> item = itemService.getOneItem(id);

        return item.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/organizerwebdb/items")
    public ResponseEntity<List<Item>> FindAll() {
        List<Item> items = itemService.getAllItems();
        if (items != null) {
            return ResponseEntity.status(HttpStatus.OK).body(items);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/organizerwebdb/delete-item/{id:\\d*}")
    public ResponseEntity<Item> DeleteItem(@PathVariable("id") Long id) {
        boolean deleted = itemService.deleteItem(id);
        return deleted ? ResponseEntity.status(HttpStatus.OK).body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/organizerwebdb/add-item")
    public ResponseEntity<Item> CreateItem(@RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("category") String category) {
        Category itemCategory = new Category();
        itemCategory.setCategoryName(category);
        Optional<Category> category_saved = categoryService.createCategory(itemCategory);
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        if (category_saved.isEmpty()) {
            item.setCategory(null);
        }
        else {
            item.setCategory(category_saved.get());
        }
        Optional<Item> item_saved = itemService.createItem(item);
        return item_saved.map(value -> ResponseEntity.status(HttpStatus.CREATED).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/organizerwebdb/update-item/{id:\\d*}")
    public ResponseEntity<Item> ModifyItem(@PathVariable("id") Long id,
                                           @RequestParam("name") String new_name,
                                           @RequestParam("description") String new_description,
                                           @RequestParam("category") String category) {
        Optional<Item> item_to_modify = itemService.getOneItem(id);
        if (item_to_modify.isPresent()) {

            Category itemCategory = new Category();
            itemCategory.setCategoryName(category);
            Optional<Category> category_saved = categoryService.createCategory(itemCategory);
            Item item = new Item();
            item.setName(new_name);
            item.setDescription(new_description);
            if (category_saved.isEmpty()) {
                item.setCategory(null);
            }
            else {
                item.setCategory(category_saved.get());
            }
            Optional<Item> item_saved = itemService.modifyItem(item);
            return item_saved.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(
                    () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/organizerwebdb/delete-all-items")
    public ResponseEntity<Item> DeleteAllItems() {
        itemService.deleteAllItems();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/organizerwebdb/categories")
    public ResponseEntity<List<Category>> FindAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories != null) {
            return ResponseEntity.status(HttpStatus.OK).body(categories);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/organizerwebdb/delete-category/{id}")
    public ResponseEntity<Category> DeleteCategory(@PathVariable("id") String id) {
        boolean deleted = categoryService.deleteCategory(id);
        return deleted ? ResponseEntity.status(HttpStatus.OK).body(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/organizerwebdb/background-color")
    public ResponseEntity<String> GetBackgroundColor(@RequestParam("color") String color,
                                                     @CookieValue(name = "background-color", defaultValue = "white") String cookieValue) {
        if (color.isBlank()) {
            return ResponseEntity.status(HttpStatus.OK).body(cookieValue);
        }
        ResponseCookie cookie = ResponseCookie
                .from("background-color", color)
                .domain("localhost")
                .maxAge(60 * 60 * 24)
                .build();
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, cookie.toString()).body(color);
    }

    @PostMapping("/organizerwebdb/font-family")
    public ResponseEntity<String> GetFontFamily(@RequestParam("font") String font,
                                                @CookieValue(name = "font-family", defaultValue = "serif") String cookieValue) {
        if (font.isBlank()) {
            return ResponseEntity.status(HttpStatus.OK).body(cookieValue);
        }
        ResponseCookie cookie = ResponseCookie
                .from("font-family", font)
                .domain("localhost")
                .maxAge(60 * 60 * 24)
                .build();
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, cookie.toString()).body(font);
    }
}
