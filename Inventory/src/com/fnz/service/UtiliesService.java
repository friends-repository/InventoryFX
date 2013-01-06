package com.fnz.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import com.fnz.dao.UtiliesDAO;

public class UtiliesService 
{
	UtiliesDAO utiliesDAO;
	public UtiliesService()
	{
		utiliesDAO = new UtiliesDAO();
	}
	
	
	public void addCategory(String categoryName) throws Exception 
	{
		utiliesDAO.addCategory(categoryName);
	}
	
	public void addItem(String itemName, String categoryId) throws Exception 
	{
		utiliesDAO.addItem(itemName, categoryId);
	}
	
	public ObservableMap<String,String> fetchCategoryDetails() throws Exception
	{
		return utiliesDAO.fetchCategoryDetails();
	}
	public ObservableList<String> fetchCategory() throws Exception
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		list = utiliesDAO.fetchCategory();
		return list;
	}
	
	public ObservableMap<String,String> fetchItemDetails() throws Exception
	{
		return utiliesDAO.fetchItemDetails();
	}
	
	public ObservableList<String> fetchItem() throws Exception
	{
		return utiliesDAO.fetchItem();
	}
	
	public void deleteItem(String itemId) throws Exception
	{
		utiliesDAO.deleteItem(itemId);
	}
	public void deleteCategory(String categoryId) throws Exception
	{
		try
		{
			utiliesDAO.deleteCategory(categoryId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
