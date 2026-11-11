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
    @Test
    public void testEstadoInicialCamposVacios() {
        verifyThat("#txtEmail", org.testfx.matcher.control.TextInputControlMatchers.hasText(""));
        verifyThat("#pwdPassword", org.testfx.matcher.control.TextInputControlMatchers.hasText(""));
        verifyThat("#lblError", hasText(""));
    }
    // Comprobar email vacio
    @Test
      public void testValidacionEmailVacio() {
          clickOn("#pwdPassword").write("hiwhihwohdio");
          // Forzamos el evento de cambio de texto
          clickOn("#txtEmail");

          verifyThat("#lblError", hasText("Email is empty"));
    }  
    //Comprobar Password vacia
    @Test
        public void testValidacionPasswordVacio() {
            clickOn("#txtEmail").write("ismael@gmail.com");
            clickOn("#pwdPassword");

            verifyThat("#lblError", hasText("Password is empty"));
    }    
    // comprobar formato correcto Email
    @Test
    public void testValidacionEmailInvalido() {
        clickOn("#txtEmail").write("correomalisimo");
        clickOn("#pwdPassword").write("djnjnwuw");
        
        verifyThat("#lblError", hasText("Invalid email"));
    }
    
    //Intento de sesion con datos invalidos 
    @Test
    public void testIntentoSignInUsuarioNoExistente() {
        clickOn("#txtEmail").write("ismael@gmail.com");
        clickOn("#pwdPassword").write("ismaelGuapo");
        clickOn("#btnSignIn");
        verifyThat("#lblError", hasText("This account does not exist"));
        //Se puede comentar la ultima linea y apagar el servidor y ver si recibimos
        //El error que esperamos
    }
    
    
}
