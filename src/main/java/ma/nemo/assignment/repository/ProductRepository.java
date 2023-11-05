package ma.nemo.assignment.repository;

import ma.nemo.assignment.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Product findByProductCode(String productCode);

  @Query(value = "SELECT * FROM Products WHERE expiration_date BETWEEN ?1 AND ?2", nativeQuery = true)
  List<Product> findExpiryAlerts(Date currentDate, Date expirationLimit);
}