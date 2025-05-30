package pl.pkasiewicz.filterpackmate.domain.corner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CornerRepository extends JpaRepository<Corner, Long> {
    boolean existsByName(String name);
}
