package crossover.backup;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;




@SpringBootApplication
@Controller
public class BackupController  extends WebMvcConfigurerAdapter {
    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    @Autowired
    ConfigurationRepository configurationRepository;


    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

         @Autowired
         private DataSource dataSource;


         @Override
         protected void configure(HttpSecurity http) throws Exception {
             http.authorizeRequests()
                 .antMatchers("/", "/anonymous/**").permitAll()
                 .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                 .antMatchers("/user/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                 .and().httpBasic().and().logout().permitAll();
             http.csrf().disable();
         }

         @Override
         public void configure(AuthenticationManagerBuilder auth) throws Exception {
             auth.jdbcAuthentication().dataSource(this.dataSource);
         }
    }

  
//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("/**/*.html").addResourceLocations("/");
//    registry.addResourceHandler("/**/*.js").addResourceLocations("/");
//  }


/*
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("backupshell");
    }
*/

    
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @RequestMapping("/admin")
    //@ResponseBody
    String admin() {
        //return "Hello World admin!";
        return "backupshell.html";
    }



    @RequestMapping("/user")
    //@ResponseBody
    String user() {
        //return "Hello World user!";
        return "userbackupshell";
    }



    @RequestMapping("/anonymous")
    @ResponseBody
    String anonymous() {
        return "Hello World anonymous!";
    }


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/admin/configuration", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    ConfigurationEntity insertConfiguration(@Valid @RequestBody ConfigurationEntity ce) {
    	//ce.setCreateUser("admin");
    	//getRemoteUser
    	UserDetails userDetails =
    			 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	ce.setCreateUser(userDetails.getUsername());
        ConfigurationEntity b = configurationRepository.save(ce);
        log.debug(b.toString()); 
        return b;
    }

    @RequestMapping(value = "/admin/configuration", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    ConfigurationEntity updateBackup(@Valid @RequestBody ConfigurationEntity ce) {
    	UserDetails userDetails =
   			 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   	    ce.setUpdateUser(userDetails.getUsername());
       
        ConfigurationEntity b = configurationRepository.save(ce);
        log.debug(b.toString()); 
        return b;
    }

    
    @RequestMapping(value = "/user/configuration/{id}", method = RequestMethod.GET)
    @ResponseBody
    ConfigurationEntity getConfiguration(@PathVariable int id) {
    	ConfigurationEntity b = configurationRepository.findOne(id);
     return b;
    }

    @RequestMapping(value = "/admin/configuration/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    String deleteConfiguration(@PathVariable int id) {
      int n = 0;
      if(configurationRepository.exists(id)) {
    	  configurationRepository.delete(id);
        n = 1;
      }
      return "{ \"data\" : \"" + n + "\"}";
    }
    
    @RequestMapping(value = "/user/configuration", method = RequestMethod.GET)
    @ResponseBody
    List<ConfigurationEntity> getConfigurationList() {
      List<ConfigurationEntity> response = new ArrayList<ConfigurationEntity>();
      BackupVO bvo;
      for(ConfigurationEntity b: configurationRepository.findAll()) {
        
         response.add(b);
      }
      return response;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackupController.class, args);
    }
}