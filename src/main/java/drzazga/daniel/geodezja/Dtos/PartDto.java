package drzazga.daniel.geodezja.Dtos;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class PartDto {

    private Long ig;

    @NotBlank(message = "{error.partName.empty}")
    private String name;

    @NotBlank(message = "{error.partPrice.empty}")
    private BigDecimal price;

    public PartDto() {
    }

    public Long getIg() {
        return ig;
    }

    public void setIg(Long ig) {
        this.ig = ig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
