package pl.pkasiewicz.filterpackmate.domain.carton;

import jakarta.persistence.*;
import lombok.Data;
import pl.pkasiewicz.filterpackmate.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "carton")
    List<Product> products = new ArrayList<>();
}
