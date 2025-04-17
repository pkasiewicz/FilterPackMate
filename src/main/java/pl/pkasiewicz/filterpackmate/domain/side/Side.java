package pl.pkasiewicz.filterpackmate.domain.side;

import jakarta.persistence.*;
import lombok.Data;
import pl.pkasiewicz.filterpackmate.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Side {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @ManyToMany(mappedBy = "sides")
    List<Product> products = new ArrayList<>();
}
