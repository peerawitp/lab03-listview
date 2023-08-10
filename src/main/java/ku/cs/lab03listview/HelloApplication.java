package ku.cs.lab03listview;

import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import ku.cs.services.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
        FXRouter.bind(this, stage, "TableView");
        configRoute();
        FXRouter.goTo("students-table");
    }

    public static void configRoute()
    {
        String viewPath = "ku/cs/views/";
        FXRouter.when("hello", viewPath + "hello-view.fxml");
        FXRouter.when("student-list", viewPath + "student-list.fxml");
        FXRouter.when("students-table", viewPath + "students-table.fxml");
        FXRouter.when("student-score", viewPath + "student-score.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}