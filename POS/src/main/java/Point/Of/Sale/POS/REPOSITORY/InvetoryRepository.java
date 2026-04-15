package Point.Of.Sale.POS.REPOSITORY;

import Point.Of.Sale.POS.ENTITY.Invetory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvetoryRepository extends JpaRepository<Invetory,Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Invetory i SET i.quantity = i.quantity - :qty WHERE i.id = :id AND i.quantity >= :qty")
    int punguzaStock(@Param("id") Long id, @Param("qty") int qty);
}
