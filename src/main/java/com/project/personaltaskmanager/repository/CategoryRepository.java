package com.project.personaltaskmanager.repository;

import com.project.personaltaskmanager.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameIgnoreCase(String name);

    List<Category> findByActiveTrueOrderByName();

    List<Category> findAllByOrderByName();

    boolean existsByNameIgnoreCase(String name);

    long countByActiveTrue();

    @Query("SELECT c FROM Category c WHERE " +
            "c.active = true AND " +
            "(LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Category> findByNameOrDescriptionContainingIgnoreCase(@Param("searchTerm") String searchTerm);

    @Query("SELECT c, COUNT(t.id) as taskCount FROM Category c " +
            "LEFT JOIN c.tasks t ON t.active = true " +
            "WHERE c.active = true " +
            "GROUP BY c.id, c.name, c.description, c.color, c.active, c.createdAt, c.updatedAt " +
            "ORDER BY c.name")
    List<Object[]> findCategoriesWithTaskCount();

    @Query("SELECT c FROM Category c WHERE c.active = true AND " +
            "(SELECT COUNT(t) FROM Task t WHERE t.category = c AND t.active = true) = 0")
    List<Category> findEmptyCategories();

    @Query("SELECT DISTINCT c FROM Category c JOIN c.tasks t WHERE c.active = true AND t.active = true")
    List<Category> findCategoriesWithTasks();

    @Query(value = "SELECT c.* FROM categories c " +
            "LEFT JOIN tasks t ON c.id = t.category_id AND t.active = true " +
            "WHERE c.active = true " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(t.id) DESC " +
            "LIMIT :limit", nativeQuery = true)
    List<Category> findMostUsedCategories(@Param("limit") int limit);

    List<Category> findByColorAndActiveTrue(String color);

    @Query("SELECT c.id, c.name, " +
            "COUNT(CASE WHEN t.status = 'PENDING' THEN 1 END) as pendingCount, " +
            "COUNT(CASE WHEN t.status = 'IN_PROGRESS' THEN 1 END) as inProgressCount, " +
            "COUNT(CASE WHEN t.status = 'COMPLETED' THEN 1 END) as completedCount, " +
            "COUNT(CASE WHEN t.status = 'CANCELLED' THEN 1 END) as cancelledCount " +
            "FROM Category c LEFT JOIN c.tasks t ON t.active = true " +
            "WHERE c.id = :categoryId AND c.active = true " +
            "GROUP BY c.id, c.name")
    Object[] getCategoryStatistics(@Param("categoryId") Long categoryId);
}