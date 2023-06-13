package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.webclient.RequestClient;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class CategoryManagerImpl implements CategoryManager {

    private RequestClient requestClient;

    public CategoryManagerImpl() {
        this.requestClient = new RequestClient();
    }

    public List<Category> getCategories() {
        throw new NotImplementedException("getCategories#notImplemented");
    }

    public Category getCategory(int id) {
        throw new NotImplementedException("getCategory#notImplemented");
    }

    public Category getCategoryByName(String name) {
        throw new UnsupportedOperationException("getCategoryByName#unsupported");
    }

    public void addCategory(String name) {
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
