package drzazga.daniel.geodezja.Dtos;

import drzazga.daniel.geodezja.annotations.PriceIsNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PartDto {

    private Long id;

    @NotBlank(message = "{error.partName.empty}")
    private String name;

    //TODO
    // nie działają validacje(null), zapisuje jak luczba jest z kropką
    @PriceIsNumber
    @NotNull(message = "{error.partPrice.empty}")
    private BigDecimal price;

    public PartDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
