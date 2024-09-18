package emazon.transaction.ports.persistence.mysql.util;

public class SupplyRepositoryConstants {
    public static final String FIND_NEXT_SUPPLY_DATE_BY_PRODUCT_ID_QUERY =
            "SELECT s.nextSupplyDate FROM SupplyEntity s WHERE s.productId = :productId ORDER BY s.nextSupplyDate DESC LIMIT 1";
    public static final String PRODUCT_ID_PARAM = "productId";

    private SupplyRepositoryConstants() {
    }
}
