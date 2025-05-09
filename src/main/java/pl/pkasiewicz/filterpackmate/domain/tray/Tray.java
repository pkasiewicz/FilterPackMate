package pl.pkasiewicz.filterpackmate.domain.tray;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.product.Product;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductSummaryDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tray {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "tray")
    private List<Product> products = new ArrayList<>();
}
