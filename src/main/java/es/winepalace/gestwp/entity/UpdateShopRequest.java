package es.winepalace.gestwp.entity;

import es.winepalace.gestwp.DTO.ShopDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UpdateShopRequest {
    private Integer id;
    private ShopDTO shopDTO;
}
