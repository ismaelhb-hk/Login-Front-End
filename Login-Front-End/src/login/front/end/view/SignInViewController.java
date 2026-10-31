package login.front.end.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */


import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
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
import login.front.end.client.UserRESTClient;
import login.front.end.model.User;

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
    public void setStage(Stage stage) {
    this.stage = stage;
}
    
    
    

    public void init() {
        // Add listener for window
        stage.setOnShown(this::handleWindowOpen);
        
        
    }
    
    @FXML
    public void handleTextChange (InputEvent e){
        //Borrar los mensajes de la etiqueta de error 
        this.lblError.setText("");
        
    // Si los dos campos están vacíos, no mostramos ningún error    
    if (this.txtEmail.getText().isEmpty() && this.pwdPassword.getText().isEmpty()) {
        return;
    }
        //Validar que ningún campo está vacío
        //y que el correo tiene formato válido
        //Si no es asi notificarselo al Usuario
        //con nuestro lblError
        if (this.txtEmail.getText().isEmpty()) {
            this.lblError.setText("Email is empty");
        return;
        }

        if(this.pwdPassword.getText().isEmpty()){
            this.lblError.setText("Password is empty");
        return;
        }
        

        String regexp = "^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$"; 
        if (!this.txtEmail.getText().matches(regexp)) {
            this.lblError.setText("Invalid email");
        return;
    }
        //consider using this annotation to enforce field validation
        if (this.txtEmail.getText().isEmpty()) {
            throw new IllegalArgumentException("Email is empty");
        }
       
        }
        
        
    
    
    @FXML
    public void handleSignIn (ActionEvent e){
    UserRESTClient client = new UserRESTClient(); 
    User u = new User ;
    List <users> ;
    //Si lblError tiene texto o algún campo está vacío, no hacemos nada (return)
        if(!this.lblError.getText().isEmpty() || this.txtEmail.getText().isEmpty() || this.pwdPassword.getText().isEmpty()){
        return;
        }
    try {
        //  si todo es correcto mandarlo al
        // backend mediante una peticion http
        // con Metodo get 
     findAll_XML( )throws ClientErrorException {
        WebTarget webTarget = null;
        WebTarget resource = webTarget;
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }    
        
        
        
        
        
     
    } catch (Exception ex) {
        // 3. Mostrar el error: this.lblError.setText("...");
    } finally {
        // 4. client.close();
    }
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
            stage.initModality(Modality.APPLICATION_MODAL);
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


    public void initialize(URL url, ResourceBundle rb) {
      
    }


    
}

