package org.organizerwebdb.repositories;

import jakarta.transaction.Transactional;
import org.organizerwebdb.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>
{
    @Transactional
    long deleteCategoryByCategoryName(String id);
}
