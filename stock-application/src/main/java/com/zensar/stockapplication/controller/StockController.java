package com.zensar.stockapplication.controller;

import java.util.ArrayList;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.service.StockService;

//import io.swagger.annotations.ApiOperation;

@RestController
//@Controller
//@CrossOrigin("*")
@RequestMapping("/stocks")
public class StockController {
	
	
	@Autowired
	private StockService stockService;
	//@RequestMapping(value="\test",method= {RequestMethod.GET,RequestMethod.POST})
	public void test() {
	System.out.println("I am inside test method");
	}
	//@ApiOperation(value="Getting all stock info")
	@RequestMapping(method=RequestMethod.GET)
	public List<StockDto> getAllStocks(@RequestParam(value="pageNumber", defaultValue="0", required=false)int pageNumber,@RequestParam(value="pageSize", defaultValue="5", required=false)int pageSize) {
		System.out.println(pageSize);
		return stockService.getAllStocks(pageNumber, pageSize);
	}
	//@ApiOperation(value="Getting  stock based on stockId")
	@RequestMapping(value="{stockId}",method=RequestMethod.GET)
	public StockDto getStock(@PathVariable("stockId") long id) throws InvalidStockIdException {
	return stockService.getStock(id); }
	
	@RequestMapping(value = "/name/{stockName}", method = RequestMethod.GET)
	public List<StockDto> getStockByName(@PathVariable("stockName") String stockName){
	return stockService.getStockByItsName(stockName);
	}
	@RequestMapping(value = "/name/{stockName}/price/{stockPrice}", method = RequestMethod.GET)
	public List<StockDto> getStockByName(@PathVariable("stockName") String stockName,@PathVariable("stockPrice") double stockPrice){
	return stockService.getStockByItsNameAndPrice(stockName,stockPrice);
	}


	//@ApiOperation(value="Posting  stock info")
	@PostMapping
	public ResponseEntity<StockDto> createStock(@RequestBody StockDto stock,@RequestHeader("auth-token")String token) {
		StockDto createstock=stockService.createStock(stock, token);

		/*if(createstock==null) {
		return new ResponseEntity<Stock>(HttpStatus.BAD_REQUEST);
		}*/
		return new ResponseEntity<StockDto>(createstock,HttpStatus.CREATED);
	
	}
	//@ApiOperation(value="Deleting stock based on stockId")
	@DeleteMapping("{stockId}")
	//@RequestMapping(value="/stocks",method=RequestMethod.DELETE)
	public String deleteStock(@PathVariable("stockId")long stockId) {
	return stockService.deleteStock(stockId);
	}
	//@ApiOperation(value="Updating all stock info based on stockId")
	@PutMapping("{stockId}")
	//@RequestMapping(value="/stocks",method=RequestMethod.DELETE)
	public StockDto updateStock(@PathVariable int stockId,@RequestBody StockDto stock) throws InvalidStockIdException
	{
	return stockService.updateStock(stockId, stock);
	}
	//@ApiOperation(value="Deleting all stock info")
	@DeleteMapping
	public ResponseEntity<String> deleteAllStocks(){
	String returnResult=stockService.deleteAllStocks();
	return new ResponseEntity<String> (returnResult,HttpStatus.OK);
	}
	/*@ExceptionHandler(InvalidStockIdException.class)
	public String handleException() {
		return "Invalid stock id";
	}*/



}
