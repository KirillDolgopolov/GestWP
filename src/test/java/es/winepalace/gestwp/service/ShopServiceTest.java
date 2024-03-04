package es.winepalace.gestwp.service;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import es.winepalace.gestwp.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ShopServiceTest {

    private ShopService shopService;
    private ShopRepository shopRepository;

    @BeforeEach
    void setUp() {
        shopRepository = Mockito.mock(ShopRepository.class);
        shopService = new ShopService(shopRepository);

        Shop shop = Shop.builder()
                .shopID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("email@wp.es")
                .build();
        when(shopRepository.save(any(Shop.class))).thenReturn(Mono.just(shop));
    }


    @Test
    void addShop() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopName("Test Shop");
        shopDTO.setShopAddress("Calle de la Test Shop 13, Barcelona");
        shopDTO.setShopPhone("999 88 77 66");
        shopDTO.setShopEmail("email@wp.es");

        Mono<Shop> response = shopService.addShop(shopDTO);

        StepVerifier.create(response)
                .assertNext(shop -> {
                    assertEquals("Test Shop", shop.getShopName());
                    assertEquals("Calle de la Test Shop 13, Barcelona", shop.getShopAddress());
                    assertEquals("999 88 77 66", shop.getShopPhone());
                    assertEquals("email@wp.es", shop.getShopEmail());
                })
                .verifyComplete();
    }
}