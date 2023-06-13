package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.webclient.HttpMethod;
import hska.iwi.eShopMaster.model.webclient.RequestClient;
import hska.iwi.eShopMaster.model.webclient.URL;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class CategoryManagerImpl implements CategoryManager {

    private RequestClient requestClient;

    public CategoryManagerImpl() {
        this.requestClient = new RequestClient();
    }

    public List<Category> getCategories() {
        String targetURL = URL.HOST + URL.CATEGORIES;
        String response = requestClient.execute(HttpMethod.GET, targetURL, null, null);
        throw new NotImplementedException("getCategories#notImplemented");
    }

    public Category getCategory(int id) {
        String targetURL = URL.HOST + URL.CATEGORIES + URL.SLASH + id;
        String response = requestClient.execute(HttpMethod.GET, targetURL, null, null);
        throw new NotImplementedException("getCategory#notImplemented");
    }

    public Category getCategoryByName(String name) {
        throw new UnsupportedOperationException("getCategoryByName#unsupported");
    }

    public void addCategory(String name) {
        String body = "{\"name\": '" + name + "\"}";
        String targetURL = URL.HOST + URL.CATEGORIES;
        String response = requestClient.execute(HttpMethod.POST, targetURL, null, body);
        throw new NotImplementedException("addCategory#notImplemented");

    }

    public void delCategory(Category cat) {
        throw new UnsupportedOperationException("delCategory#unsupported");
    }

    public void delCategoryById(int id) {
        throw new NotImplementedException("delCategoryById#notImplemented");
        // helper.deleteById(id);
    }
}
