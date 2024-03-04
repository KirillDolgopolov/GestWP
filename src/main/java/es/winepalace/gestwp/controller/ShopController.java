package es.winepalace.gestwp.controller;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import es.winepalace.gestwp.service.ShopService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "api/v1/shop")
@Tag(
        name = "Shop API",
        description = "Endpoints for managing shops"
)
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(path = "/add")
    @Operation(summary = "Save new shop")
    public ResponseEntity<Mono<Shop>> addShop(@RequestBody ShopDTO shopDTO) {
        return new ResponseEntity<>(shopService.addShop(shopDTO), HttpStatus.CREATED);
    }
}
