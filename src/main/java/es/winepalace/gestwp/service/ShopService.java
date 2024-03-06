package es.winepalace.gestwp.service;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import es.winepalace.gestwp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
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
    public Mono<Shop> updateShop(Integer id, ShopDTO shopDTO) {
        return shopRepository.findById(id)
                .flatMap(shop -> {
                    shop.setShopName(shopDTO.getShopName());
                    shop.setShopAddress(shopDTO.getShopAddress());
                    shop.setShopPhone(shopDTO.getShopPhone());
                    shop.setShopEmail(shopDTO.getShopEmail());
                    return shopRepository.save(shop);
                });
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
    public Mono<Void> deleteShop(Integer id) {
        return shopRepository.deleteById(id);
    }
}