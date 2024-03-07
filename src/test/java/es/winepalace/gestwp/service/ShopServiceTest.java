package es.winepalace.gestwp.service;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import es.winepalace.gestwp.entity.UpdateShopRequest;
import es.winepalace.gestwp.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ShopServiceTest {

    private IShopService shopService;
    private ShopRepository shopRepository;

    @BeforeEach
    void setUp() {
        shopRepository = Mockito.mock(ShopRepository.class);
        shopService = new ShopService(shopRepository);
    }


    @Test
    void addShop() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopName("Test Shop");
        shopDTO.setShopAddress("Calle de la Test Shop 13, Barcelona");
        shopDTO.setShopPhone("999 88 77 66");
        shopDTO.setShopEmail("email@wp.es");

        Shop shop = Shop.builder()
                .ID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("email@wp.es")
                .build();

        when(shopRepository.save(any(Shop.class))).thenReturn(Mono.just(shop));

        Mono<Shop> response = shopService.addShop(shopDTO);

        StepVerifier.create(response)
                .assertNext(shopToAssert -> {
                    assertEquals("Test Shop", shop.getShopName());
                    assertEquals("Calle de la Test Shop 13, Barcelona", shop.getShopAddress());
                    assertEquals("999 88 77 66", shop.getShopPhone());
                    assertEquals("email@wp.es", shop.getShopEmail());
                })
                .verifyComplete();
    }


    @Test
    void updateShop() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopName("Update Shop");
        shopDTO.setShopAddress("Update address");
        shopDTO.setShopPhone("4812132332");
        shopDTO.setShopEmail("new@mail.com");
        Integer id = 1;

        Shop shop = Shop.builder()
                .ID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("email@wp.es")
                .build();

        Shop updatedShop = Shop.builder()
                .ID(1)
                .shopName("Update Shop")
                .shopAddress("Update address")
                .shopPhone("4812132332")
                .shopEmail("new@mail.com")
                .build();

        when(shopRepository.findById(1)).thenReturn(Mono.just(shop));
        when(shopRepository.save(any(Shop.class))).thenReturn(Mono.just(updatedShop));

        Mono<Shop> response = shopService.updateShop(id, shopDTO);

        StepVerifier.create(response)
                .assertNext(shopToAssert -> {
                    assertEquals("Update Shop", shop.getShopName());
                    assertEquals("Update address", shop.getShopAddress());
                    assertEquals("4812132332", shop.getShopPhone());
                    assertEquals("new@mail.com", shop.getShopEmail());
                    assertEquals(1, shop.getID());
                })
                .verifyComplete();
    }

    @Test
    void findShopById() {
        Shop shop = Shop.builder()
                .ID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("email@wp.es")
                .build();

        when(shopRepository.findById(1)).thenReturn(Mono.just(shop));

        Mono<Shop> response = shopService.getShopById(1);

        StepVerifier.create(response)
                .assertNext(shopToAssert -> {
                    assertEquals("Test Shop", shop.getShopName());
                    assertEquals("Calle de la Test Shop 13, Barcelona", shop.getShopAddress());
                    assertEquals("999 88 77 66", shop.getShopPhone());
                    assertEquals("email@wp.es", shop.getShopEmail());
                    assertEquals(1, shop.getID());
                })
                .verifyComplete();
    }

    @Test
    void deleteShop() {
        when(shopRepository.deleteById(1)).thenReturn(Mono.empty());

        Mono<Void> response = shopService.deleteShop(1);

        StepVerifier.create(response)
                .verifyComplete();
    }

    @Test
    void getAllShops() {
        Shop shop = Shop.builder()
                .ID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("email@wp.es")
                .build();

        Shop shop2 = Shop.builder()
                .ID(2)
                .shopName("Test Shop2")
                .shopAddress("Calle de la Test Shop 14, Barcelona")
                .shopPhone("999 88 77 67")
                .shopEmail("email2@wp.es")
                .build();

        when(shopRepository.findAll()).thenReturn(Flux.just(shop, shop2));
        StepVerifier.create(shopService.getAllShops())
                .expectNext(shop)
                .expectNext(shop2)
                .verifyComplete();
    }


}
