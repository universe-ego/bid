package edu.app.web.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.FilterEvent;

import edu.app.business.CatalogServiceLocal;
import edu.app.persistence.Category;
import edu.app.persistence.Product;

@ManagedBean
@ViewScoped
public class ProductBean {
	
	@EJB
	private CatalogServiceLocal catalogServiceLocal;
	
	private Product product = new Product();
	private List<Product> products;
	private boolean formDisplayed = false;
	private List<SelectItem> selectItemsForCategories;
	private int selectedCategoryId = -1;
	private List<Product> filteredProducts;
	private List<SelectItem> filterOptions;
	
	
	public ProductBean() {
	}
	
	@PostConstruct
	public void init(){
		products = catalogServiceLocal.findAllProducts();
		List<Category> categories = catalogServiceLocal.findAllCategories();
		selectItemsForCategories = new ArrayList<SelectItem>(categories.size());
		for(Category category:categories){
			selectItemsForCategories.add(new SelectItem(category.getId(),category.getName()));
		}
		filterOptions = new ArrayList<SelectItem>(categories.size()+1);
		filterOptions.add(new SelectItem("", "select"));
		for(Category category:categories){
			filterOptions.add(new SelectItem(category.getName(),category.getName()));
		}
		
	}
	
	
	public void doSaveOrUpdate(){
		product.setCategory(catalogServiceLocal.findCategoryById(selectedCategoryId));
		catalogServiceLocal.saveOrUpdateProduct(product);
		products = catalogServiceLocal.findAllProducts();
		formDisplayed = false;
	}
	
	public void doNew(){
		product = new Product();
		selectedCategoryId = -1;
		formDisplayed = true;
	}
	
	public void doCancel(){
		formDisplayed = false;
	}
	
	public void doDelete(){
		catalogServiceLocal.removeProduct(product);
		products = catalogServiceLocal.findAllProducts();
		formDisplayed = false;
	}
	
	public void onRowSelect(SelectEvent event){
		formDisplayed = true;
		if (product.getCategory()!= null) {
			selectedCategoryId = product.getCategory().getId();
		}else {
			selectedCategoryId = -1;
		}
		
	}
	
	public void onFilter(FilterEvent event){
		formDisplayed = false;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<SelectItem> getSelectItemsForCategories() {
		return selectItemsForCategories;
	}

	public void setSelectItemsForCategories(
			List<SelectItem> selectItemsForCategories) {
		this.selectItemsForCategories = selectItemsForCategories;
	}

	public int getSelectedCategoryId() {
		return selectedCategoryId;
	}

	public void setSelectedCategoryId(int selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}

	public List<Product> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(List<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

	public List<SelectItem> getFilterOptions() {
		return filterOptions;
	}

	public void setFilterOptions(List<SelectItem> filterOptions) {
		this.filterOptions = filterOptions;
	}
	
	

	
	
}
