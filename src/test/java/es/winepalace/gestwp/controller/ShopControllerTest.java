package es.winepalace.gestwp.controller;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import es.winepalace.gestwp.entity.UpdateShopRequest;
import es.winepalace.gestwp.service.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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

    @Test
    void updateShop() {
        Shop shop = Shop.builder()
                .ID(1)
                .shopName("Updated Shop")
                .shopAddress("Calle de la Updated Shop 13, Barcelona")
                .shopPhone("111 22 33 44")
                .shopEmail("new_email@wp.com")
                .build();


        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopName("Updated Shop");
        shopDTO.setShopAddress("Calle de la Updated Shop 13, Barcelona");
        shopDTO.setShopPhone("111 22 33 44");
        shopDTO.setShopEmail("new_email@wp.com");

        UpdateShopRequest updateShopRequest = new UpdateShopRequest(1, shopDTO);
        when(shopService.updateShop(1, shopDTO)).thenReturn(Mono.just(shop));
        Mono<Shop> response = shopController.updateShop(updateShopRequest).getBody();
        assertEquals("Updated Shop", response.block().getShopName());
        assertEquals("Calle de la Updated Shop 13, Barcelona", response.block().getShopAddress());
        assertEquals("111 22 33 44", response.block().getShopPhone());
        assertEquals("new_email@wp.com", response.block().getShopEmail());

    }


    @Test
    void getAllShops() {
        Shop shop1 = Shop.builder()
                .ID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("shop1@gmail.com")
                .build();

        Shop shop2 = Shop.builder()
                .ID(2)
                .shopName("Test Shop 2")
                .shopAddress("Calle de la Test Shop 2 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("shop2@gmail.com")
                .build();
        when(shopService.getAllShops()).thenReturn(Flux.just(shop1, shop2));
        Flux<Shop> response = shopController.getAllShops().getBody();
        assertEquals(2, response.collectList().block().size());
        assertEquals("Test Shop", response.collectList().block().get(0).getShopName());
        assertEquals("Calle de la Test Shop 13, Barcelona", response.collectList().block().get(0).getShopAddress());
        assertEquals("999 88 77 66", response.collectList().block().get(0).getShopPhone());
        assertEquals("shop1@gmail.com", response.collectList().block().get(0).getShopEmail());
        assertEquals("Test Shop 2", response.collectList().block().get(1).getShopName());
        assertEquals("Calle de la Test Shop 2 13, Barcelona", response.collectList().block().get(1).getShopAddress());
        assertEquals("999 88 77 66", response.collectList().block().get(1).getShopPhone());
        assertEquals("shop2@gmail.com", response.collectList().block().get(1).getShopEmail());


    }

    @Test
    void getShopById() {
        Shop shop = Shop.builder()
                .ID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("wp@gmail.com")
                .build();

        when(shopService.getShopById(1)).thenReturn(Mono.just(shop));

        Mono<ResponseEntity<Shop>> responseMono = shopController.getShopById(1);

        StepVerifier.create(responseMono)
                .expectNextMatches(responseEntity -> {
                    Shop resultShop = responseEntity.getBody();
                    assertNotNull(resultShop);
                    assertEquals("Test Shop", resultShop.getShopName());
                    assertEquals("Calle de la Test Shop 13, Barcelona", resultShop.getShopAddress());
                    assertEquals("999 88 77 66", resultShop.getShopPhone());
                    return true;
                })
                .verifyComplete();
    }



    @Test
    void deleteShop() {
    }
}

