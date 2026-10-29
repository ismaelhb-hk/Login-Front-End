/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package login.front.end;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.front.end.view.SignInViewController;

/**
 *
 * @author ismael
 */
public class Application extends javafx.application.Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader =
                            new FXMLLoader(getClass().getResource("view/SignInView.fxml"));
        Parent root =loader.load();
        SignInViewController controller=(SignInViewController)loader.getController();
        controller.setStage(stage);
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
