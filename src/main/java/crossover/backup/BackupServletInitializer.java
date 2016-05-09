package crossover.backup;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class BackupServletInitializer extends SpringBootServletInitializer {
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackupController.class);
    }
	
//	// gets invoked automatically when application starts up
//	  public void onStartup(ServletContext servletContext) throws ServletException {
//
//	    // Create ApplicationContext. I'm using the
//	    // AnnotationConfigWebApplicationContext to avoid using beans xml files.
//	    AnnotationConfigWebApplicationContext ctx =
//	        new AnnotationConfigWebApplicationContext();
//	    ctx.register(BackupController.class);
//
//	    // Add the servlet mapping manually and make it initialize automatically
//	    Dynamic servlet =
//	        servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//	    servlet.addMapping("/");
//	    servlet.setLoadOnStartup(1);
//	  }
}
