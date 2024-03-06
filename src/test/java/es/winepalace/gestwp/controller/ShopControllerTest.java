package es.winepalace.gestwp.controller;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import es.winepalace.gestwp.service.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ShopControllerTest {


    private ShopController shopController;
    private ShopService shopService;


    @BeforeEach
    void setUp() {
        shopService = Mockito.mock(ShopService.class);
        shopController = new ShopController(shopService);

        Shop shop = Shop.builder()
                .ID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("email@wp.es")
                .build();
        when(shopService.addShop(any(ShopDTO.class))).thenReturn(Mono.just(shop));
    }

    @Test
    void addShop() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopName("Test Shop");
        shopDTO.setShopAddress("Calle de la Test Shop 13, Barcelona");
        shopDTO.setShopPhone("999 88 77 66");
        shopDTO.setShopEmail("email@wp.es");

        Mono<Shop> response = shopController.addShop(shopDTO).getBody();

        assertEquals("Test Shop", response.block().getShopName());
        assertEquals("Calle de la Test Shop 13, Barcelona", response.block().getShopAddress());
        assertEquals("999 88 77 66", response.block().getShopPhone());
        assertEquals("email@wp.es", response.block().getShopEmail());
    }
}