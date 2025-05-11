package pl.pkasiewicz.filterpackmate.domain.corner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CornerRepository extends JpaRepository<Corner, Long> {
}
