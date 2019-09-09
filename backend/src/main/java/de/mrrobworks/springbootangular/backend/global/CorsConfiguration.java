package de.mrrobworks.springbootangular.backend.global;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** CORS-Configuration for Communication between Angular-Frontend and Spring-Boot-Backend. */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@CrossOrigin(origins = "http://localhost:4200")
public @interface CorsConfiguration {}
