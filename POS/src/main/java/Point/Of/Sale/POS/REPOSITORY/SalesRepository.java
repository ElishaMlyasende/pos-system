package Point.Of.Sale.POS.REPOSITORY;

import Point.Of.Sale.POS.ENTITY.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {


        @Query("SELECT DISTINCT s FROM Sales s LEFT JOIN FETCH s.salesItems si LEFT JOIN FETCH s.product p")
        List<Sales> findAllWithItems();
    }


