package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.webclient.RequestClient;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class ProductManagerImpl implements ProductManager {
    private RequestClient requestClient;

    public ProductManagerImpl() {
        this.requestClient = new RequestClient();
    }

    public List<Product> getProducts() {
        throw new NotImplementedException("getCategories#notImplemented");
    }

    public List<Product> getProductsForSearchValues(String searchDescription,
                                                    Double searchMinPrice, Double searchMaxPrice) {
        throw new NotImplementedException("getCategories#notImplemented");
    }

    public Product getProductById(int id) {
        throw new NotImplementedException("getCategories#notImplemented");
    }

    public Product getProductByName(String name) {
        throw new UnsupportedOperationException("getProductByName#unsupported");
    }

    public int addProduct(String name, double price, int categoryId, String details) {
        throw new NotImplementedException("getCategories#notImplemented");
    }


    public void deleteProductById(int id) {
        throw new UnsupportedOperationException("getProductByName#unsupported");
    }

    public boolean deleteProductsByCategoryId(int categoryId) {
        throw new UnsupportedOperationException("getProductByName#unsupported");
    }

}
