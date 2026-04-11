package Point.Of.Sale.POS.REPOSITORY;

import Point.Of.Sale.POS.ENTITY.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    @EntityGraph(attributePaths = {"products", "products.invetory"})
    List<Category> findAll();
}
