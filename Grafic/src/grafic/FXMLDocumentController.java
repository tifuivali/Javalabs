/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafic;

import Elements.Desen;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author tifui
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField text_formula;
    @FXML
    private Canvas canvas_desen;
    @FXML
    private TextField text_min_x;
    @FXML
    private TextField text_max_x;
    @FXML
    private TextField text_name_file;
    @FXML
    private ComboBox<Double> combo_dimensiune;
    @FXML
    private TextField text_y;
    @FXML
    private TextField text_x;
    @FXML
    private ColorPicker combo_color;
    private Desen desen;
    
  

    @FXML
    void desenare_action(ActionEvent action) {
        if (text_formula.getText().length() <= 0) {
            return;
        }
        desen.desenare(text_formula.getText());
    }

    @FXML
    void load_image(ActionEvent evt) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Image");
        File img = fileChooser.showOpenDialog(new Stage());
        try {
            desen.load_from_image(img);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eroare!");
            alert.setContentText("Eroare incarcare imagine!");
            alert.showAndWait();
        }
    }
    

    @FXML
    void set_text_x(ActionEvent act) {
        desen.setGcDash(Double.parseDouble(text_x.getText()), Double.parseDouble(text_y.getText()));
    }
    @FXML
    void set_text_r(ActionEvent act) {
        text_x.setText("0");
        text_y.setText("0");
        desen.setGcDash(Double.parseDouble(text_x.getText()), Double.parseDouble(text_y.getText()));
    }

    @FXML
    void redesenare(ActionEvent evt) {
        desen.stergereDesenCuAxe();
        desen = new Desen(canvas_desen, Double.parseDouble(text_min_x.getText()), Double.parseDouble(text_max_x.getText()), text_formula.getText());
    }

    @FXML
    void on_selected_dimensiune(ActionEvent evt) {
        double dim = combo_dimensiune.getSelectionModel().getSelectedItem();
        desen.setGcDimension(dim);
    }

    @FXML
    void on_selected_color(ActionEvent evt) {
        Color col = combo_color.getValue();
        desen.setGcColor(col);
    }

    @FXML
    void save_grafic(ActionEvent evt) {
        if (text_name_file.getText().length() <= 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atentie!");
            alert.setContentText("Trebuie sa specificati un nume de fisier!");
            alert.showAndWait();
            return;
        }
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Directory to Save");
        File defaultDirectory = new File("/home/tifui/Descărcări/IP");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(new Stage());
        selectedDirectory = new File(selectedDirectory, text_name_file.getText() + ".png");
        WritableImage wi = new WritableImage((int) canvas_desen.getWidth(), (int) canvas_desen.getHeight());
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(canvas_desen.snapshot(null, wi), null), "png", selectedDirectory);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eroare!");
            alert.setContentText("Eroare salvare grafic!");
            alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done!");
        alert.setContentText("Grafic salvat!");
        alert.showAndWait();

    }

    @FXML
    private void stergere_desen(ActionEvent actionEvent) {
        desen.stergereDesen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        desen = new Desen(canvas_desen, Double.parseDouble(text_min_x.getText()), Double.parseDouble(text_max_x.getText()), text_formula.getText());
        for (double i = 1; i < 15; i += 0.5) {
            combo_dimensiune.getItems().add(i);
        }
        combo_dimensiune.setValue(Double.valueOf(1));
        combo_color.setValue(Color.BLACK);

    }

}
