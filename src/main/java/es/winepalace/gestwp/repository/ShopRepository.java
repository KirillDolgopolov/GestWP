package es.winepalace.gestwp.repository;

import es.winepalace.gestwp.entity.Shop;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends R2dbcRepository<Shop, Integer> {
}