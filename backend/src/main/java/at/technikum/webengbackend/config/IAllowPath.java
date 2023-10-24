package at.technikum.webengbackend.config;

import java.util.List;


/*
    All allowed Paths should be defined in here.

    Create a new field "CLASS_FUNCTION" with its path => STRING CLASS_FUNCTION = path + "examplepath";

    Then add it the right LIST bellow.

    These paths are used in the controllers and in the SecurityConfig file.
*/
public interface IAllowPath {
    String path = "/api/v1/";

    String USER_SIGNIN = path + "signin"; // POST
    String USER_SIGNUP = path + "signup"; // POST

    String USER_LIST = path + "user/list/"; // GET
    String USER_ADD = path + "user/add/"; // POST
    String USER_DELETE = path + "user/delete/{userId}"; // DELETE
    String USER_UPDATE = path + "user/update/{userId}"; // PUT

    String PRODUCT_LIST = path + "product/list/"; // GET
    String PRODUCT_ADD = path + "product/add/"; // POST
    String PRODUCT_DELETE = path + "product/delete/{productId}"; // DELETE
    String PRODUCT_UPDATE = path + "product/update/{productId}"; // PUT

    String[] allowedPath_GET = {
            USER_LIST,
            PRODUCT_LIST
    };

    String[] allowedPath_POST = {
            USER_SIGNIN,
            USER_SIGNUP,
            USER_ADD,
            PRODUCT_ADD
    };

    String[] allowedPath_DELETE = {
            PRODUCT_DELETE,
            USER_DELETE
    };

    String[] allowedPath_PUT = {
            PRODUCT_UPDATE,
            USER_UPDATE
    };

}
