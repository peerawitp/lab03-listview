package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Student;
import ku.cs.models.StudentList;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.StudentListFileDatasource;
import ku.cs.services.StudentListHardCodeDatasource;

import java.io.IOException;

public class StudentListController {
    @FXML private ListView<Student> studentListView;
    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    @FXML private Label scoreLabel;

    @FXML private Label errorLabel;
    @FXML private TextField giveScoreTextField;

    @FXML private AnchorPane hiddenPane;

    private StudentList studentList;
    private Student selectedStudent;

    private Datasource<StudentList> datasource;

    @FXML
    private ComboBox<String> selectDatasourceDropdown;

    @FXML
    public void initialize() {
        errorLabel.setText("");
        clearStudentInfo();

        // Datasource<StudentList> datasource = new StudentListHardCodeDatasource();
        // StudentListHardCodeDatasource datasource = new StudentListHardCodeDatasource();
        // Datasource<StudentList> datasource = new StudentListFileDatasource("data", "student-list.csv");
        datasource = new StudentListFileDatasource("data", "student-list.csv");

        selectDatasourceDropdown.getItems().addAll("Hardcode", "File");
        selectDatasourceDropdown.setValue("File");

        selectDatasourceDropdown.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldSelect, String newSelect) {
                if (newSelect != null) {
                    switch(newSelect) {
                        case "Hardcode":
                            datasource = new StudentListHardCodeDatasource();
                            break;
                        case "File":
                            datasource = new StudentListFileDatasource("data", "student-list.csv");
                            break;
                        default:
                            break;
                    }
                    studentList = datasource.readData();
                    showList(studentList);
                }
            }
        });

        studentList = datasource.readData();
        showList(studentList);
        studentListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                if (newValue == null) {
                    clearStudentInfo();
                    selectedStudent = null;
                } else {
                    showStudentInfo(newValue);
                    selectedStudent = newValue;
                    hiddenPane.setVisible(true);
                }
            }
        });
    }

    private void showList(StudentList studentList) {
        studentListView.getItems().clear();
        studentListView.getItems().addAll(studentList.getStudents());
    }

    private void showStudentInfo(Student student) {
        idLabel.setText(student.getId());
        nameLabel.setText(student.getName());
        scoreLabel.setText(String.format("%.2f", student.getScore()));
    }

    private void clearStudentInfo() {
        idLabel.setText("");
        nameLabel.setText("");
        scoreLabel.setText("");
    }

    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onGiveScoreButtonClick() {
        if (selectedStudent != null) {
            String scoreText = giveScoreTextField.getText();
            String errorMessage = "";
            try {
                double score = Double.parseDouble(scoreText);
                studentList.giveScoreToId(selectedStudent.getId(), score);

                // เขียนข้อมูลลงในไฟล์เมื่อมีการเปลี่ยนแปลงของข้อมูล
                datasource.writeData(studentList);

                showStudentInfo(selectedStudent);
            } catch (NumberFormatException e) {
                errorMessage = "Please insert number value";
                errorLabel.setText(errorMessage);
            } finally {
                if (errorMessage.equals("")) {
                    giveScoreTextField.setText("");
                }
            }
        } else {
            giveScoreTextField.setText("");
            errorLabel.setText("");
        }
    }

    @FXML
    public void onCloseStudentClick() {
        clearStudentInfo();
        hiddenPane.setVisible(false);
        studentListView.getSelectionModel().clearSelection();
    }
}