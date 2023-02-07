package com.example.restaurante3;

import com.example.restaurante3.controller.HelloController;
import com.example.restaurante3.repo.db.MenuItemRepoDB;
import com.example.restaurante3.repo.db.OrderItemRepoDB;
import com.example.restaurante3.repo.db.OrderRepoDB;
import com.example.restaurante3.repo.db.TableRepoDB;
import com.example.restaurante3.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        HelloController controllerStaff = fxmlLoader.getController();
        controllerStaff.setService(new Service(new TableRepoDB(), new MenuItemRepoDB(), new OrderRepoDB(), new OrderItemRepoDB()));
        controllerStaff.setLabel("Staff");
        controllerStaff.showTables();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}