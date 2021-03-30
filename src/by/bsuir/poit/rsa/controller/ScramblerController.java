package by.bsuir.poit.rsa.controller;

import by.bsuir.poit.rsa.model.RsaScrambler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigInteger;

public class ScramblerController {

    private static final String KEY_WINDOW_TITLE = "Key generator";
    private static final String KEY_GEN_FXML_PATH = "../view/keyGenerator.fxml";

    @FXML
    private TextArea inputText;

    @FXML
    private TextField keyInput;

    @FXML
    private TextField rInput;

    @FXML
    private Button encryptBtn;

    @FXML
    private TextArea resultText;

    @FXML
    void encryptText(ActionEvent event) {
        String text = inputText.getText();
        BigInteger key;
        BigInteger r;
        try {
            key = new BigInteger(keyInput.getText());
            r = new BigInteger(rInput.getText());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
        String result = RsaScrambler.INSTANCE.encrypt(text, key, r);
        resultText.setText(result);
    }

    @FXML
    void viewKeyGeneratorWindow(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(KEY_GEN_FXML_PATH));
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

    @FXML
    void closeApp(ActionEvent event) {
        Stage stage = (Stage) encryptBtn.getScene().getWindow();
        stage.close();
    }
}