package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.services.FXRouter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ImageView sampleImageView;

    @FXML
    private ComboBox<String> selectImageDropdown;

    Timer timer = new Timer();
    private ArrayList<String> imageStates = new ArrayList<>();

    @FXML
    public void initialize() {
        int i = 0;

        welcomeText.setText("Hello JavaFX");

        Image image = new Image(getClass().getResource("/images/cat.png").toString());  // แบบที่ 1
        // Image image = new Image(getClass().getResourceAsStream("/images/cat.png"));  // แบบที่ 2

        imageStates.add("cat");
        imageStates.add("dog");
        imageStates.add("fox");

        sampleImageView.setImage(image);

        selectImageDropdown.getItems().addAll("cat", "dog", "fox");
        selectImageDropdown.setValue("cat");
        selectImageDropdown.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldSelect, String newSelect) {
                if (newSelect != null) {
                    switch(newSelect) {
                        case "cat":
                            sampleImageView.setImage(new Image(getClass().getResource("/images/cat.png").toString()));
                            break;
                        case "dog":
                            sampleImageView.setImage(new Image(getClass().getResource("/images/dog.png").toString()));
                            break;
                        case "fox":
                            sampleImageView.setImage(new Image(getClass().getResource("/images/fox.png").toString()));
                            break;
                        default:
                            break;
                    }
                }
            }
        });


    }

    @FXML
    protected void onHelloButtonClick() {
        try {
            FXRouter.goTo("student-list");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}