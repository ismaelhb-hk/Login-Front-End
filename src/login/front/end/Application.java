
package login.front.end;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.front.end.view.ChangePasswordController;
import login.front.end.view.MainWindowController;
import login.front.end.view.SignInViewController;

/**
 *
 * @author Abdel
 */
public class Application extends javafx.application.Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("view/SignInView.fxml")
        );
         Parent root = loader.load();
         
         // Get the controller associated with FXML
         SignInViewController sincon = loader.getController();
         

        // Pass the Stage to the controller
        sincon.setStage(stage);

        // Initialize the listeners
        sincon.init();
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
