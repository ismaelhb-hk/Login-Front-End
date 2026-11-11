/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.front.end.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
/**
 *
 * @author ismah
 */
public class SignInViewControllerTest extends ApplicationTest {
    private SignInViewController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInView.fxml"));
        Parent root = loader.load();
        
        controller = loader.getController();
        controller.setStage(stage);
        
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    //Campos se inicialicen vacios
    
    // Comprobar email vacio
    
    //Comprobar Password vacia
    
    // comprobar formato correcto Email
    
    
    //Intento de sesion con datos invalidos 
}
