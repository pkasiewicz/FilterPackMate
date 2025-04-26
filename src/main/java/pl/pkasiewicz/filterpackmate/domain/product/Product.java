package pl.pkasiewicz.filterpackmate.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.carton.Carton;
import pl.pkasiewicz.filterpackmate.domain.divider.Divider;
import pl.pkasiewicz.filterpackmate.domain.side.Side;
import pl.pkasiewicz.filterpackmate.domain.tray.Tray;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Carton carton;
    @ManyToOne
    Tray tray;
    Pallet pallet;
    @ManyToMany
    List<Divider> dividers;
    @ManyToMany
    List<Side> sides;
}
