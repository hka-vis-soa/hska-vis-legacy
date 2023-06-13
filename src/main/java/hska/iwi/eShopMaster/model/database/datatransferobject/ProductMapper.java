package hska.iwi.eShopMaster.model.database.datatransferobject;

import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

public class ProductMapper {

	public static Product mapToProduct(ProductDTO productDTO, Category category) {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setCategory(category);
		product.setDetails(productDTO.getDetails());
		return product;
	}
}
