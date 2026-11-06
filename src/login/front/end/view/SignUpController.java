/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
/*
 * Controlador de la vista Sign .
 */
package login.front.end.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtDomicilio;
    @FXML private TextField txtCiudad;
    @FXML private TextField txtEmail;
    @FXML private TextField txtEstado;
    @FXML private TextField txtCodigoPostal;
    @FXML private TextField txtTelefono;
    @FXML private PasswordField pwdPassword;
    @FXML private PasswordField pwdConfirmPassword;
    @FXML private Label lblError;
    @FXML private Button btnSignUp;
    @FXML private Hyperlink linkSignIn;

    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblError.setText("");
        btnSignUp.setDisable(true);

        // Actualiza la validación cada vez que cambia un campo.
        txtNombre.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        txtApellido.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        txtDomicilio.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        txtCiudad.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        txtEmail.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        txtEstado.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        txtCodigoPostal.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        txtTelefono.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        pwdPassword.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        pwdConfirmPassword.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
    }

    public boolean camposCompletos() {
        return !txtNombre.getText().trim().isEmpty()
                && !txtApellido.getText().trim().isEmpty()
                && !txtDomicilio.getText().trim().isEmpty()
                && !txtCiudad.getText().trim().isEmpty()
                && !txtEmail.getText().trim().isEmpty()
                && !txtEstado.getText().trim().isEmpty()
                && !txtCodigoPostal.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty()
                && !pwdPassword.getText().isEmpty()
                && !pwdConfirmPassword.getText().isEmpty();
    }

    public boolean emailValido() {
        return EMAIL_PATTERN.matcher(txtEmail.getText().trim()).matches();
    }

    public boolean contrasenasCoinciden() {
        return pwdPassword.getText().equals(pwdConfirmPassword.getText());
    }

    public void validarFormulario() {
        boolean valido = camposCompletos()
                && emailValido()
                && contrasenasCoinciden()
                && pwdPassword.getText().length() >= 8;

        btnSignUp.setDisable(!valido);

        if (txtEmail.getText().trim().isEmpty()) {
            lblError.setText("");
        } else if (!emailValido()) {
            lblError.setText("Introduce un correo electrónico válido.");
        } else if (!pwdPassword.getText().isEmpty() && pwdPassword.getText().length() < 8) {
            lblError.setText("La contraseña debe tener al menos 8 caracteres.");
        } else if (!pwdConfirmPassword.getText().isEmpty() && !contrasenasCoinciden()) {
            lblError.setText("Las contraseñas no coinciden.");
        } else {
            lblError.setText("");
        }
    }

    // Métodos vinculados a los campos desde el FXML.
    @FXML public void handleNameTextChange() { validarFormulario(); }
    @FXML public void handleSecondNameTextChange() { validarFormulario(); }
    @FXML public void handleAddressTextChange() { validarFormulario(); }
    @FXML public void handleCityTextChange() { validarFormulario(); }
    @FXML public void handleEmailTextChange() { validarFormulario(); }
    @FXML public void handleStateTextChange() { validarFormulario(); }
    @FXML public void handlePostalCodeTextChange() { validarFormulario(); }
    @FXML public void handleMobileTextChange() { validarFormulario(); }
    @FXML public void handlePasswordTextChange() { validarFormulario(); }

    // Alias para que coincidan tanto los nombres del FXML como los del controlador.
    @FXML public void handleConfirmPasswordTextChange() { validarFormulario(); }
    @FXML public void handleConfirmPasswordChange() { validarFormulario(); }

    @FXML
    public void handleSignUp() {
        validarFormulario();

        if (btnSignUp.isDisabled()) {
            lblError.setText("Revisa los datos del formulario antes de continuar.");
            return;
        }

        /*
         * Aquí todavía no se crea ni se guarda una cuenta.
         * Para ello hay que conectar el controlador con la base de datos
         * o con el servicio que gestione los usuarios.
         */
        lblError.setText("Formulario válido. Falta conectar el registro con el sistema de usuarios.");
    }

    @FXML
    public void handleGoToSignIn() {
        /*
         * La navegación real depende del nombre y la ruta de la vista de login.
         * Por ahora se muestra un aviso para no asumir una ruta que no conocemos.
         */
        lblError.setText("Para abrir el inicio de sesión, conecta este enlace con tu vista de login.");
    }
}

