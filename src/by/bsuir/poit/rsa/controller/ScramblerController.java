package by.bsuir.poit.rsa.controller;

import by.bsuir.poit.rsa.model.HashGenerator;
import by.bsuir.poit.rsa.model.RsaScrambler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigInteger;

public class ScramblerController {

    private static final String KEY_WINDOW_TITLE = "Key generator";
    private static final String KEY_GEN_FXML_PATH = "../view/keyGenerator.fxml";
    private final RsaScrambler rsaScrambler = RsaScrambler.INSTANCE;
    private final HashGenerator hashGenerator = HashGenerator.INSTANCE;
    private static final String CORRECT_SIGNATURE_MESSAGE = "Signature correct";
    private static final String INCORRECT_SIGNATURE_MESSAGE = "Incorrect signature!";

    @FXML
    private TextArea inputText;

    @FXML
    private TextField keyInput;

    @FXML
    private TextField rInput;

    @FXML
    private Button encryptBtn;

    @FXML
    private Button decryptBtn;

    @FXML
    private TextArea resultText;

    @FXML
    private TextField signField;

    @FXML
    private Button countBtn;

    @FXML
    private Button checkBtn;

    @FXML
    private Label signCheckLbl;

    @FXML
    void checkSignature(ActionEvent event) {
        signCheckLbl.setText("");
        String text = inputText.getText();
        String decryptedSign;
        BigInteger hash;
        try {
            BigInteger key = new BigInteger(keyInput.getText());
            BigInteger r = new BigInteger(rInput.getText());
            hash = hashGenerator.hashText(text, r);
            String sign = signField.getText();
            decryptedSign = rsaScrambler.encryptNumbers(sign, key, r, true);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (hash.toString().equals(decryptedSign)) {
            signCheckLbl.setText(CORRECT_SIGNATURE_MESSAGE);
        } else {
            signCheckLbl.setText(INCORRECT_SIGNATURE_MESSAGE);
        }
    }

    @FXML
    void checkSignatureNumbers(ActionEvent event) {
        signCheckLbl.setText("");
        String text = inputText.getText();
        String decryptedSign;
        BigInteger hash;
        try {
            BigInteger key = new BigInteger(keyInput.getText());
            BigInteger r = new BigInteger(rInput.getText());
            hash = hashGenerator.hashNumeric(text, r);
            String sign = signField.getText();
            decryptedSign = rsaScrambler.encryptNumbers(sign, key, r, true);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (hash.toString().equals(decryptedSign)) {
            signCheckLbl.setText(CORRECT_SIGNATURE_MESSAGE);
        } else {
            signCheckLbl.setText(INCORRECT_SIGNATURE_MESSAGE);
        }
    }

    @FXML
    void countSignature(ActionEvent event) {
        String text = inputText.getText();
        try {
            BigInteger key = new BigInteger(keyInput.getText());
            BigInteger r = new BigInteger(rInput.getText());
            BigInteger hash = hashGenerator.hashText(text, r);
            String signature = rsaScrambler.encryptNumbers(hash.toString(), key, r, true);
            signField.setText(signature);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void countNumericSignature(ActionEvent event) {
        String text = inputText.getText();
        try {
            BigInteger key = new BigInteger(keyInput.getText());
            BigInteger r = new BigInteger(rInput.getText());
            BigInteger hash = hashGenerator.hashNumeric(text, r);
            String signature = rsaScrambler.encryptNumbers(hash.toString(), key, r, true);
            signField.setText(signature);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void encrypt(ActionEvent event) {
        String text = inputText.getText();
        try {
            BigInteger key = new BigInteger(keyInput.getText());
            BigInteger r = new BigInteger(rInput.getText());
            String result = RsaScrambler.INSTANCE.encryptText(text, key, r);
            resultText.setText(result);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void decrypt(ActionEvent event) {
        String text = inputText.getText();
        try {
            BigInteger key = new BigInteger(keyInput.getText());
            BigInteger r = new BigInteger(rInput.getText());
            String result = rsaScrambler.encryptNumbers(text, key, r, false);
            resultText.setText(result);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
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