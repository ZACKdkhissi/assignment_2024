package ma.nemo.assignment.repository;

import ma.nemo.assignment.domain.Return;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRapository extends JpaRepository<Return,Long> {

}
