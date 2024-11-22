package com.coderhouse.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.interfaces.ProductRestInterface;
import com.coderhouse.models.Product;


@Component
public class ProductRestApi implements ProductRestInterface {

	private final String BASE_URL = "https://673601d45995834c8a9501ca.mockapi.io/clients";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Product> getAllProducts() {
		try {
			@SuppressWarnings("unchecked")
			List<Product> products = restTemplate
					.exchange(BASE_URL, HttpMethod.GET, null, List.class).getBody();
			return products;
			
		} catch (Exception e){
			throw new RuntimeException("Error al obtener productos", e);
		}
	}

	@Override
	public Product getProductById(String id) {
		try {
			String url = BASE_URL + "/" + id;
			return restTemplate.getForObject(url, Product.class);
		} catch (Exception e){
			throw new RuntimeException("Error al obtener el producto", e);
		}
	}

	@Override
	public Product addProduct(Product product) {
		try {
			return restTemplate.postForObject(BASE_URL, product, Product.class);
		} catch (Exception e){
			throw new RuntimeException("Error al agregar producto", e);
		}
	}

	@Override
	public Product updateProduct(Product product) {
		try {
			String url = BASE_URL + "/" + product.getId();
			restTemplate.put(url, product);
			return product;
		} catch (Exception e){
			throw new RuntimeException("Error al actualizar producto", e);
		}
	}

	@Override
	public void deleteProduct(Long id) {
		try {
			String url = BASE_URL + "/" + id;
			restTemplate.delete(url);
		} catch (Exception e){
			throw new RuntimeException("Error al eliminar producto", e);
		}
	}
}
