/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pessoa;
import dao.PessoaDao;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class TelaCadastrarPessoaController implements Initializable {

    @FXML
    private TextField txNome;
    @FXML
    private TextField txEmail;
    @FXML
    private PasswordField txSenha;
    @FXML
    private Button btCadastrar;
    @FXML
    private Button btCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btCadastrar.setOnMouseClicked((MouseEvent e) -> {
            Cadastrar();
        });
        btCadastrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                Cadastrar();
            }
        });
        txSenha.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                Cadastrar();
            }
        });

        btCancelar.setOnMouseClicked((MouseEvent e) -> {
            abrePrincipal();
        });

    }

    public void abrePrincipal() {
        try {
            Main main = new Main();
            main.abreTela("TelaPrincipal.fxml", "Menu Principal");

            Stage stage = (Stage) btCancelar.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            System.out.println("Erro ao abrir tela principal");
        }
    }

    private void Cadastrar() {
        String nome = txNome.getText(),
                email = txEmail.getText(),
                senha = txSenha.getText();

        if (txNome.getText().equals("") || txEmail.equals("") || txSenha.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Erro Preencha todos os campos!");
            alert.show();

        } else {
            try {
                Pessoa p = new Pessoa(nome, email, senha);
                PessoaDao dao = new PessoaDao();
                dao.add(p);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Pessoa Cadastrada com sucesso!");

                alert.setContentText("Deseja cadastrar uma nova pessoa? ");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        LimpaCampos();
                    } else {
                        abrePrincipal();
                    }
                });
            } catch (SQLException e) {
                System.out.println("Erro ao cadastrar pessoinha! " + e.getMessage());
            }
        }
    }

    public void LimpaCampos() {
        txNome.setText("");
        txEmail.setText("");
        txSenha.setText("");
    }
}
/* if (dao.add(p) == true) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Pessoa Cadastrada com sucesso!");
                alert.showAndWait();
                main.abreTela("TelaPrincipal.fxml", "Menu Principal");
                s.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Erro Pessoa Nao Cadastrada!");
                alert.show();
            }*/
