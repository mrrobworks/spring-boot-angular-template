package de.mrrobworks.springbootangular.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * CORS-Configuration for Communication between Angular-Frontend and Spring-Boot-Backend.
 */
@CrossOrigin(origins = "http://localhost:4200")
@interface CorsConfiguration {

}
