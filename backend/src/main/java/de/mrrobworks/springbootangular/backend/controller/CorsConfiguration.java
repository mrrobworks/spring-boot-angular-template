package de.mrrobworks.springbootangular.backend.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * CORS-Configuration for Communication between Angular-Frontend and Spring-Boot-Backend.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@CrossOrigin(origins = "http://localhost:4200")
@interface CorsConfiguration {

}
