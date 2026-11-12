/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package login.front.end.view;

import jakarta.ws.rs.core.GenericType;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.front.end.client.UserRESTClient;
import login.front.end.model.User;

/**
 * Controller of the sign-up window (SignUp).
 *
 * <p>Validates the form as the user types, checks on the server that the
 * email address is not already registered, and creates the new user through
 * {@link UserRESTClient}. The window is opened modally by SignIn, so the
 * {@link Stage} is obtained from the scene itself and no {@code setStage()}
 * method is needed.</p>
 *
 * @author E. Jamil
 */
public class SignUpController implements Initializable {

    /** Logger used to report errors and relevant events of this controller. */
    public static final Logger logger
            = Logger.getLogger("login.front.end.view.SignUpController");

    /** Text field for the user's first name. */
    @FXML private TextField txtNombre;

    /** Text field for the user's last name. */
    @FXML private TextField txtApellido;

    /** Text field for the user's street address. */
    @FXML private TextField txtDomicilio;

    /** Text field for the user's city. */
    @FXML private TextField txtCiudad;

    /** Text field for the user's email address. */
    @FXML private TextField txtEmail;

    /** Text field for the user's state. */
    @FXML private TextField txtEstado;

    /** Text field for the user's postal (ZIP) code. */
    @FXML private TextField txtCodigoPostal;

    /** Text field for the user's phone number. */
    @FXML private TextField txtTelefono;

    /** Password field for the new password. */
    @FXML private PasswordField pwdPassword;

    /** Password field for the password confirmation. */
    @FXML private PasswordField pwdConfirmPassword;

    /** Label where validation and server error messages are displayed. */
    @FXML private Label lblError;

    /** Button that submits the form and creates the account. */
    @FXML private Button btnSignUp;

    /** Link that takes the user back to the sign-in window. */
    @FXML private Hyperlink linkSignIn;

    /**
     * Regular expression used to validate email addresses.
     *
     * <p>It is the same expression used by SignInViewController, so any email
     * accepted here can also be typed later to sign in.</p>
     */
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*"
            + "@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+"
            + "[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$");

    /**
     * Regular expression used to validate the postal code.
     *
     * <p>{@code zip} is an {@code Integer} in the entity, so 5 digits fit with
     * plenty of room to spare.</p>
     */
    public static final Pattern ZIP_PATTERN = Pattern.compile("^\\d{5}$");

    /**
     * Regular expression used to validate the phone number.
     *
     * <p>{@code phone} is a {@code BigInteger} in the entity, so only digits
     * are allowed.</p>
     */
    public static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{9,15}$");

    /** Minimum number of characters required for the password. */
    public static final int MIN_PASSWORD = 8;

    /**
     * Maximum number of characters allowed in the text fields.
     *
     * <p>The text columns of the entity are annotated with
     * {@code @Size(max = 255)}.</p>
     */
    public static final int MAX_TEXTO = 255;

    /**
     * Initializes the controller after its FXML has been loaded.
     *
     * <p>Clears the error label, disables the sign-up button and registers a
     * listener on every field so that the form is validated whenever any of
     * them changes.</p>
     *
     * @param url the location used to resolve relative paths for the root
     *            object, or {@code null} if it is not known
     * @param rb  the resources used to localize the root object, or
     *            {@code null} if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblError.setText("");
        btnSignUp.setDisable(true);

        // Re-validates the form every time any field changes.
        TextField[] campos = {txtNombre, txtApellido, txtDomicilio, txtCiudad,
            txtEmail, txtEstado, txtCodigoPostal, txtTelefono,
            pwdPassword, pwdConfirmPassword};
        for (TextField campo : campos) {
            campo.textProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        }
    }

    /**
     * Checks that every field of the form has been filled in.
     *
     * <p>Text fields are trimmed before the check; password fields are not,
     * since spaces are valid password characters.</p>
     *
     * @return {@code true} if no field is empty, {@code false} otherwise
     */
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

    /**
     * Checks that the typed email address matches {@link #EMAIL_PATTERN}.
     *
     * @return {@code true} if the (trimmed) email address is valid,
     *         {@code false} otherwise
     */
    public boolean emailValido() {
        return EMAIL_PATTERN.matcher(txtEmail.getText().trim()).matches();
    }

    /**
     * Checks that the password and its confirmation are identical.
     *
     * @return {@code true} if both password fields contain the same text,
     *         {@code false} otherwise
     */
    public boolean contrasenasCoinciden() {
        return pwdPassword.getText().equals(pwdConfirmPassword.getText());
    }

    /**
     * Returns the typed phone number with all whitespace removed.
     *
     * <p>The phone number may be typed with spaces (for example
     * {@code "600 123 456"}).</p>
     *
     * @return the phone number without whitespace characters
     */
    public String telefonoNormalizado() {
        return txtTelefono.getText().replaceAll("\\s+", "");
    }

    /**
     * Checks whether any text field exceeds the maximum allowed length.
     *
     * @return {@code true} if at least one field has more than
     *         {@link #MAX_TEXTO} characters, {@code false} otherwise
     */
    public boolean algunCampoDemasiadoLargo() {
        TextField[] campos = {txtNombre, txtApellido, txtDomicilio, txtCiudad,
            txtEmail, txtEstado, pwdPassword};
        for (TextField campo : campos) {
            if (campo.getText().trim().length() > MAX_TEXTO) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the first problem found in the form.
     *
     * <p>Only fields that already contain text are reported, so that no errors
     * are shown before the user has filled them in.</p>
     *
     * @return the error message to display, or an empty string if there is no
     *         problem
     */
    public String obtenerError() {
        String cp = txtCodigoPostal.getText().trim();
        String telefono = telefonoNormalizado();
        String password = pwdPassword.getText();
        String confirmacion = pwdConfirmPassword.getText();

        if (algunCampoDemasiadoLargo()) {
            return "Ningún campo puede tener más de 255 caracteres.";
        }
        if (!txtEmail.getText().trim().isEmpty() && !emailValido()) {
            return "Introduce un correo electrónico válido.";
        }
        if (!cp.isEmpty() && !ZIP_PATTERN.matcher(cp).matches()) {
            return "El código postal debe tener 5 dígitos.";
        }
        if (!telefono.isEmpty() && !PHONE_PATTERN.matcher(telefono).matches()) {
            return "El teléfono debe tener entre 9 y 15 dígitos.";
        }
        if (!password.isEmpty() && password.length() < MIN_PASSWORD) {
            return "La contraseña debe tener al menos 8 caracteres.";
        }
        if (!confirmacion.isEmpty() && !contrasenasCoinciden()) {
            return "Las contraseñas no coinciden.";
        }
        return "";
    }

    /**
     * Validates the whole form.
     *
     * <p>Shows the current error (if any) in the error label and enables the
     * sign-up button only when all fields are filled in and there are no
     * errors.</p>
     */
    public void validarFormulario() {
        String error = obtenerError();
        lblError.setText(error);
        btnSignUp.setDisable(!camposCompletos() || !error.isEmpty());
    }

    /**
     * Handles the action of the sign-up button.
     *
     * <p>Re-validates the form and, if it is correct, registers the user on
     * the server. On success it shows a confirmation dialog and closes the
     * window; on failure it shows the error in the error label.</p>
     */
    @FXML
    public void handleSignUp() {
        validarFormulario();
        if (btnSignUp.isDisabled()) {
            lblError.setText("Revisa los datos del formulario antes de continuar.");
            return;
        }

        String email = txtEmail.getText().trim();
        String error = registrarUsuario(email);
        if (error != null) {
            lblError.setText(error);
            return;
        }

        mostrarCuentaCreada(email);
        cerrarVentana();
    }

    /**
     * Creates the user on the server.
     *
     * @param email the email address of the new user
     * @return {@code null} if the user was created successfully; otherwise,
     *         the message that must be shown to the user
     */
    public String registrarUsuario(String email) {
        UserRESTClient client = new UserRESTClient();
        try {
            List<User> usuarios = listaSegura(
                    client.findAll_XML(new GenericType<List<User>>() {}));

            if (emailYaRegistrado(usuarios, email)) {
                return "Ya existe una cuenta con ese correo.";
            }

            User nuevo = construirUsuario(email, siguienteId(usuarios));
            client.create_XML(nuevo);

            // create_XML does not report whether the server rejected the
            // creation, so we check that the user really appears on the server.
            List<User> despues = listaSegura(
                    client.findAll_XML(new GenericType<List<User>>() {}));
            if (!existeUsuario(despues, nuevo.getId())) {
                logger.severe("El servidor no guardó el usuario con id " + nuevo.getId());
                return "No se pudo crear la cuenta. Inténtalo de nuevo más tarde.";
            }
            return null;

        } catch (Exception ex) {
            logger.severe("Error al registrar el usuario: " + ex.getMessage());
            return "No se puede conectar con el servidor. Inténtalo más tarde.";
        } finally {
            client.close();
        }
    }

    /**
     * Protects against a {@code null} list returned by the REST client.
     *
     * @param lista the list returned by the server, possibly {@code null}
     * @return the same list, or a new empty list if it was {@code null}
     */
    public List<User> listaSegura(List<User> lista) {
        return lista == null ? new ArrayList<User>() : lista;
    }

    /**
     * Checks whether an email address is already registered.
     *
     * <p>The comparison is case-insensitive.</p>
     *
     * @param usuarios the users that already exist on the server
     * @param email    the email address to look for
     * @return {@code true} if any user already has that email address,
     *         {@code false} otherwise
     */
    public boolean emailYaRegistrado(List<User> usuarios, String email) {
        return usuarios.stream()
                .anyMatch(u -> u.getEmail() != null && u.getEmail().equalsIgnoreCase(email));
    }

    /**
     * Checks whether a user with the given id exists in a list.
     *
     * @param usuarios the list of users to search
     * @param id       the id to look for
     * @return {@code true} if a user with that id is in the list,
     *         {@code false} otherwise
     */
    public boolean existeUsuario(List<User> usuarios, Long id) {
        return usuarios.stream().anyMatch(u -> id.equals(u.getId()));
    }

    /**
     * Calculates the id for the new user.
     *
     * <p>The {@code User} entity does not generate its id automatically (it has
     * no {@code @GeneratedValue}), so it is assigned here: the highest existing
     * id plus one.</p>
     *
     * @param usuarios the users that already exist on the server
     * @return the next available id ({@code 1} if there are no users)
     */
    public Long siguienteId(List<User> usuarios) {
        return usuarios.stream()
                .map(User::getId)
                .filter(Objects::nonNull)
                .max(Long::compare)
                .orElse(0L) + 1;
    }

    /**
     * Builds a {@link User} from the data typed in the form.
     *
     * @param email the (already trimmed) email address of the new user
     * @param id    the id to assign to the new user
     * @return the new {@link User}, ready to be sent to the server
     */
    public User construirUsuario(String email, Long id) {
        User user = new User(id);
        user.setFirstName(txtNombre.getText().trim());
        user.setLastName(txtApellido.getText().trim());
        user.setStreet(txtDomicilio.getText().trim());
        user.setCity(txtCiudad.getText().trim());
        user.setState(txtEstado.getText().trim());
        user.setZip(Integer.valueOf(txtCodigoPostal.getText().trim()));
        user.setPhone(new BigInteger(telefonoNormalizado()));
        user.setEmail(email);
        user.setPassword(pwdPassword.getText());
        return user;
    }

    /**
     * Shows an information dialog confirming that the account was created.
     *
     * @param email the email address of the account that was created
     */
    public void mostrarCuentaCreada(String email) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(btnSignUp.getScene().getWindow());
        alert.setTitle("Registro completado");
        alert.setHeaderText("Cuenta creada correctamente");
        alert.setContentText("Ya puedes iniciar sesión con " + email + ".");
        alert.showAndWait();
    }

    /**
     * Handles the action of the "sign in" link.
     *
     * <p>SignIn opens this window modally on top of its own, so going back to
     * the sign-in window is simply a matter of closing this one.</p>
     */
    @FXML
    public void handleGoToSignIn() {
        cerrarVentana();
    }

    /**
     * Closes the window that contains this controller's form.
     */
    private void cerrarVentana() {
        ((Stage) btnSignUp.getScene().getWindow()).close();
    }
}
