package ma.nemo.assignment.repository;

import ma.nemo.assignment.domain.TransactionHistory;
import ma.nemo.assignment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
}
