package at.technikum.webengbackend.config;

import java.util.List;
/* DEPRECATED */
@Deprecated
public enum AllowedPath {

    // AUTH MANAGEMENT
    SIGNIN("signin"), // POST
    SIGNUP("signup"), // POST

    // USER

    USER_LIST("user/list/"), // GET
    USER_ADD("user/add/"), // POST
    USER_DELETE("user/delete/{userId}"), // DELETE
    USER_UPDATE("user/update/{userId}"), // PUT

    // PRODUCT
    PRODUCT_LIST("product/list/"), // GET
    PRODUCT_ADD("product/add/"), // POST
    PRODUCT_DELETE("product/delete/{productId}"), // DELETE
    PRODUCT_UPDATE("product/update/{productId}"), // PUT

    // TEST
    TEST("test/**")
    ;


    private final String path;

    AllowedPath(String path) {
        this.path = "/api/v1/"+path;
    }

    public String getPath() {
        return path;
    }

    public static String[] getAllowedPaths_POST () {
        return List.of(
                SIGNIN.getPath(),
                SIGNUP.getPath(),
                PRODUCT_ADD.getPath(),
                USER_ADD.getPath()


        ).toArray(new String[0]);
    }

    public static String[] getAllowedPaths_GET() {
        return List.of(
                PRODUCT_LIST.getPath(),
                USER_LIST.getPath(),
                TEST.getPath()


        ).toArray(new String[0]);
    }

    public static String[] getAllowedPaths_DELETE() {
        return List.of(
                PRODUCT_DELETE.getPath(),
                USER_DELETE.getPath()


        ).toArray(new String[0]);
    }
    public static String[] getAllowedPaths_PUT() {
        return List.of(
                PRODUCT_UPDATE.getPath(),
                USER_UPDATE.getPath()


        ).toArray(new String[0]);
    }

}
