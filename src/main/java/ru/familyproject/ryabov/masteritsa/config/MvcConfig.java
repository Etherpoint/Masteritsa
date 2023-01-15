package ru.familyproject.ryabov.masteritsa.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * EN: Configuration for customizing the login page and user registration<br>
 * RU: Конфигурация для кастомизации страницы логина и регистрации пользователя
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    public String path;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/account-images/**")
                .addResourceLocations("file://" + path.substring(3) + "/");
    }
}
