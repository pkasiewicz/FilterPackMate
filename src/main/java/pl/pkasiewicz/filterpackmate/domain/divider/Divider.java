package pl.pkasiewicz.filterpackmate.domain.divider;

import jakarta.persistence.*;
import lombok.Data;
import pl.pkasiewicz.filterpackmate.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Divider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @ManyToMany(mappedBy = "dividers")
    List<Product> products = new ArrayList<>();
}
