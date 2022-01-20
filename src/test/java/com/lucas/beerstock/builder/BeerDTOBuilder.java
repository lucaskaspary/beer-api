package com.lucas.beerstock.builder;

import com.lucas.beerstock.dto.BeerDTO;
import com.lucas.beerstock.enums.BeerType;
import lombok.Builder;


@Builder
public class BeerDTOBuilder {
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Polar";

    @Builder.Default
    private String brand = "Ambev";

    @Builder.Default
    private Integer max = 50;

    @Builder.Default
    private Integer quantity = 10;

    @Builder.Default
    private BeerType type = BeerType.LAGER;

    public BeerDTO toBeerDTO(){
        return new BeerDTO(id,name,brand,max,quantity,type);
    }

}
