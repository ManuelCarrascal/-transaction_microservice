package emazon.transaction.ports.persistence.mysql.repository;

import emazon.transaction.ports.persistence.mysql.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplyRepository extends JpaRepository<SupplyEntity, Long> {
}
