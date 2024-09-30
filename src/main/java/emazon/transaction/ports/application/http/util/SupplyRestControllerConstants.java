package emazon.transaction.ports.application.http.util;

public class SupplyRestControllerConstants {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String ADD_PRODUCT_TO_SUPPLY_SUMMARY = "Add product to supply";
    public static final String ADD_PRODUCT_TO_SUPPLY_DESCRIPTION = "Adds a product to the supply based on the provided product ID and supply request.";
    public static final String GET_NEXT_SUPPLY_DATE_SUMMARY = "Get next supply date";
    public static final String GET_NEXT_SUPPLY_DATE_DESCRIPTION = "Retrieves the next supply date for the specified product ID.";
    public static final String SUCCESSFUL_ADDITION_MESSAGE = "Product successfully added to supply.";
    public static final String SUCCESSFUL_RETRIEVAL_MESSAGE = "Next supply date successfully retrieved.";
    public static final String INVALID_INPUT_MESSAGE = "Invalid input.";
    public static final String ACCESS_DENIED_MESSAGE = "Access denied.";

    public static final String RESPONSE_CODE_200 = "200";
    public static final String RESPONSE_CODE_201 = "201";
    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_CODE_404 = "404";
    public static final String RESPONSE_CODE_403 = "403";

    private SupplyRestControllerConstants() {
    }
}
