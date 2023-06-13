package hska.iwi.eShopMaster.model.webclient;

public class URL {

    public static final String HOST = "http://localhost:8080";
    public static final String DOCKER_HOST = System.getenv("PROXY_URL");

    // category-service URLs
    public static final String CATEGORIES = "/categories";
    // product-service URLs
    public static final String PRODUCTS = "/products";
    public static final String MINPRICE = "minPrice=";
    public static final String MAXPRICE = "maxPrice=";
    public static final String DETAILS = "details=";
    public static final String CATEGORY = "/category";

    public static final String SEARCH = "/search";
    // Helper
    public static final String QUESTIONMARK = "?";

    public static final String AND = "&";
    public static final String SLASH = "/";
}
