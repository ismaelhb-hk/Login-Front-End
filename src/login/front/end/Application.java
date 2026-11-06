
package login.front.end;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.front.end.view.ChangePasswordController;

/**
 *
 * @author Abdel
 */
public class Application extends javafx.application.Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("view/ChangePassword.fxml")
        );
         Parent root = loader.load();
         
         // Get the controller associated with FXML
        ChangePasswordController controller = loader.getController();

        // Pass the Stage to the controller
        controller.setStage(stage);

        // Initialize the listeners
        controller.init();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
