package es.winepalace.gestwp.service;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import es.winepalace.gestwp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ShopService implements IShopService {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Mono<Shop> addShop(ShopDTO shopDTO) {

        Shop shopToSave = Shop.builder()
                .shopName(shopDTO.getShopName())
                .shopAddress(shopDTO.getShopAddress())
                .shopPhone(shopDTO.getShopPhone())
                .shopEmail(shopDTO.getShopEmail())
                .build();
        return shopRepository.save(shopToSave);
    }

    @Override
    public Flux<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Mono<Shop> getShopById(Integer id) {
        return shopRepository.findById(id);
    }

    @Override
    public void deleteShop(Integer id) {
        shopRepository.deleteById(id);
    }
}
