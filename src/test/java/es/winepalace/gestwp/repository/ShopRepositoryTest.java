package es.winepalace.gestwp.repository;

import es.winepalace.gestwp.entity.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ShopRepositoryTest {

    @Mock
    private ShopRepository shopRepository;

    @BeforeEach
    void setUp() {


        Shop shop = Shop.builder()
                .ID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("email@wp.es")
                .build();
                when(shopRepository.save(any(Shop.class))).thenReturn(Mono.just(shop));
    }


    @Test
    void testSaveShop() {
        Shop shop = Shop.builder()
                .ID(1)
                .shopName("Test Shop")
                .shopAddress("Calle de la Test Shop 13, Barcelona")
                .shopPhone("999 88 77 66")
                .shopEmail("email@wp.es")
                .build();

        Mono<Shop> savedShop = shopRepository.save(shop);

        StepVerifier.create(savedShop)
                .assertNext(savedShop1 -> {
                    assertEquals(shop.getShopName(), savedShop1.getShopName());
                    assertEquals(shop.getShopAddress(), savedShop1.getShopAddress());
                    assertEquals(shop.getShopPhone(), savedShop1.getShopPhone());
                    assertEquals(shop.getShopEmail(), savedShop1.getShopEmail());
                })
                .verifyComplete();


    }
}