package space.gavinklfong.demo.streamapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import space.gavinklfong.demo.streamapi.repos.CustomerRepo;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;
import space.gavinklfong.demo.streamapi.models.Product;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {

	@Autowired
	private CustomerRepo customerRepos;
	
	@Autowired
	private OrderRepo orderRepos;
	
	@Autowired
	private ProductRepo productRepos;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		log.info("Customers:");
		customerRepos.findAll()
				.forEach(c -> log.info(c.toString()));

		log.info("Orders:");
		orderRepos.findAll()
				.forEach(o -> log.info(o.toString()));

		log.info("Products:");
		productRepos.findAll()
				.forEach(p -> log.info(p.toString()));
		log.info ("==========================");
        log.info ("Exercise 1 — Obtain a list of products belongs to category “Books” with price > 100");
		List<Product> productList = productRepos.findAll().stream().filter(a -> (a.getCategory().equalsIgnoreCase("books")
		&& a.getPrice() > 100))
		.collect(Collectors.toList());

		//SOLUTION from the website
		/*List<Product> result = productRepo.findAll()
		.stream()
		.filter(p -> p.getCategory().equalsIgnoreCase("Books"))
		.filter(p -> p.getPrice() > 100)
		.collect(Collectors.toList());*/

		log.info("{} ", productList);
		log.info ("==========================");
		log.info ("Exercise 2 — Obtain a list of order with products belong to category “Baby”");
		//skip this exercise 2 cause it involve in joining two table using hibernate
		//which i am not yet proficient in

		//or maybe i just write native query in? cant it will defeat the exercise purposes, to join
		//and filter using streams

		log.info ("==========================");
		log.info ("Exercise 3 — Obtain a list of product with category = “Toys” and then apply 10% discount");
		List<Product> toysList = productRepos.findAll()
		.stream()
		.filter(p -> p.getCategory().equalsIgnoreCase("Toys"))
		.map((p)->{
			p.setPrice(p.getPrice() * 0.9);
			return p;
		})
		.collect(Collectors.toList());
		log.info("{} ", toysList);
		log.info ("==========================");
		log.info ("Exercise 4 — Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021");
	}

}
