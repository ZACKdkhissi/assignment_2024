package ma.nemo.assignment.repository;

import ma.nemo.assignment.domain.Sale;
import ma.nemo.assignment.domain.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
