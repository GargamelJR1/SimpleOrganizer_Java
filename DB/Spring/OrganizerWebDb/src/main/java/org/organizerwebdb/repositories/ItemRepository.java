package org.organizerwebdb.repositories;

import jakarta.transaction.Transactional;
import org.organizerwebdb.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>
{
    @Transactional
    long deleteItemById(Long id);
}
