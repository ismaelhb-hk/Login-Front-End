package login.front.end.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

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

    @Test
    public void testEstadoInicialCamposVacios() {
        verifyThat("#txtEmail", org.testfx.matcher.control.TextInputControlMatchers.hasText(""));
        verifyThat("#pwdPassword", org.testfx.matcher.control.TextInputControlMatchers.hasText(""));
        verifyThat("#lblError", hasText(""));
    }

    @Test
    public void testValidacionEmailVacio() {
        clickOn("#pwdPassword").write("123456");
        clickOn("#txtEmail");

        verifyThat("#lblError", hasText("Email is empty"));
    }

    @Test
    public void testValidacionPasswordVacio() {
        clickOn("#txtEmail").write("ismael@gmail.com");
        clickOn("#pwdPassword");

        verifyThat("#lblError", hasText("Password is empty"));
    }

    @Test
    public void testValidacionEmailInvalido() {
        clickOn("#txtEmail").write("correomalisimo");
        clickOn("#pwdPassword").write("123456");

        verifyThat("#lblError", hasText("Invalid email"));
    }

    @Test
    public void testIntentoSignInUsuarioNoExistente() {
        clickOn("#txtEmail").write("ismael@gmail.com");
        clickOn("#pwdPassword").write("IsmaelGuapo");
        clickOn("#btnSignIn");
        // Descomenta la línea según si pruebas con GlassFish encendido o apagado:
        
        verifyThat("#lblError", hasText("This account does not exist"));
        // verifyThat("#lblError", hasText("No se puede conectar con el servidor"));
    }
}