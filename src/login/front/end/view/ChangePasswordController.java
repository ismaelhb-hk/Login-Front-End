/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package login.front.end.view;


import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import login.front.end.client.UserRESTClient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import login.front.end.model.User;

/**
 *  This is the controller class for Change Password window.
 * @author Abdel
 */
public class ChangePasswordController{
    @FXML
    private PasswordField currentPasswordField;
    
    @FXML
    private PasswordField newPasswordField;
    
    @FXML
    private PasswordField confirmPasswordField;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private Label matchLabel;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Button changeButton;   
    @FXML   
    private Stage stage ;
    
    private User user;
    
    public void setUser(User user){
        
        this.user=user;
    }
    
    public void setStage(Stage stage) {
        
    this.stage = stage;
    }
        public void init() {
        // Add listener for window
        stage.setOnShown(this::handleWindowOpen);
        //Add listener for the text fields text change
        
        this.newPasswordField.textProperty().addListener(this::handleNewPasswordTextChange);
        this.confirmPasswordField.textProperty().addListener(this::handleNewPasswordTextChange);
        this.currentPasswordField.textProperty().addListener(this::handleCurrentPasswordTextChange);

        
    }
    
    @FXML
    public void handleWindowOpen(WindowEvent e){
        // All fields empty 
        this.currentPasswordField.setText("");
        this.newPasswordField.setText("");
        this.confirmPasswordField.setText("");
        
        //Focus current password field
        this.currentPasswordField.requestFocus();
    }  
    
    @FXML
    public void handleCurrentPasswordTextChange(ObservableValue observable, String oldvalue, String newvalue){
        //Delete the messageLabel message, so that an old error is not left on the screen.
            this.messageLabel.setText("");
    }
    @FXML
    public void handleNewPasswordTextChange(ObservableValue observable, String oldvalue, String newvalue){
           //Delete the messageLabel message and update matchLabel with the comparison between the new password and the confirmation.
           

            if (newPasswordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
                matchLabel.setText("");
            }
            else if (newPasswordField.getText().equals(confirmPasswordField.getText())) {
                matchLabel.setText("Las contraseñas coinciden ✅");
                this.matchLabel.setStyle("-fx-text-fill:green");
            }
            else {
                this.matchLabel.setStyle("-fx-text-fill:red");
                this.matchLabel.setText("Las contraseñas no coinciden ❌");
            }
            
    }
    /*
    Method to close the window and return to MinWindow without making changes
    */
    @FXML
    public void handleCancel(ActionEvent e){
        // Close the window without saving or sending anything to the backend.
               Stage stage = (Stage) cancelButton.getScene().getWindow();
               stage.close();
        
    }
    /*
    Method to change the password, communicating with the server and controlling possible errors
    */
    @FXML
    public void handleChangePassword(ActionEvent e){
        
        UserRESTClient  client = new UserRESTClient();
        try {
                    //Check empty fields
                     if (currentPasswordField.getText().isEmpty() || newPasswordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
                         throw new Exception("Todos los campos son obligatorios");
                     }
                     //Check if new password is different
                     if (currentPasswordField.getText().equals(newPasswordField.getText()) )
                         throw new Exception("La nueva contraseña debe ser diferente");
                     
                     //Check if passwords match
                    if (!this.confirmPasswordField.getText().equals(this.newPasswordField.getText())) {
                        throw new Exception("Las contraseñas no coinciden");
                    }
                     //check if user password is equal to textfield actual password
                    if (!this.user.getPassword().equals(this.currentPasswordField.getText())) {
                        throw new Exception("Contraseña actual incorrecta");
                    }


                         user.setPassword(this.newPasswordField.getText());

                         client.edit_XML(this.user, user.getId().toString());
                         
                         messageLabel.setStyle("-fx-text-fill:green");
                         messageLabel.setText("Contraseña cambiada correctamente");

                         
            //Handle the internal server error and Inform the User.
            }catch (jakarta.ws.rs.WebApplicationException ex) {

                messageLabel.setStyle("-fx-text-fill:red");
                messageLabel.setText("Error interno del servidor. Inténtalo más tarde.");

             //Handle other errors
            } catch (Exception ex){
                messageLabel.setStyle("-fx-text-fill:red");
                messageLabel.setText(ex.getMessage());
            } finally {
                client.close();
            }
       

        }
       }
       
    

    
    


