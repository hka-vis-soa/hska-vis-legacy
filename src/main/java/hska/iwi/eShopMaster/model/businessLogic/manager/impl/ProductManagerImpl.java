package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.database.datatransferobject.ProductDTO;
import hska.iwi.eShopMaster.model.database.datatransferobject.ProductMapper;
import hska.iwi.eShopMaster.model.webclient.HttpMethod;
import hska.iwi.eShopMaster.model.webclient.RequestClient;
import hska.iwi.eShopMaster.model.webclient.Response;
import hska.iwi.eShopMaster.model.webclient.URL;

import java.util.ArrayList;
import java.util.List;

public class ProductManagerImpl implements ProductManager {
    private final RequestClient requestClient;

    private final ObjectMapper objectMapper;

    private final CategoryManager categoryManager;

    public ProductManagerImpl() {
        this.requestClient = new RequestClient();
        this.objectMapper = new ObjectMapper();
        this.categoryManager = new CategoryManagerImpl();
    }

    public List<Product> getProducts() {
        String targetURL = URL.DOCKER_HOST + URL.PRODUCTS;
        Response response = requestClient.execute(HttpMethod.GET, targetURL, null);
        return getProductsResponse(response);
    }

    public List<Product> getProductsForSearchValues(String details,
                                                    Double minPrice, Double maxPrice) {
        StringBuilder urlBuilder = new StringBuilder(URL.DOCKER_HOST + URL.PRODUCTS + URL.SEARCH);
        boolean detailsParam = details != null && details.length() > 0;
        boolean minPriceParam = minPrice != null;
        boolean maxPriceParam = maxPrice != null;
        if (detailsParam || minPriceParam || maxPriceParam) {
            urlBuilder.append(URL.QUESTIONMARK);
            if (detailsParam) {
                urlBuilder.append(URL.DETAILS).append(details);
            }
            if (minPriceParam) {
                if (detailsParam) {
                    urlBuilder.append(URL.AND);
                }
                urlBuilder.append(URL.MINPRICE).append(minPrice);
            }
            if (maxPriceParam) {
                if (detailsParam || minPriceParam) {
                    urlBuilder.append(URL.AND);
                }
                urlBuilder.append(URL.MAXPRICE).append(maxPrice);
            }
        }
        String targetURL = urlBuilder.toString();
        System.out.println(targetURL);
        Response response = requestClient.execute(HttpMethod.GET, targetURL, null);
        return getProductsResponse(response);
    }

    private List<Product> getProductsResponse(Response response) {
        List<Product> products = new ArrayList<Product>();
        if (response.getStatusCode() == 200) {
            try {
                List<ProductDTO> productDTOs = objectMapper.readValue(response.getBody(), new TypeReference<List<ProductDTO>>() {
                });
                for (ProductDTO productDTO : productDTOs) {
                    products.add(ProductMapper.mapToProduct(productDTO, categoryManager.getCategory(productDTO.getCategory_id())));
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    public Product getProductById(int id) {
        String targetURL = URL.DOCKER_HOST + URL.PRODUCTS + URL.SLASH + id;
        Response response = requestClient.execute(HttpMethod.GET, targetURL, null);
        Product product = null;
        if (response.getStatusCode() == 200) {
            try {
                ProductDTO productDTO = objectMapper.readValue(response.getBody(), ProductDTO.class);
                product = ProductMapper.mapToProduct(productDTO, categoryManager.getCategory(productDTO.getCategory_id()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return product;
    }


    public int addProduct(String name, double price, int categoryId, String details) {
        String body = null;
        int id = 0;
        try {
            body = objectMapper.writeValueAsString(new ProductDTO(name, price, details, categoryId));
            String targetURL = URL.DOCKER_HOST + URL.PRODUCTS;
            Response response = requestClient.execute(HttpMethod.POST, targetURL, body);
            if (response.getStatusCode() == 200) {
                ProductDTO productDTO = objectMapper.readValue(response.getBody(), ProductDTO.class);
                id = productDTO.getId();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void deleteProductById(int id) {
        String targetURL = URL.DOCKER_HOST + URL.PRODUCTS + URL.SLASH + id;
        requestClient.execute(HttpMethod.DELETE, targetURL, null);
    }

    // Following methods are not used
    public Product getProductByName(String name) {
        throw new UnsupportedOperationException("getProductByName#unsupported");
    }

    public boolean deleteProductsByCategoryId(int categoryId) {
        throw new UnsupportedOperationException("getProductByName#unsupported");
    }
}
