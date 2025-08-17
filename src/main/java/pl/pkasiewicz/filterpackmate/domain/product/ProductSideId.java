package pl.pkasiewicz.filterpackmate.domain.product;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSideId implements Serializable {
    private Long productId;
    private Long sideId;
}