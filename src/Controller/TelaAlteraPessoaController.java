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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SnakeGnr
 */
public class TelaAlteraPessoaController implements Initializable {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private PasswordField txtSenha;

    //quando a tla for iniciada selecionada irá receber a Pessoa selecionada da tela de consulta
    Pessoa selecionada = TelaConsultaController.getSelecionada();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheCaixas();
        acaoBotoes();
    }

    
    public void preencheCaixas() {
        txtID.setText(String.valueOf(selecionada.getId()));
        txtNome.setText(selecionada.getNome());
        txtEmail.setText(selecionada.getEmail());
        txtSenha.setText(selecionada.getSenha());

        txtID.setEditable(false);

    }

    public void acaoBotoes() {
        btnCancelar.setOnMouseClicked(event -> {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
            
            Main m = new Main();
            m.abreTela("TelaConsulta.fxml","Consulta");
        });

        btnAlterar.setOnMouseClicked(event -> {
            try {
                //cria um novo objeto com os valores das caixas de testo
                Pessoa p = new Pessoa();
                p.setId(Integer.valueOf(txtID.getText()));
                p.setNome(txtNome.getText());
                p.setEmail(txtEmail.getText());
                p.setSenha(txtSenha.getText());

                //ReCadastra esse objeto no mesmo ID
                PessoaDao dao = new PessoaDao();
                dao.update(p);

                Stage stage = (Stage) btnCancelar.getScene().getWindow();
                stage.close();
                Main m = new Main();
                m.abreTela("TelaConsulta.fxml", "Consulta");
              
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Pessoa alterada com sucesso!");
                a.show();
            } catch (SQLException e) {
                System.out.println("Erro ao alterar pessoinha!" + e.getMessage());
            }
        });
    }

}
