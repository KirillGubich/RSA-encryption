package by.bsuir.poit.rsa.controller;

import by.bsuir.poit.rsa.model.RsaKeyGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigInteger;

public class KeyCalculationController {

    @FXML
    private TextField pValueInput;

    @FXML
    private TextField qValueInput;

    @FXML
    private Button generateKeyBtn;

    @FXML
    private TextField rField;

    @FXML
    private TextField publicKeyField;

    @FXML
    private TextField privateKeyField;

    @FXML
    void generateKey(ActionEvent event) {
        RsaKeyGenerator keyGen = RsaKeyGenerator.INSTANCE;
        BigInteger pValue;
        BigInteger qValue;
        try {
            pValue = new BigInteger(pValueInput.getText());
            qValue = new BigInteger(qValueInput.getText());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
        BigInteger publicKey = keyGen.generatePublicKey(pValue, qValue);
        BigInteger rValue = pValue.multiply(qValue);
        BigInteger privateKey = keyGen.calculatePrivateKey(pValue, qValue, publicKey);
        rField.setText(rValue.toString());
        publicKeyField.setText(publicKey.toString());
        privateKeyField.setText(privateKey.toString());
    }
}
