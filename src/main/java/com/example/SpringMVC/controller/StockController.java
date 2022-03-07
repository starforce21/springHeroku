package com.example.SpringMVC.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.SpringMVC.repository.StockRepository;
import com.example.SpringMVC.repository.WatchListRepository;
import com.example.SpringMVC.model.Stock;
import com.example.SpringMVC.model.WatchList;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class StockController {
	@Autowired
	private StockRepository stockRepo;
	@Autowired
	private WatchListRepository wlRepo;
	
	@GetMapping("/watchlist/{ticker}")
	public Optional <WatchList> getOneTickers(@PathVariable String ticker)
	{
		return wlRepo.findById(ticker);
	}
	
	@GetMapping("/alltickers")
	public List<WatchList> getAllTickers()
	{
		return wlRepo.findAll();
	}
	
	@PostMapping("/addticker")
	public WatchList newTicker(@RequestBody WatchList ticker)
	{
		return wlRepo.save(ticker);
	}
	
	@DeleteMapping("/ticker/{ticker}")
	@Transactional
	public String deleteTicker(@PathVariable String ticker) throws Exception
	{
		wlRepo.findById(ticker).orElseThrow(() ->  new Exception("Ticker not found"));
	    wlRepo.deleteById(ticker);
	    return "The Stock with ticker: "+ ticker +" is removed from the database.";
	}
	
	
	
	
	// get all holding
	@GetMapping("/allstocks")
	public List<Stock> getAllStocks()
	{
		return stockRepo.findAll();
	}
	
	@PostMapping("/addstock")
	public Stock newStocks(@RequestBody Stock stonk)
	{
		return stockRepo.save(stonk);
	}
	
	@GetMapping("/company/{company}")
	public List<Stock> getStockByName(@PathVariable String company)
	{
		
		List <Stock> stocks=stockRepo.findByCompany(company);
		if(stocks.isEmpty())
		{
			System.out.println(new Exception("Company with the name "+ company +" not found"));
		}
		
		return stockRepo.findByCompany(company);
	}
	
	@GetMapping("/ticker/{ticker}")
	public List<Stock> getStockByTicker(@PathVariable String ticker)
	{
		
		List <Stock> stocks=stockRepo.findByTicker(ticker);
		if(stocks.isEmpty())
		{
			System.out.println(new Exception("Stock with the ticker "+ ticker +" not found"));
		}
		
		return stockRepo.findByTicker(ticker);
	}
	
	
	
	@PutMapping("stock/{ticker}")
	public ResponseEntity<Stock> updateStock(@PathVariable String ticker, @RequestBody Stock stonk)
	{
		List <Stock> stocks=stockRepo.findByTicker(ticker);
		if(stocks.isEmpty())
		{
			System.out.println(new Exception("Stock with the ticker "+ ticker +" not found"));
		}
		Stock s= stocks.get(0);
	    s.setTicker(stonk.getTicker());
	    s.setQuantity(stonk.getQuantity());
	    s.setPurchasePrice(stonk.getPurchasePrice());
	    Stock updatedStock=stockRepo.save(s);
	    return ResponseEntity.ok(updatedStock);
	}
	

	
	@DeleteMapping("/stock/{ticker}")
	@Transactional
	public String deleteStudent(@PathVariable String ticker)
	{
		List <Stock> stocks=stockRepo.findByTicker(ticker);
		if(stocks.isEmpty())
		{
			System.out.println(new Exception("Stock with the ticker "+ ticker +" not found"));
		}
	    stockRepo.deleteByTicker(ticker);
	    return "The Stock with ticker: "+ ticker +" is removed from the database.";
	}
	
	
}
