package crossover.backup;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;




public class SampleController  extends WebMvcConfigurerAdapter {
    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    @Autowired
    BackupRepository backupRepository;


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

    @RequestMapping(value = "/admin/backup", method = RequestMethod.POST)
    @ResponseBody
    String insertBackup(@RequestBody BackupVO backupVO) {
        BackupEntity b = new BackupEntity(backupVO);
        b = backupRepository.save(b);
        log.debug(b.toString()); 
        return "{ \"data\" : \"" + b.getId() + "\"}";
    }

    @RequestMapping(value = "/admin/backup", method = RequestMethod.PUT)
    @ResponseBody
    BackupVO updateBackup(@RequestBody BackupVO backupVO) {
      /*BackupEntity b = backupRepository.findOne(backupVO.id);
      if(b != null) {
        b.setName(backupVO.name);
        b.setSrcip(backupVO.srcip);
        backupRepository.save(b);
      }
      */
        BackupEntity b = new BackupEntity(backupVO);
        b.setId(backupVO.id);
        b = backupRepository.save(b);
        log.debug(b.toString()); 
        return backupVO;
    }

    
    @RequestMapping(value = "/user/backup/{id}", method = RequestMethod.GET)
    @ResponseBody
    BackupVO getBackup(@PathVariable int id) {
      BackupEntity b = backupRepository.findOne(id);
      BackupVO bvo = new BackupVO();
      if(b != null) {
         bvo.id = b.getId();
         bvo.name = b.getName();
         bvo.srcip = b.getSrcip(); 
      }
      return bvo;
    }

    @RequestMapping(value = "/admin/backup/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    String deleteBackup(@PathVariable int id) {
      int n = 0;
      if(backupRepository.exists(id)) {
        backupRepository.delete(id);
        n = 1;
      }
      return "{ \"data\" : \"" + n + "\"}";
    }
    
    @RequestMapping(value = "/user/backup", method = RequestMethod.GET)
    @ResponseBody
    List<BackupVO> getBackup() {
      List<BackupVO> response = new ArrayList<BackupVO>();
      BackupVO bvo;
      for(BackupEntity b: backupRepository.findAll()) {
         bvo = new BackupVO();
         bvo.name = b.getName();
         bvo.srcip = b.getSrcip(); 
         bvo.id = b.getId();
         response.add(bvo);
      }
      return response;
    }

    /*public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }*/
}
