package com.lucas.beerstock.service;

import com.lucas.beerstock.builder.BeerDTOBuilder;
import com.lucas.beerstock.dto.BeerDTO;
import com.lucas.beerstock.entity.Beer;
import com.lucas.beerstock.exception.BeerAlreadyRegisteredException;
import com.lucas.beerstock.mapper.BeerMapper;
import com.lucas.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {
    private static final long INVALID_BEER_ID = 1L;

    @Mock
    private BeerRepository beerRepository;

    private BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private BeerService beerService;

    @Test
    void WhenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
        // Given
        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedSavedBeer = beerMapper.toModel(beerDTO);

        //When
        when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());
        when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);

        //Then
        BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

        assertEquals(beerDTO.getId(), createdBeerDTO.getId());
        assertEquals(beerDTO.getName(), createdBeerDTO.getName());

    }
}
