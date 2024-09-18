package emazon.transaction.ports.persistence.mysql.repository;

import emazon.transaction.ports.persistence.mysql.entity.SupplyEntity;
import emazon.transaction.ports.persistence.mysql.util.SupplyRepositoryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ISupplyRepository extends JpaRepository<SupplyEntity, Long> {

    @Query(SupplyRepositoryConstants.FIND_NEXT_SUPPLY_DATE_BY_PRODUCT_ID_QUERY)
    Date findNextSupplyDateByProductId(@Param(SupplyRepositoryConstants.PRODUCT_ID_PARAM) Long productId);
}
