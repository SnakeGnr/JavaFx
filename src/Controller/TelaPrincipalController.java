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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author SnakeGnr
 */
public class TelaPrincipalController implements Initializable {
    @FXML private Button btnGerarPdf;
    @FXML private ImageView imgFundo;
    @FXML  private Button btnSair;
    @FXML private Button btCadastrarPessoa;
    @FXML private Button btListarPessoa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setaImagenFundo();
        acaoDosBotoes();
    }

    public void acaoDosBotoes(){
        
        btCadastrarPessoa.setOnMouseClicked((MouseEvent e) -> {
            abreCadastroPessoa();
        });
        btCadastrarPessoa.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                abreCadastroPessoa();
            }
        });

        btListarPessoa.setOnMouseClicked((MouseEvent e) -> {
            ListaPessoa();
        });
        btListarPessoa.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                ListaPessoa();
            }
        });       
    }
    
    public void abreCadastroPessoa() {
        try {
            Main main = new Main();
            main.abreTela("TelaCadastrarPessoa.fxml", "Cadastro Pessoa");
            fecha();
        } catch (Exception ex) {
            System.out.println("Erro ao abrir tela cadastro"+ ex.getMessage());
        }
    }

    public void ListaPessoa() {
        try {
            Main main = new Main();
            main.abreTela("TelaConsulta.fxml", "Pessoas Cadastradas");
            fecha();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela Listar"+ e.getMessage());
        }
    }

    public void fecha() {
        Stage stage = (Stage) btCadastrarPessoa.getScene().getWindow();
        stage.close();
    }
    
    public void setaImagenFundo(){
            imgFundo.setImage(new Image("file:///"+System.getProperty("user.dir")+"\\src\\img\\background.jpg"));
}
    
    /*public void ListaPessoa(){
        System.out.println("LISTANDO PESSOAS");
        List<Pessoa> pessoas = new PessoaDao().getList();
        for(int i = 0; i < pessoas.size(); i++){
            pessoas.get(i).mostraPessoa();
        }  
    }*/
}
