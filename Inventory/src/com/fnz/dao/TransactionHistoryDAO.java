package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

import javafx.collections.ObservableList;

import com.fnz.VO.StockVO;
import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class TransactionHistoryDAO {
	
	public Boolean deletePurchaseFromDate(ObservableList<StockVO> itemList) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		ResultSet resultSet = null;
		SQLiteConfig config = null;
		java.sql.Statement statement = null;
		Class.forName(CommonConstants.DRIVERNAME);
		String msg = CommonConstants.UPDATE_MSG;
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			statement = conn.createStatement();
			
			for(StockVO stock:itemList)
			{
				if(stock.getCheck())
				{
					String splitsDate[] = stock.getDate().split("/");
					stock.setDate(splitsDate[2]+"-"+splitsDate[1]+"-"+splitsDate[0]);
					statement.addBatch(SQLConstants.DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_1+stock.getInvoiceId()+
						SQLConstants.DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_2+stock.getDate()+
						SQLConstants.DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_3+stock.getItemId()+
						SQLConstants.DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_4+stock.getTypeId()+
						SQLConstants.DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_5);
					statement.addBatch(SQLConstants.DELETE_TRANSACTION_UPDATE_ITEM_TYPE_1+stock.getQuantity() +
							SQLConstants.DELETE_TRANSACTION_UPDATE_ITEM_TYPE_2 + stock.getItemId() +
							SQLConstants.UPDATE_DEL_ITEMS_TYPES_3+ stock.getTypeId() +
							SQLConstants.UPDATE_DEL_ITEMS_TYPES_4);
				}
			}
			statement.executeBatch();	
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		finally{
			if(conn !=null )
			{
				conn.close();
			}
			if(statement != null )
			{
				statement.close();
			}
			if(resultSet != null)
			{
				resultSet.close();
			}
		}
			return null;
	}
	
	
	public Boolean deleteSalesFromDate(ObservableList<StockVO> itemList) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		SQLiteConfig config = null;
		java.sql.Statement statement = null;
		Class.forName(CommonConstants.DRIVERNAME);
		String msg = CommonConstants.UPDATE_MSG;
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			statement = conn.createStatement();
			
			for(StockVO stock:itemList)
			{
				if(stock.getCheck())
				{
					String splitsDate[] = stock.getDate().split("/");
					stock.setDate(splitsDate[2]+"-"+splitsDate[1]+"-"+splitsDate[0]);
					statement.addBatch(SQLConstants.DELETE_TRANSACTION_OUTGOING_STOCK_DETAILS_1+
						SQLConstants.DELETE_TRANSACTION_OUTGOING_STOCK_DETAILS_2+stock.getDate()+
						SQLConstants.DELETE_TRANSACTION_OUTGOING_STOCK_DETAILS_3+stock.getItemId()+
						SQLConstants.DELETE_TRANSACTION_OUTGOING_STOCK_DETAILS_4+stock.getTypeId()+
						SQLConstants.DELETE_TRANSACTION_OUTGOING_STOCK_DETAILS_5);
					statement.addBatch(SQLConstants.ADD_TRANSACTION_UPDATE_ITEM_TYPE_1+stock.getQuantity() +
							SQLConstants.ADD_TRANSACTION_UPDATE_ITEM_TYPE_2 + stock.getItemId() +
							SQLConstants.ADD_TRANSACTION_UPDATE_ITEM_TYPE_3+ stock.getTypeId() +
							SQLConstants.ADD_TRANSACTION_UPDATE_ITEM_TYPE_4);
				}
			}
			statement.executeBatch();	
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		finally{
			if(conn !=null )
			{
				conn.close();
			}
			if(statement != null )
			{
				statement.close();
			}
		}
			return null;
	}
	
}
