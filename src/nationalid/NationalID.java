package nationalid;

import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NationalID extends Application {

    public static void main(String[] args) {
//        System.out.print("ID: ");
//        Scanner input = new Scanner(System.in);
//        String id = input.next();
//        IDInfo info = new IDInfo();
//        info.extractInfo(id);
//        System.out.print("DOB: ");
//        String date = info.getDate();
//        System.out.println(date);
//        System.out.print("Gov: ");
//        String gov = info.getGov();
//        System.out.println(gov);
//        System.out.print("Gender: ");
//        String gender = info.getGender();
//        System.out.println(gender);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        IDInfo info = new IDInfo();
        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        Text titleTxt = new Text("Enter Your National ID");
        Label errorTxt = new Label("");
        errorTxt.setTextFill(Color.web("#FF0000"));
        titleTxt.setFont(Font.font("System", FontWeight.BOLD, 20));
        TextField field = new TextField();
        Label lengthTxt = new Label(field.getText().length() + "/14");
        VBox fieldBox = new VBox();
        fieldBox.getChildren().addAll(field,lengthTxt);
        fieldBox.setAlignment(Pos.CENTER_RIGHT);
        field.setOnKeyPressed(e -> {
            if (field.getText().length() > 14) {
                errorTxt.setText("Invalid National ID");
                field.setStyle("-fx-text-fill: red;");
            } else {
                errorTxt.setText("");
                lengthTxt.setText(field.getText().length() + "/14");
                field.setStyle("-fx-text-fill: black");
            }
        });
        Button extractBtn = new Button("Extract");
        Button clearBtn = new Button("Clear");
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(fieldBox, extractBtn,clearBtn);
        hbox.setAlignment(Pos.TOP_CENTER);
        Text dobTxt = new Text("");
        Text govTxt = new Text("");
        Text genderTxt = new Text("");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(titleTxt, hbox, errorTxt, dobTxt, govTxt, genderTxt);
        vbox.setAlignment(Pos.CENTER);
        pane.setCenter(vbox);
        extractBtn.setOnAction(e -> {
            String id = field.getText();
            info.extractInfo(id);
            if (info.getErrorMsg() == null) {
                String date = info.getDate();
                String gov = info.getGov();
                String gender = info.getGender();
                dobTxt.setText("DOB: " + date);
                govTxt.setText("Gov: " + gov);
                genderTxt.setText("Gender: " + gender);
                errorTxt.setText("");
            } else {
                String error = info.getErrorMsg();
                errorTxt.setText(error);
            }
        });
        clearBtn.setOnAction(e -> {
            field.setText("");
            field.setStyle("-fx-text-fill: black");
            lengthTxt.setText(field.getText().length() + "/14");
            dobTxt.setText("");
            govTxt.setText("");
            genderTxt.setText("");
            errorTxt.setText("");
            info.clearAll();
        });
        Scene scene = new Scene(pane, 500, 300);
        stage.setScene(scene);
        stage.setTitle("National ID");
        stage.show();
    }

}
