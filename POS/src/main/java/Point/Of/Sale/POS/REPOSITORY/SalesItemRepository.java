package Point.Of.Sale.POS.REPOSITORY;

import Point.Of.Sale.POS.ENTITY.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesItemRepository extends JpaRepository<SalesItem,Long> {
}
