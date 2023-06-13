package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.webclient.HttpMethod;
import hska.iwi.eShopMaster.model.webclient.RequestClient;
import hska.iwi.eShopMaster.model.webclient.Response;
import hska.iwi.eShopMaster.model.webclient.URL;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class CategoryManagerImpl implements CategoryManager {

    private final RequestClient requestClient;
    private final ObjectMapper objectMapper;

    public CategoryManagerImpl() {
        this.requestClient = new RequestClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Category> getCategories() {
        String targetURL = URL.DOCKER_HOST + URL.CATEGORIES;
        Response response = requestClient.execute(HttpMethod.GET, targetURL, null);
        List<Category> categories = Collections.emptyList();
        if(response.getStatusCode() == 200) {
            try {
                categories = objectMapper.readValue(response.getBody(), new TypeReference<List<Category>>() {});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    public Category getCategory(int id) {
        String targetURL = URL.DOCKER_HOST + URL.CATEGORIES + URL.SLASH + id;
        Response response = requestClient.execute(HttpMethod.GET, targetURL, null);
        Category category = null;
        if(response.getStatusCode() == 200) {
            try {
                category = objectMapper.readValue(response.getBody(), Category.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return category;
    }

    public void addCategory(String name) {
        String body = null;
        try {
            body = objectMapper.writeValueAsString(new Category(name));
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String targetURL = URL.DOCKER_HOST + URL.CATEGORIES;
        requestClient.execute(HttpMethod.POST, targetURL, body);
    }

    public void delCategoryById(int id) {
        String targetURL = URL.DOCKER_HOST + URL.CATEGORIES + URL.SLASH + id;
        requestClient.execute(HttpMethod.DELETE, targetURL, null);
    }

    // Following methods are not used
    public Category getCategoryByName(String name) {
        throw new UnsupportedOperationException("getCategoryByName#unsupported");
    }

    public void delCategory(Category cat) {
        throw new UnsupportedOperationException("delCategory#unsupported");
    }
}
