package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.app.persistence.Category;
import edu.app.persistence.Product;

@Stateless
public class CatalogService implements CatalogServiceRemote, CatalogServiceLocal {
	
	@PersistenceContext
	private EntityManager em;

    public CatalogService() {
    }

	public void createProduct(Product product) {
		em.persist(product);
	}

	public void saveOrUpdateProduct(Product product) {
		em.merge(product);
	}

	public Product findProductById(int id) {
		return em.find(Product.class, id);
	}

	public void removeProduct(Product product) {
		em.remove(em.merge(product));
	}

	public List<Product> findAllProducts() {
		return em.createQuery("select p from Product p").getResultList();
	}

	public List<Product> findProductsByCategory(Category category) {
		return em.createQuery("select p from Product p where p.category=:c").setParameter("c", category).getResultList();
	}

	public void createCategory(Category category) {
		em.persist(category);
	}

	public void saveOrUpdateCategory(Category category) {
		em.merge(category);
	}

	public Category findCategoryById(int id) {
		return em.find(Category.class, id);
	}

	public void removeCategory(Category category) {
		em.remove(em.merge(category));
	}

	public List<Category> findAllCategories() {
		return em.createQuery("select c from Category c").getResultList();
	}

}
