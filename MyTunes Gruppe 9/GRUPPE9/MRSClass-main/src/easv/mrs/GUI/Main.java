package easv.mrs.GUI;

import easv.mrs.GUI.Controller.MyTunesViewController;
import easv.mrs.GUI.Model.MRSModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
*@author Magnus, Johnni & Jesper
*/

public class Main extends Application {
//Launch the Application
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("View/MyTunesView.fxml"));
        Parent root = loader.load();

        MyTunesViewController controller = loader.getController();
        controller.setModel(new MRSModel());
        controller.setup();

        primaryStage.setTitle("MyTunes2022");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
