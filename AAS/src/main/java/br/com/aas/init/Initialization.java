package br.com.aas.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.aas.entities.DatabaseConfig;
import br.com.aas.entities.enums.Driver;
import br.com.aas.services.DataBaseConfigService;

@Configuration
public class Initialization implements CommandLineRunner{

	@Autowired
	private DataBaseConfigService DBService;
	
	
	@Override
	public void run(String... args) throws Exception {
		DatabaseConfig db = new DatabaseConfig();
		
		db.setUrl("jdbc:postgresql://localhost:5432/query_aas");
		db.setUser("postgres");
		db.setPassword("a");
		db.setDriver(Driver.POSTGRES);
		db.setActive(true);
		

		DatabaseConfig db2 = new DatabaseConfig();
		db2.setUrl("jdbc:mysql://localhost:5432/query_aas");
		db2.setUser("root");
		db2.setPassword("a");
		db2.setDriver(Driver.MYSQL);
		db2.setActive(false);
		

		DatabaseConfig db3 = new DatabaseConfig();
		db3.setUrl("jdbc:sqlserver://localhost:5432/query_aas");
		db3.setUser("sa");
		db3.setPassword("a");
		db3.setDriver(Driver.SQLSERVER);
		db3.setActive(false);
		
		try {
			
			DBService.save(db);
			DBService.save(db2);
			DBService.save(db3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(DBService.findByActive(true));
	}

}
