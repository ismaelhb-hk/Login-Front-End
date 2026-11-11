/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package login.front.end.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author E. Jamil
 */
public class SignUpController implements Initializable {
    
    @FXML
    private TextField txtNombre; 
    
    @FXML
    private TextField txtApellido;
    
    @FXML
    private TextField txtDomicilio;

    @FXML
    private TextField txtCiudad;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtEstado;
    
    @FXML
    private TextField txtCodigoPostal;
    
    @FXML
    private TextField txtTelefono;
    
    @FXML
    private PasswordField pwdPassword;    

    @FXML
    private PasswordField pwdConfirmPassword;

    @FXML
    private Label lblError;
    
    @FXML
    private Button btnSignUp;
    
    @FXML
    private Hyperlink linkSignIn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void handleEmailTextChange(){
        
    }
    
    @FXML
    public void handleSignUp(){
        
    }
    
    @FXML
    public void handleGoToSignIn(){
        
    }
    
    @FXML
    public void handlePasswordTextChange(){
        
    }
    
    @FXML
    public void handleConfirmPasswordChange(){
        
    }

    
    
}
