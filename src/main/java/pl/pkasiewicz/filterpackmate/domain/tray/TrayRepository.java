package pl.pkasiewicz.filterpackmate.domain.tray;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TrayRepository  extends JpaRepository<Tray, Long> {
}
