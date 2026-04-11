package Point.Of.Sale.POS.REPOSITORY;

import Point.Of.Sale.POS.ENTITY.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
}
