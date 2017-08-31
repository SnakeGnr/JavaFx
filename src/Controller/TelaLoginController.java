/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SnakeGnr
 */
public class TelaLoginController implements Initializable {

    @FXML
    private TextField txEmail;
    @FXML
    private PasswordField txSenha;
    @FXML
    private Button btEntrar;
    @FXML
    private Button btSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btEntrar.setOnMouseClicked((MouseEvent e) -> {
            //System.out.println("Entra");  
            Logar();
        });

        btEntrar.setOnKeyPressed((KeyEvent e) -> {
            //System.out.println("Entra");  
            if (e.getCode() == KeyCode.ENTER) {
                Logar();
            }
        });
        btSair.setOnMouseClicked((MouseEvent e) -> {
            Stage stage = (Stage) btEntrar.getScene().getWindow();
            stage.close();
        });

        btSair.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                Stage stage = (Stage) btEntrar.getScene().getWindow();
                stage.close();
            }
        });
        txSenha.setOnKeyPressed((KeyEvent e) -> {  
            if (e.getCode() == KeyCode.ENTER) {
                Logar();
            }
        });
    }
    public void abreMenu() {
        Main main = new Main();
        main.abreTela("TelaPrincipal.fxml", "Menu Principal");
        //cria um stage que recebe o stage em que o botão está! 
        Stage stage = (Stage) btEntrar.getScene().getWindow();
        stage.close();
    }

    public void Logar() {
        if (txEmail.getText().equals("admin") && txSenha.getText().equals("admin")) {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Sucesso, Login correto!");
                alert.showAndWait();
                abreMenu();
            } catch (Exception ex) {
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERRO, Login ou Senha Inválidos!");
            alert.show();
        }
    }
}
