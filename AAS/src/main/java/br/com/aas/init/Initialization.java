package br.com.aas.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.aas.entities.DatabaseConfig;
import br.com.aas.entities.User;
import br.com.aas.entities.enums.Driver;
import br.com.aas.entities.enums.Perfil;
import br.com.aas.services.DataBaseConfigService;
import br.com.aas.services.UserService;

@Configuration
public class Initialization implements CommandLineRunner {

	@Autowired
	private DataBaseConfigService dbService;
	@Autowired
	private UserService usService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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

		// Cadastrando bancos de dados
		dbService.save(db);
		dbService.save(db2);
		dbService.save(db3);

		dbService.activeDataConfig(db.getId());

		User us = new User();
		us.setName("joylson");
		us.setLogin("joylson");
		us.setPassword(bCryptPasswordEncoder.encode("123456"));
		us.setEmail("joylsont@gmail.com");
		us.addPerfil(Perfil.ADMIN);
		us.addPerfil(Perfil.PROGRAMADOR);

		// Cadastrando usuario
		usService.save(us);
	}

}
