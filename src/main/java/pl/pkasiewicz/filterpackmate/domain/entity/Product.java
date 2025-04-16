package pl.pkasiewicz.filterpackmate.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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
