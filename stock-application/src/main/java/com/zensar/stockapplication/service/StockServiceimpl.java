package com.zensar.stockapplication.service;

import java.util.ArrayList;



import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.repository.StockRepository;
@Service
public class StockServiceimpl implements StockService {
	@Autowired
	private StockRepository stockRepository;
	/*static List<Stock> stocks=new ArrayList<Stock>();
	static {
	stocks.add(new Stock(1L,"RIL","bse",2611));
	stocks.add(new Stock(2L,"Zensar","bse",342));
	}*/
	//private ModelMapper modelMapper=new ModelMapper();
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<StockDto> getAllStocks(int pageNumber,int pageSize) {
		Page<Stock> pageStocks= stockRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by("stockId").descending()));

		List<Stock> content= pageStocks.getContent();
		List<StockDto> stockResponses=new ArrayList<>();
		for(Stock stock: content) {
			StockDto mapToResponse=mapToResponse(stock);
			stockResponses.add(mapToResponse);
		}
		
	return stockResponses;
	}
	@Override
	public StockDto getStock(long id) throws InvalidStockIdException {
	/*Optional<Stock> stock1=stocks.stream().filter(stock-> stock.getStockId()==id).findAny();
	if(stock1.isPresent())
	{
	return stock1.get();
	}else {
	return stock1.orElseGet(()->{return new Stock();});
	}*/
		Optional<Stock> optionalStock=stockRepository.findById(id);
		//Stock stock=stockRepository.findById(id).get();
		Stock stock=null;
		if(optionalStock.isPresent()) {
			stock=optionalStock.get();
			return modelMapper.map(stock,StockDto.class);
		}else {
			throw new InvalidStockIdException("Stock is not available with stock id"+id);
		//return modelMapper.map(stock,StockDto.class);
		//return stockRepository.findById(id).get();
	}
	}
	@Override
	public StockDto createStock(StockDto stock, String token) {
	// Stock newStock=mapToStock(stock);
	//System.out.println(stock);
	//if(token.equals("sh66607")) {
	//stocks.add(stock);
	
	/*return stockRepository.save(newStock);
	}
	else {
	return null;
	}*/
		Stock newStock=	modelMapper.map(stock, Stock.class);
		if(token.equals("sh66607")) {
			Stock save= stockRepository.save(newStock);
			return modelMapper.map(save,StockDto.class );
			}else {
				return null ;
			} 
	}
	@Override
	public String deleteStock(long stockId) {
		stockRepository.deleteById(stockId);
		return "Stock deleted with stock id"+ stockId;
	/*for(Stock stock :stocks) {
	if(stock.getStockId()==stockId) {
	stocks.remove(stock);
	return "Stock deleted with stockId"+stockId;
	}
	}
	return "sorry,stock id is not available";*/
	}
	public List<StockDto> getStockByItsName(String stockName){
		List<Stock> findStockByName=stockRepository.findStockByItsName(stockName);
		List<StockDto> stocks=new ArrayList<StockDto>();
		for(Stock st:findStockByName) {
		stocks.add(modelMapper.map(st,StockDto.class));
		}
		return stocks;
		}
		public List<StockDto> getStockByItsNameAndPrice(String stockName,double price){
		List<Stock> findStockByNameAndPrice=stockRepository.findStockByItsNameAndPrice(stockName, price);
		List<StockDto> stocks=new ArrayList<StockDto>();
		for(Stock st:findStockByNameAndPrice) {
		stocks.add(modelMapper.map(st,StockDto.class));
		}
		return stocks;
		}
	@Override
	public StockDto updateStock(int stockId, StockDto stock) throws InvalidStockIdException {
    /* Stock stock2=getStock(stockId);
     stock2.setMarketName(stock.getMarketName());
		stock2.setName(stock.getName());
		stock2.setPrice(stock.getPrice());
	return stockRepository.save(stock2);*/
	/*availableStock.setMarketName(stock.getMarketName());
	availableStock.setName(stock.getName());
	availableStock.setPrice(stock.getPrice());
	return availableStock;*/
		StockDto stockResponse =getStock(stockId);
		Stock stock2=mapToStock(stockResponse);
		if(stock2!=null) {
			stock2.setPrice(stock.getPrice());
			stock2.setMarketName(stock.getMarketName());
			stock2.setName(stock.getName());
			stock2.setStockId(stockId);
		Stock stock3=	stockRepository.save(stock2);
	return	mapToResponse(stock3);
		}
		
		return null;
	}
	@Override
	public String deleteAllStocks() {
	//stocks.removeAll(stocks);
		stockRepository.deleteAll();
	return "All stocks deleted sucessfully";
	}
	
		
	private Stock mapToStock(StockDto stockDto) {
		Stock newStock= new Stock();
		newStock.setMarketName(stockDto.getMarketName());
		newStock.setName(stockDto.getName());
		newStock.setPrice(stockDto.getPrice());
		return newStock;
	}
	/*private Stock mapToStock(StockDto stockDto) {
		Stock newStock= new Stock();
		newStock.setMarketName(stockDto.getMarketName());
		newStock.setName(stockDto.getName());
		newStock.setPrice(stockDto.getPrice());
		return newStock;
	}*/
	private StockDto mapToResponse(Stock stock) {
		
		StockDto stockResponse=new StockDto();
		stockResponse.setStockId(stock.getStockId());
		stockResponse.setPrice(stock.getPrice());
		stockResponse.setMarketName(stock.getMarketName());
		stockResponse.setName(stock.getName());
		
		return stockResponse;
	}
	


}
