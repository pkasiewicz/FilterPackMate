package pl.pkasiewicz.filterpackmate.domain.divider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DividerRepository extends JpaRepository<Divider, Long> {
}
