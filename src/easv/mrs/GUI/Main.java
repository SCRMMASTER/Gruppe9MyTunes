/*
Created by Group 9.
Magnus, Jesper and Johnni.
 */
package easv.mrs.GUI;

import easv.mrs.GUI.Controller.MyTunesViewController;
import easv.mrs.GUI.Model.MyTunesModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class
Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("View/MyTunesView.fxml"));
        Parent root = loader.load();

        MyTunesViewController controller = loader.getController();
        controller.setModel(new MyTunesModel());
        controller.setup();

        primaryStage.setTitle("MyTunes2022");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaxHeight(1000);
        primaryStage.setMaxWidth(1000);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
