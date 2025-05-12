package pl.pkasiewicz.filterpackmate.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.carton.Carton;
import pl.pkasiewicz.filterpackmate.domain.corner.Corner;
import pl.pkasiewicz.filterpackmate.domain.divider.Divider;
import pl.pkasiewicz.filterpackmate.domain.side.Side;
import pl.pkasiewicz.filterpackmate.domain.tray.Tray;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "carton_id")
    private Carton carton;
    private int filtersPerCarton;
    @ManyToOne
    @JoinColumn(name = "tray_id")
    private Tray tray;
    @Enumerated(EnumType.STRING)
    private Pallet pallet;
    private int cartonsPerPallet;
    private int filtersPerPallet;
    @ManyToMany
    @JoinTable(
            name = "product_divider",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "divider_id")
    )
    private List<Divider> dividers = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "product_side",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "side_id")
    )
    private List<Side> sides = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "corner_id")
    private Corner corner;
}
