package emazon.transaction.ports.persistence.mysql.repository;


import emazon.transaction.ports.persistence.mysql.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository  extends JpaRepository<SaleEntity, Long> {

}
