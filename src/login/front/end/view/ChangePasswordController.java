/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package login.front.end.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    
    public void setStage(Stage stage) {
    this.stage = stage;
    }
        public void init() {
        // Add listener for window
        stage.setOnShown(this::handleWindowOpen);
        //Add listener for the text fields text change
        
        this.newPasswordField.textProperty().addListener(this::handleNewPasswordTextChange);
        this.confirmPasswordField.textProperty().addListener(this::handleNewPasswordTextChange);
        
        
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
    public void handleCurrentPasswordTextChange(InputEvent e){
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
                matchLabel.setText("Las contraseñas coinciden");
            }
            else {
                matchLabel.setText("Las contraseñas no coinciden");
            }
    }
    
    @FXML
    public void handleCancel(ActionEvent e){
        // Close the window without saving or sending anything to the backend.
           
        
    }
    
    @FXML
    public void handleChangePassword(ActionEvent e){
        //
    }
    
    public void initialize(URL url, ResourceBundle rb) {
      
    }

}
