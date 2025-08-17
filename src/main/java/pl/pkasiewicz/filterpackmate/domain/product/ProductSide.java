package pl.pkasiewicz.filterpackmate.domain.product;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.side.Side;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSide {

    @EmbeddedId
    private ProductSideId id;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("sideId")
    private Side side;

    private boolean isLotted;
}
