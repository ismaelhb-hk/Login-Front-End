/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package login.front.end.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *this is the code controller 
 * @author ismael
 */
public class SignInViewController implements Initializable {
    
    @FXML
    private TextField txtEmail ;
    
    @FXML
    private PasswordField pwdPassword ;
    
    @FXML
    private Button btnSignIn;  
    
    
    @FXML
    private Hyperlink linkSignUp ; 


    @FXML
    private Label lblError;    
    private Stage stage ;
    
    

    public void init() {
        // Add listener for window
        stage.setOnShown(this::handleWindowOpen);
        
        
    }
    
    @FXML
    public void handleTextChange (InputEvent e){
        //Borrar los mensajes de la etiqueta de error 
        this.lblError.setText("");
    }
    
    @FXML
    public void handleSignIn (ActionEvent e){
        
    }    
                             
    @FXML
    public void handleGoToSignUp (ActionEvent e){
        //Abrir la ventana de Sign Up de forma modal
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("SignUpView.fxml"));
                    //Creo una escena y cargo la vista en la escena 
            Scene scene = new Scene(root);
            //Mostrar la escena en una ventana
            Stage stage = new Stage();
            stage.setScene(scene);
            //Abrir de forma modal la ventana 
            stage.initModality(Modality.APPLICATION_MODAL
            
            
            );
            stage.show();
        } catch (IOException ex) {
            System.getLogger(SignInViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }    
    @FXML
    public void handleWindowOpen(WindowEvent e){
        //todos los campos estaran vacios
        this.txtEmail.setText("");
        this.pwdPassword.setText("");
        
        //Enfocamos el texto email
        this.txtEmail.requestFocus();
        
        
    }     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
