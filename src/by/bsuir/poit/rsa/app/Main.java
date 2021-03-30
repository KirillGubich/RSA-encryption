package by.bsuir.poit.rsa.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String MAIN_WINDOW_TITLE = "RSA scrambler";
    private static final String MAIN_WINDOW_FXML_PATH = "../view/scrambler.fxml";
    private static final int MAIN_WINDOW_WIDTH = 600;
    private static final int MAIN_WINDOW_HEIGHT = 410;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_WINDOW_FXML_PATH));
        primaryStage.setTitle(MAIN_WINDOW_TITLE);
        primaryStage.setScene(new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
