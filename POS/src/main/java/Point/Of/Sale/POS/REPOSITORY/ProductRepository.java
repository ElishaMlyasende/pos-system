package Point.Of.Sale.POS.REPOSITORY;

import Point.Of.Sale.POS.ENTITY.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @EntityGraph(attributePaths = {"category", "invetory"}) // Vuta category na inventory kwa pamoja
    List<Product> findAll();
    @Modifying
    @Transactional
    @Query(value = "UPDATE invetory  SET quantity = 0 WHERE product_id IN (SELECT id FROM products WHERE perishable = true)", nativeQuery = true)
    void resetPerishable();
}
