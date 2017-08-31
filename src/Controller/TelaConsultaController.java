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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SnakeGnr
 */
public class TelaConsultaController implements Initializable {

    @FXML
    private TableView<Pessoa> tabelaPessoa;
    @FXML
    private TableColumn<Pessoa, Long> colunaID;
    @FXML
    private TableColumn<Pessoa, String> colunaNome;
    @FXML
    private TableColumn<Pessoa, String> colunaEmail;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnVoltar;

    private static Pessoa selecionada;

    public static Pessoa getSelecionada() {
        return selecionada;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTabela();

        //Lógica para sempre pegar a pessoa selecionada na Tabela e montar um Objeto pessoa "selecionada"    
        tabelaPessoa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                //System.out.println("selecionei");
                selecionada = (Pessoa) newValue;
            }
        });

        btnVoltar.setOnMouseClicked(event -> {
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.close();;
            abreMenu();
        });

        btnDeletar.setOnMouseClicked(event -> {
            deleta();
        });

        btnAlterar.setOnMouseClicked(event -> {
           Stage stage = (Stage) btnAlterar.getScene().getWindow();
            stage.close();
            Main m = new Main();
            m.abreTela("TelaAlteraPessoa.fxml", "Alterar");
            
        });

    }

    public void deleta() {

        if (getSelecionada() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Deseja excluir o usuário de id: " + getSelecionada().getId());
            alert.setContentText("A operação não poderá ser desfeita!");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        PessoaDao dao = new PessoaDao();
                        dao.delete(getSelecionada());
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setHeaderText("Pessoa deletada com sucesso!");
                        a.show();
                        iniciaTabela();
                    } catch (SQLException e) {
                        System.out.println("Erro ao deletar pessoinha!" + e.getMessage());
                    }
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Erro, Selecione um usuário!");
            alert.show();
        }
    }

    public ObservableList<Pessoa> atualizaTabela() {
        try {
            PessoaDao dao = new PessoaDao();

            //coloca a lista que a DAo retorna em uma ObservableList
            //A table do JavaFx só aceita ObservableList
            return FXCollections.observableList(dao.getList());
        } catch (SQLException e) {
            System.out.println("Erro ao att tabela!" + e.getMessage());
            return null;
        }
    }

    public void iniciaTabela() {
        //diz qe a coluna será preenchida pelos seguintes atributos (" ")
        colunaID.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory("email"));

        //coloca o método que retorna uma ObservableList na tabela do JavaFx
        tabelaPessoa.setItems(atualizaTabela());
    }

    public void abreMenu() {
        Main m = new Main();
        m.abreTela("TelaPrincipal.fxml", "Menu Principal");
    }
}
