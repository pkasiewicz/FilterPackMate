package pl.pkasiewicz.filterpackmate.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository  extends JpaRepository<Product, Long> {
}
