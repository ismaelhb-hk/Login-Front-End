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
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import login.front.end.client.UserRESTClient;
import login.front.end.model.User;

/**
 * Controller class for the Sign In view (SignInView.fxml).
 * Handles real-time input validation, user authentication against the RESTful
 * back-end service, and navigation to the MainWindow and SignUp views.
 *
 * @author ismael
 */
public class SignInViewController implements Initializable {

    private static final Logger logger = Logger.getLogger("login.front.end.view.SignInViewController");

    // Email input field
    @FXML
    private TextField txtEmail;

    // Password input field
    @FXML
    private PasswordField pwdPassword;

    // Button to authenticate and access MainWindow
    @FXML
    private Button btnSignIn;

    // Hyperlink to navigate to the Sign Up modal window
    @FXML
    private Hyperlink linkSignUp;

    // Label used to display validation and server error messages
    @FXML
    private Label lblError;

    // Reference to the primary application stage
    private Stage stage;

    /**
     * Sets the stage associated with this view.
     *
     * @param stage The primary {@link Stage} for the Sign In window.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initializes window event handlers and attaches text change listeners
     * to the email and password fields for real-time validation.
     */
    public void init() {
        // Add listener for window display event
        stage.setOnShown(this::handleWindowOpen);

        // Attach real-time validation listeners to input fields
        this.txtEmail.textProperty().addListener(this::handleTextChange);
        this.pwdPassword.textProperty().addListener(this::handleTextChange);
    }

    /**
     * Validates the email and password fields whenever their text changes.
     * Ensures neither field is empty and verifies that the email matches a valid format,
     * displaying feedback in {@code lblError} if any rule fails.
     *
     * @param observable The {@link ObservableValue} that triggered the change.
     * @param oldValue   The previous text value of the field.
     * @param newValue   The new text value of the field.
     */
    @FXML
    public void handleTextChange(ObservableValue observable, String oldValue, String newValue) {
        // Clear any previous error messages from the error label
        this.lblError.setText("");
        String regexp = "^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$";

        // If both fields are empty, do not display any error message
        if (this.txtEmail.getText().isEmpty() && this.pwdPassword.getText().isEmpty()) {
            return;
        }

        // Validate that neither field is empty and the email has a valid format.
        // If validation fails, notify the user via lblError.
        if (this.txtEmail.getText().isEmpty()) {
            this.lblError.setText("Email is empty");
            return;
        }

        if (this.pwdPassword.getText().isEmpty()) {
            this.lblError.setText("Password is empty");
            return;
        }

        if (!this.txtEmail.getText().matches(regexp)) {
            this.lblError.setText("Invalid email");
        }
    }

    /**
     * Handles the Sign In button action event.
     * Verifies client-side validation state, queries the RESTful back-end for matching
     * user credentials, opens {@code MainWindow.fxml} upon success, or displays an
     * appropriate error message if authentication or connection fails.
     *
     * @param e The {@link ActionEvent} triggered by clicking the Sign In button.
     */
    @FXML
    public void handleSignIn(ActionEvent e) {
        UserRESTClient client = new UserRESTClient();

        // If lblError has an active error message or any field is empty, abort submission
        if (!this.lblError.getText().isEmpty() || this.txtEmail.getText().isEmpty() || this.pwdPassword.getText().isEmpty()) {
            return;
        }

        try {
            // If all validations pass, request the user list from the back-end via HTTP GET
            List<User> users;
            users = client.findAll_XML(new GenericType<List<User>>() {});

            // Use a stream to filter users matching both the entered email and password
            if (users.stream()
                    .filter(user -> user.getEmail().equals(this.txtEmail.getText()))
                    .filter(user -> user.getPassword().equals(this.pwdPassword.getText()))
                    .count() == 0) {
                this.lblError.setText("This account does not exist");
                return;
            }

            // Retrieve the authenticated User object to pass it to MainWindowController
            User found = users.stream()
                    .filter(user -> user.getEmail().equals(this.txtEmail.getText()))
                    .filter(user -> user.getPassword().equals(this.pwdPassword.getText()))
                    .findFirst()
                    .get();

            // Load MainWindow view and initialize its controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent root = loader.load();
            Stage nuevaVentana = new Stage();
            nuevaVentana.setScene(new Scene(root));

            MainWindowController controller = loader.getController();
            controller.setUser(found);
            controller.setStage(nuevaVentana);

            nuevaVentana.show();
            this.stage.close(); // Close the Login window

        } catch (Exception ex) {
            // Display server connection error to the user and log details
            this.lblError.setText("Error devuelto por el servidor  \n Inténtalo mas tarde \n o contacta con el soporte técnico");
            logger.severe("Error connecting to the server: " + ex.getMessage());

        } finally {
            // Ensure the REST client connection is properly closed
            client.close();
        }
    }

    /**
     * Handles the Sign Up hyperlink action event by loading and displaying
     * the registration view ({@code SignUpView.fxml}) as a modal window.
     *
     * @param e The {@link ActionEvent} triggered by clicking the Sign Up hyperlink.
     */
    @FXML
    public void handleGoToSignUp(ActionEvent e) {
        // Open the Sign Up window modally
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("SignUpView.fxml"));
            // Create a scene and load the view into it
            Scene scene = new Scene(root);
            // Configure and display the scene in a new modal stage
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException ex) {
            System.getLogger(SignInViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    /**
     * Handles the window shown event by resetting all input fields to empty
     * and setting initial focus on the email text field.
     *
     * @param e The {@link WindowEvent} fired when the window is displayed.
     */
    @FXML
    public void handleWindowOpen(WindowEvent e) {
        // Clear all input fields
        this.txtEmail.setText("");
        this.pwdPassword.setText("");

        // Set initial focus on the email text field
        this.txtEmail.requestFocus();
    }

    /**
     * Initializes the controller class after its root element has been completely processed.
     *
     * @param url The location used to resolve relative paths for the root object, or {@code null}.
     * @param rb  The resources used to localize the root object, or {@code null}.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}