package com.zensar.stockapplication.service;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;


public interface StockService {
	//List<StockResponse> getAllStocks(int pageNumber,int pageSize);
	List<StockDto> getStockByItsName(String stockName);
	List<StockDto> getStockByItsNameAndPrice(String stockName, double stockPrice);
	StockDto getStock(long id) throws InvalidStockIdException;
	StockDto createStock(StockDto stock,String token);
	String deleteStock(long stockId);
	StockDto updateStock(int stockId, StockDto stock) throws InvalidStockIdException;
	String deleteAllStocks();
	List<StockDto> getAllStocks(int pageNumber, int pageSize);
}
