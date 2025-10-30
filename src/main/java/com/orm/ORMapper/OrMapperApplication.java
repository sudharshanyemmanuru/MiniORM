package com.orm.ORMapper;

import com.orm.ORMapper.appscanner.ApplicationScanner;
import com.orm.ORMapper.model.Book;
import com.orm.ORMapper.repo.DbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrMapperApplication implements CommandLineRunner {
	@Autowired
	private ApplicationScanner applicationScanner;
	@Autowired
	private DbRepository dbRepository;
	public static void main(String[] args) {
		SpringApplication.run(OrMapperApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
		applicationScanner.scan();
		for(int i=1;i<=5;i++){
			Book book=new Book();
			book.setId(i);
			book.setName("Book "+i);
			book.setPrice((23*i));
			dbRepository.save(book);
		}
	}
}
