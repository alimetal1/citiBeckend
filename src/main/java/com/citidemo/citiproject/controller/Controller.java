package com.citidemo.citiproject.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(tags = "Test Controller", description = "Controlador de prueba para Swagger")
public class Controller {

    @GetMapping("/hello")
    @ApiOperation("Saludo básico")
    public String hello() {
        return "Hola desde el controlador de prueba";
    }

    @GetMapping("/square")
    @ApiOperation("Calcular el cuadrado de un número")
    public int square(int number) {
        return number * number;
    }
}

