package edu.app.web.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.FilterEvent;

import edu.app.business.CatalogServiceLocal;
import edu.app.persistence.Category;

@ManagedBean
@ViewScoped
public class CategoryBean {
	
	@EJB
	private CatalogServiceLocal catalogServiceLocal;
	
	private Category category = new Category();
	private List<Category> categories;
	private List<Category> filteredCategories;
	
	private boolean formDisplayed = false;
	
	public CategoryBean() {
	}
	
	@PostConstruct
	public void init(){
		categories = catalogServiceLocal.findAllCategories();
	}

	public void doSaveOrUpdate(){
		catalogServiceLocal.saveOrUpdateCategory(category);
		categories = catalogServiceLocal.findAllCategories();
		formDisplayed = false;
	}
	
	public void doNew(){
		category = new Category();
		formDisplayed = true;
	}
	
	public void doCancel(){
		formDisplayed = false;
	}
	
	public void doDelete(){
		catalogServiceLocal.removeCategory(category);
		categories = catalogServiceLocal.findAllCategories();
		formDisplayed = false;
	}
	
	public void onRowSelect(SelectEvent event){
		formDisplayed = true;
	}
	
	public void onFilter(FilterEvent event){
		formDisplayed = false;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<Category> getFilteredCategories() {
		return filteredCategories;
	}

	public void setFilteredCategories(List<Category> filteredCategories) {
		this.filteredCategories = filteredCategories;
	}
	
	
	

}
