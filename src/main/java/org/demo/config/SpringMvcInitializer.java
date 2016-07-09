package org.demo.config;

import org.demo.filters.MyAuthenticationFilter;
import org.demo.service.security.MyAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class SpringMvcInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MyAppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Autowired
    private MyAuthenticationService authenticationService;
    @Override
    protected Filter[] getServletFilters() {
        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        System.out.println("******** getFilter");
        System.out.println(authenticationService);
        filter.setAuthenticationService(authenticationService);
        return new Filter[]{ filter };
    }

}
