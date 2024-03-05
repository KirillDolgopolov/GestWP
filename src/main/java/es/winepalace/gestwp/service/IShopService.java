package es.winepalace.gestwp.service;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IShopService {
    public Mono<Shop> addShop(ShopDTO shopDTO);
    public Flux<Shop> getAllShops();
    public Mono<Shop> getShopById(Integer id);
    public void deleteShop(Integer id);
}
