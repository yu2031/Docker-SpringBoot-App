package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository, OrderRepository orderRepository) {

    return args -> {
     /* log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
      log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));*/
    	
    	log.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
    	log.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));	
    	
    	
    	 
        orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
        orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

        orderRepository.findAll().forEach(order -> {
          log.info("Preloaded " + order);
        });
    	
    };
  }
  
  @Autowired Environment env;
  @Bean
  public DataSource dataSource() {
  DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
  dataSourceBuilder.driverClassName("org.sqlite.JDBC");
  dataSourceBuilder.url("jdbc:sqlite:payroll.db");
  return dataSourceBuilder.build();
  }
  
}