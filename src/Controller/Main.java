/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author SnakeGnr
 */
public class Main extends Application {
   
    private String caminhoTela = "TelaLogin.fxml";
    private String tituloTela = "Login";
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("/View/"+caminhoTela));//Carrega o arquivo fxml
      Scene scene = new Scene(root); //coloca o fxml em uma cena
      stage.setScene(scene);//Coloca essa cena(fxml) em uma janela
      stage.setResizable(false);
      stage.show();//abre a tela (janela2)
      stage.setTitle(tituloTela);
     // coloca o incone na tela
      stage.getIcons().add(new Image("file:///"+System.getProperty("user.dir")+"\\src\\img\\person.png"));
    }
    
   public void abreTela(String nomeTela, String Titulo){
        caminhoTela = nomeTela;
        tituloTela = Titulo;
        Stage stage = new Stage();
        try {
            start(stage);
        } catch (Exception ex) {
            System.out.println("Erro ao abrir tela!" + ex.getMessage() );
        }        
    }
}
