package pl.pkasiewicz.filterpackmate.domain.carton;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartonRepository extends JpaRepository<Carton, Long> {
    boolean existsByName(String name);
}
