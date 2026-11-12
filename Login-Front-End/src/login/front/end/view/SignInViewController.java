package login.front.end.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */



import jakarta.ws.rs.core.GenericType;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
/**
 *this is the code controller 
 * @author ismael
 */
public class SignInViewController implements Initializable {
    private static final Logger logger = 
    Logger.getLogger("login.front.end.view.SignInViewController");
    
    
    @FXML
    private TextField txtEmail ;
    //El campo de email
    @FXML
    private PasswordField pwdPassword ;
    //El campo de Contraseña
    @FXML
    private Button btnSignIn;  
    //El boton para acceder a la MainWindows
    
    @FXML
    private Hyperlink linkSignUp ; 
    //Campo de enlace para ir al SignUp
    
    @FXML
    private Label lblError;    
    //Campo de Errores
    
    private Stage stage ;
    
    public void setStage(Stage stage) {
    this.stage = stage;
    }
    
    
    

    public void init() {
        // Add listener for window
        stage.setOnShown(this::handleWindowOpen);
        this.txtEmail.textProperty().addListener(this::handleTextChange);
        this.pwdPassword.textProperty().addListener(this::handleTextChange);
        
    }
    
   
    
    
    
    @FXML
    public void handleTextChange (ObservableValue observable , String oldValue,
                                                              String newValue){
        //Borrar los mensajes de la etiqueta de error 
        this.lblError.setText("");
        String regexp = "^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$"; 
        
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
        

        if (!this.txtEmail.getText().matches(regexp)) {
            this.lblError.setText("Invalid email");
    }
            
}
        
        
    
    
    @FXML
    public void handleSignIn (ActionEvent e){
    UserRESTClient client = new UserRESTClient(); 
    

    //Si lblError tiene texto o algún campo está vacío, no hacemos nada 
        if(!this.lblError.getText().isEmpty() || this.txtEmail.getText().isEmpty() || this.pwdPassword.getText().isEmpty()){
        return;
        }

    try {
        //  si todo es correcto mandarlo al
        // backend mediante una peticion http
        // con Metodo get 
        List <User> users  ;
        users =client.findAll_XML(new GenericType <List<User>>(){} );
        
        //Con la funcion stream comparamos los campos que optenemos de nuestra lista de La clase 
        //User y lo comparamos con la funcion equals con los que pone el usuario 
            if(users.stream()
                .filter(user->user.getEmail().equals(this.txtEmail.getText()))
                .filter(user->user.getPassword().equals(this.pwdPassword.getText()))
                .count()==0){
            this.lblError.setText("This account does not exist");
            return;
            }
            
        //Obtenemos el usuario y lo guardamos en found para comunicarselo a nuestro controlador
        // de la mainWindows
        User found = users.stream()
        .filter(user -> user.getEmail().equals(this.txtEmail.getText()))
        .filter(user -> user.getPassword().equals(this.pwdPassword.getText()))
        .findFirst()
        .get();
   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

        Parent root = loader.load();
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(new Scene(root));
        
        MainWindowController controller = loader.getController();
        controller.setUser(found);
        controller.setStage(nuevaVentana);
        
        nuevaVentana.show();
        this.stage.close(); // Cerramos la ventana de Login

    } catch (Exception ex) {
        //  Mostrar el error
        this.lblError.setText("Error devuelto por el servidor  \n Inténtalo mas tarde \n o contacta con el soporte técnico");
        logger.severe("Error al conectar con el servidor: " + ex.getMessage());

        
    } finally {
         client.close();
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
    


