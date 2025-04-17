package pl.pkasiewicz.filterpackmate.domain.tray;

import jakarta.persistence.*;
import lombok.Data;
import pl.pkasiewicz.filterpackmate.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Tray {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "tray")
    List<Product> products = new ArrayList<>();
}
