package VennDiagramMain;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class VennDiagramWindowController implements Initializable {

	@FXML
	public TextField textField, title1, title2;

	@FXML
	public StackPane stackPane;

	@FXML
	public Circle circle1;

	@FXML
	public Circle circle2;

	@FXML
	public Button entryButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void entryButton(ActionEvent event) {
		try {

			// Testing For Invalid Statements
			/*
			 * Situations that will throw an invalid statement 1.Empty Text 2.Text Length
			 * Over 100 3.Word Length Over 20 4.If a word is not All CAPS, NO CAPS, or ONLY
			 * THE FIRST LETTER CAPS
			 */

			String testSpaces = textField.getText();
			if (textField.getText().length() > 100
					|| (testSpaces.replaceAll("", " ").length() == textField.getText().length()
							&& textField.getText().length() > 20)
					|| textField.getText().equals("")) {
				throw new Exception();
			}

			int wordLength = 0;
			int capCount = 0;
			boolean isFirst = false;
			ArrayList<Integer> lengths = new ArrayList<Integer>();
			for (int i = 0; i < textField.getText().length(); i++) {
				if (textField.getText().charAt(i) != ' ') {
					wordLength++;
				}

				if (Character.isUpperCase(textField.getText().charAt(i)) && wordLength == 1) {
					capCount++;
					isFirst = true;
					continue;
				}

				if (Character.isUpperCase(textField.getText().charAt(i))) {
					capCount++;
				}

				if (textField.getText().charAt(i) == ' ') {
					lengths.add(wordLength);
					if (capCount != wordLength
							&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
						throw new Exception();
					}
					capCount = 0;
					wordLength = 0;
				}
				if (i == textField.getText().length() - 1) {
					lengths.add(wordLength);
					if (capCount != wordLength
							&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
						throw new Exception();
					}
					capCount = 0;
					wordLength = 0;
				}
			}
			for (int i : lengths) {
				if (i > 20) {
					throw new Exception();
				}
			}

			// Input Valid Text File Into Drag and Drop TextBox

			TextField entry = new TextField();
			entry.autosize();
			entry.setText(textField.getText());
			entry.setVisible(true);
			entry.setEditable(false);
			entry.resizeRelocate(circle1.getCenterX(), circle1.getCenterY(), 1, 1);
			entry.resize(50, 50);
			entry.setMinWidth(50);
			entry.setPrefWidth(50);
			entry.setMaxWidth(400);

			stackPane.getChildren().add(entry);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Please enter a valid entry");
			textField.setText("");
		}
	}

}