/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SignInViewControllerTest;

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
    
    //Empezamos con los campos vacios
    @Test
    public void testEstadoInicialCamposVacios() {
        verifyThat("#txtEmail", org.testfx.matcher.control.TextInputControlMatchers.hasText(""));
        verifyThat("#pwdPassword", org.testfx.matcher.control.TextInputControlMatchers.hasText(""));
        verifyThat("#lblError", hasText(""));
    }
}
