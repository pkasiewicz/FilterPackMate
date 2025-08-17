package pl.pkasiewicz.filterpackmate.domain.product;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.corner.Corner;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCorner {

    @EmbeddedId
    private ProductCornerId id;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("cornerId")
    private Corner corner;

    private boolean isLotted;
}

