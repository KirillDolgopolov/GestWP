package es.winepalace.gestwp.entity;

import es.winepalace.gestwp.DTO.ShopDTO;
import lombok.Data;

@Data

public class UpdateShopRequest {
    private Integer id;
    private ShopDTO shopDTO;
}
