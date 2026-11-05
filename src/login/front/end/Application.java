
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
         
         // Obtener el controlador asociado al FXML
        ChangePasswordController controller = loader.getController();

        // Pasar el Stage al controlador
        controller.setStage(stage);

        // Inicializar los listeners
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
