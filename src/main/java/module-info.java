module com.example.restaurante3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.restaurante3.controller to javafx.graphics, javafx.fxml, javafx.base;
    opens com.example.restaurante3.domain to javafx.graphics, javafx.fxml, javafx.base;

    exports com.example.restaurante3;
    exports com.example.restaurante3.controller;
    exports com.example.restaurante3.domain;
    exports com.example.restaurante3.repo.db;
    exports com.example.restaurante3.service;
    exports com.example.restaurante3.dto;
    exports com.example.restaurante3.utils.observer;
    exports com.example.restaurante3.utils.event;
}