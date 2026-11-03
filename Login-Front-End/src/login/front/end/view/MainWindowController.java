/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login.front.end.view;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import login.front.end.model.User;
    /**
     * Controlador de la ventana principal (MainWindow).
     * Se muestra tras un inicio de sesión correcto y ofrece un saludo
     * personalizado, un mensaje de agradecimiento y las opciones de
     * cambiar contraseña y cerrar sesión.
     *
     * @author ismael
     * @version 1.0
     */
public class MainWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    


// Botón que muestra el mensaje de agradecimiento. 
@FXML
private Button btnThanks;


// Botón que muestra el saludo personalizado. 
@FXML
private Button btnWelcome;



//Etiqueta donde se escribe el mensaje de agradecimiento. 
@FXML
private Label lblThanks;


// Etiqueta donde se escribe el saludo con el nombre del usuario. 
@FXML
private Label lblWelcome;


// Enlace para abrir la ventana de cambio de contraseña. 
@FXML
private Hyperlink linkChangePassword;


// Enlace para cerrar la sesión y volver al inicio de sesión. 
@FXML
private Hyperlink linkLogout;



private Stage stage;


public void setStage(Stage stage) {
    this.stage = stage; }

private User user;

public void setUser(User user) {
    this.user = user;
}
    
    @FXML
    void handleThanks(ActionEvent event) {
          // Escribimos el mensaje de agradecimiento en la etiqueta
          
          this.lblThanks.setText("Muchas Gracias :)");
          this.lblWelcome.setText("");
        
    }

    @FXML
    void handleWelcome(ActionEvent event) {
        // Muestra un saludo personalizado con el nombre del usuario
        // que ha iniciado sesión.
        
        this.lblWelcome.setText("Bienvenido "+ user.getFirstName()+" "+user.getLastName());
        this.lblThanks.setText("");
    }
    
      //Cada ventana tiene su propio controlador 


    @FXML
    public void handleLogout(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInView.fxml"));
        Parent root = loader.load();
        
        
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(new Scene(root));
        
        SignInViewController controller = loader.getController();
        controller.setStage(nuevaVentana);
        nuevaVentana.show();
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();  
        
    } catch (IOException ex) {
        System.getLogger(MainWindowController.class.getName()).log(System.Logger.Level.ERROR, "Error al cargar la vista de Login", ex);
    }
}


@FXML
    public void handleChangePassword(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePasswordView.fxml"));
            Parent root = loader.load();
            
            Stage nuevaVentana = new Stage();
            nuevaVentana.setScene(new Scene(root));
            
            // ChangePasswordViewController controller = loader.getController();
            //controller.setStage(nuevaVentana);
            //controller.setUser(this.user); // Fundamental: pasamos el usuario
            
            nuevaVentana.show();
            this.stage.close(); // Cerramos la ventana principal
            
        } catch (Exception ex) {
            System.getLogger(MainWindowController.class.getName()).log(System.Logger.Level.ERROR, "Error al cargar ChangePassword", ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}






