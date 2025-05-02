package pl.pkasiewicz.filterpackmate.domain.side;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SideRepository extends JpaRepository<Side, Long> {
}
