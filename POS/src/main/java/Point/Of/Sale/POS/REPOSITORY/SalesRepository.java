package Point.Of.Sale.POS.REPOSITORY;

import Point.Of.Sale.POS.ENTITY.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
    // Leo
    @Query(value = "SELECT COALESCE(SUM(total_amount), 0) FROM sales WHERE DATE(created_at) = CURDATE()", nativeQuery = true)
    Double sumTodaySales();

    // Wiki hii
    @Query(value = "SELECT COALESCE(SUM(total_amount), 0) FROM sales WHERE WEEK(created_at, 1) = WEEK(CURDATE(), 1) AND YEAR(created_at) = YEAR(CURDATE())", nativeQuery = true)
    Double sumWeeklySales();

    // Mwezi huu
    @Query(value = "SELECT COALESCE(SUM(total_amount), 0) FROM sales WHERE MONTH(created_at) = MONTH(CURDATE()) AND YEAR(created_at) = YEAR(CURDATE())", nativeQuery = true)
    Double sumMonthlySales();

    // Mwaka huu
    @Query(value = "SELECT COALESCE(SUM(total_amount), 0) FROM sales WHERE YEAR(created_at) = YEAR(CURDATE())", nativeQuery = true)
    Double sumYearlySales();

}


