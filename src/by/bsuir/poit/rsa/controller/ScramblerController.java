package by.bsuir.poit.rsa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class ScramblerController {

    private static final String KEY_WINDOW_TITLE = "Public key calculator";
    @FXML
    private MenuItem keyCalculateMenu;

    @FXML
    void viewKeyCalculatorWindow(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/keyCalculation.fxml"));
            loader.load();
            root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(KEY_WINDOW_TITLE);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}