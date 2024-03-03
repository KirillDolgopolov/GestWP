package es.winepalace.gestwp.service;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import es.winepalace.gestwp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Shop addShop(ShopDTO shopDTO) {

        Shop shopToSave = Shop.builder()
                .shopName(shopDTO.getShopName())
                .shopAddress(shopDTO.getShopAddress())
                .shopPhone(shopDTO.getShopPhone())
                .shopEmail(shopDTO.getShopEmail())
                .build();
        return shopRepository.save(shopToSave);
    }
}
