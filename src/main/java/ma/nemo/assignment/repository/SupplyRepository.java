package ma.nemo.assignment.repository;

import ma.nemo.assignment.domain.Supply;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long>{
}
