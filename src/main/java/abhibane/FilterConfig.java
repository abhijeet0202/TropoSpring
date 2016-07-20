package abhibane;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Configurable
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean conversationLoggingFilterRegistration() {
    
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("RequestFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }
    
}
