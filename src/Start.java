import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("MotorPH Application");
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
