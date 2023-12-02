package at.technikum.webengbackend.config;

/*
    All allowed Paths should be defined in here.

    Create a new field "CLASS_FUNCTION" with its path => STRING CLASS_FUNCTION = path + "examplepath";

    Then add it the right Array bellow.

    These paths are used in the controllers and in the SecurityConfig file.
*/
public class AllowedPaths {
    public static final String API_BASE = "/api/v1/";

    public static class User {
        public static final String USER_BASE = "user/";

        public static final String SIGNIN = API_BASE + "signin"; // POST
        public static final String SIGNUP = API_BASE + USER_BASE + "signup"; // POST
        public static final String LIST = API_BASE + USER_BASE + "list/"; // GET
        public static final String GET_ONE = API_BASE + USER_BASE + "{userId}"; // GET
        public static final String DELETE = API_BASE + USER_BASE + "delete/{userId}"; // DELETE
        public static final String UPDATE = API_BASE + USER_BASE + "update/{userId}"; // PUT

    }

    public static class Order {
        public static final String USER_BASE = "order/";
        public static final String LIST = API_BASE + USER_BASE + "list/"; // GET
        public static final String GET_ONE = API_BASE + USER_BASE + "{orderId}"; // GET
        public static final String ADD = API_BASE + USER_BASE + "add/"; // POST
        public static final String DELETE = API_BASE + USER_BASE + "delete/{orderId}"; // DELETE
    }

    public static class Product {
        public static final String PRODUCT_BASE = "product/";
        public static final String LIST = API_BASE + PRODUCT_BASE + "list/"; // GET
        public static final String ADD = API_BASE + PRODUCT_BASE + "add/"; // POST
        public static final String DELETE = API_BASE + PRODUCT_BASE + "delete/{productId}"; // DELETE
        public static final String UPDATE = API_BASE + PRODUCT_BASE + "update/{productId}"; // PUT
    }

    public static final String[] allowedPath_GET = {
            User.LIST,
            User.GET_ONE,
            Product.LIST,
            Order.LIST,
            Order.GET_ONE
    };

    public static final String[] allowedPath_POST = {
            User.SIGNIN,
            User.SIGNUP,
            Product.ADD,
            Order.ADD
    };

    public static final String[] allowedPath_DELETE = {
            User.DELETE,
            Product.DELETE,
            Order.DELETE
    };

    public static final String[] allowedPath_PUT = {
            User.UPDATE,
            Product.UPDATE
    };

}
