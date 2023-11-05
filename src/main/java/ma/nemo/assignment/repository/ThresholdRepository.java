package ma.nemo.assignment.repository;

import ma.nemo.assignment.domain.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThresholdRepository extends JpaRepository<Threshold,Long> {
}
