package emazon.transaction.ports.application.http.util;

public class RolePermissionConstants {
    public static final String HAS_ROLE_AUX_BODEGA = "hasRole('AUX_BODEGA')";
    public static final String HAS_ROLE_CLIENTE = "hasRole('CLIENTE')";
    public static final String HAS_ROLE_AUX_BODEGA_OR_CLIENTE = HAS_ROLE_AUX_BODEGA + " or " + HAS_ROLE_CLIENTE;

    private RolePermissionConstants() {
    }


}
