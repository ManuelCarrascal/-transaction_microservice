package emazon.transaction.infrastructure.configuration.util;

public class FeignConstants {
    public static final String CART_SERVICE_NAME = "cart";
    public static final String CART_SERVICE_URL = "http://localhost:9093";
    public static final String STOCK_SERVICE_NAME = "stock";
    public static final String STOCK_SERVICE_URL = "http://localhost:9091";
    public static final String REPORT_SERVICE_NAME = "reports";
    public static final String REPORT_SERVICE_URL = "http://localhost:9095";

    private FeignConstants() {
    }
}
