package es.winepalace.gestwp.repository;

import es.winepalace.gestwp.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ShopRepository extends JpaRepository<Shop, Integer>{
}
