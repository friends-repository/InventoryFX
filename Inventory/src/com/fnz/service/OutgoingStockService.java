package com.fnz.service;

import javafx.collections.ObservableList;

import com.fnz.VO.ItemVO;
import com.fnz.VO.StockVO;
import com.fnz.dao.OutgoingStockDAO;

public class OutgoingStockService 
{
		OutgoingStockDAO outgoingStockDAO;
		public OutgoingStockService()
		{
			outgoingStockDAO = new OutgoingStockDAO();
		}
		public String deleteOutgoingStock(String date, ObservableList<ItemVO> listData, String categoryId) throws Exception 
		{
			return outgoingStockDAO.deleteOutgoingStock(date, listData, categoryId);
		}
		public ObservableList<StockVO> fetchOutgoingStockDetails(String initialDate, String finalDate) throws Exception 
		{
			return outgoingStockDAO.fetchOutgoingStockDetails(initialDate, finalDate);
		}
		public ObservableList<StockVO> fetchOutgoingStockDetails(String invoiceid) throws Exception 
		{
			return outgoingStockDAO.fetchOutgoingStockDetails(invoiceid);
		}
}
