package com.example.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api") // define o caminho base
public class ApplicationConfig extends Application {
    // Não precisa implementar nada
}
