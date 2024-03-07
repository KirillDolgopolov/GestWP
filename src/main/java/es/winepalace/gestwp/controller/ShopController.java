package es.winepalace.gestwp.controller;

import es.winepalace.gestwp.DTO.ShopDTO;
import es.winepalace.gestwp.entity.Shop;
import es.winepalace.gestwp.entity.UpdateShopRequest;
import es.winepalace.gestwp.service.IShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import es.winepalace.gestwp.service.ShopService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "api/v1/shop")
@Tag(
        name = "Shop API",
        description = "Endpoints for managing shops"
)
public class ShopController {
    private final IShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(path = "/add")
    @Operation(summary = "Save new shop")
    public ResponseEntity<Mono<Shop>> addShop(@RequestBody ShopDTO shopDTO) {
        try {
            return new ResponseEntity<>(shopService.addShop(shopDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update")
    @Operation(summary = "Update shop")
    public ResponseEntity<Mono<Shop>> updateShop(@RequestBody UpdateShopRequest updateShopRequest
    ) {
        try {
            return new ResponseEntity<>(shopService.updateShop(updateShopRequest.getId(), updateShopRequest.getShopDTO()), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/all")
    @Operation(summary = "Get all shops")
    public ResponseEntity<Flux<Shop>> getAllShops() {
        return new ResponseEntity<>(shopService.getAllShops(), HttpStatus.OK);
    }

    @GetMapping(path = "/getById")
    @Operation(summary = "Get shop by id")
    public Mono<ResponseEntity<Shop>> getShopById(@RequestBody Integer id) {

        return shopService.getShopById(id)
                .map(shop -> new ResponseEntity<>(shop, HttpStatus.FOUND))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop not found")));

    }


    @DeleteMapping(path = "/delete")
    @Operation(summary = "Delete shop by id")
    public Mono<Void> deleteShop(@RequestBody Integer id) {
        return shopService.deleteShop(id);
    }


}
