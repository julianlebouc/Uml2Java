package views;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Attribut;
import models.Type;
import models.Visibilite;

public class FenetreModifierAttribut extends Stage {

    private Attribut attribut;

    private Label erreurLabel = new Label();

    private Label nomAttribut = new Label("Nom : ");
    private Label typeAttribut = new Label("Type : ");
    private Label visibiliteAttribut = new Label("Visibilite : ");

    private TextField textFieldNom = new TextField();
    private ComboBox<Type> comboBoxType = new ComboBox<>();
    private ComboBox<Visibilite> comboBoxVisibilite = new ComboBox<>();

    private HBox hBoxNom = new HBox();
    private HBox hBoxTyoe = new HBox();
    private HBox hBoxVisibilite = new HBox();
    private VBox vBox = new VBox();

    private String[] visibilites = {"public", "private", "protected"};
    private String[] types = {"float", "boolean", "String", "int", "double"};

    private Button confirmer = new Button("Confirmer");
    private Button annuler = new Button("Annuler");
    private ButtonBar buttonBar = new ButtonBar();

    public FenetreModifierAttribut(Attribut attribut) {
        this.attribut = attribut;
        this.setTitle("Modifier Attribut");
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setScene(new Scene(initControls()));
        afficherAttribut();
    }

    private Parent initControls() {
        VBox root = new VBox();
        root.setSpacing(20.0);
        root.setPadding(new Insets(10.0));

        buttonBar.setPadding(new Insets(0.0));
        buttonBar.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);

        comboBoxVisibilite.setItems(FXCollections.observableArrayList(Visibilite.values()));
        comboBoxType.setItems(FXCollections.observableArrayList(Type.values()));

        hBoxNom.getChildren().addAll(nomAttribut, textFieldNom);
        hBoxNom.setSpacing(10.0);
        hBoxTyoe.getChildren().addAll(typeAttribut, comboBoxType);
        hBoxTyoe.setSpacing(10.0);
        hBoxVisibilite.getChildren().addAll(visibiliteAttribut, comboBoxVisibilite);
        hBoxVisibilite.setSpacing(10.0);
        vBox.getChildren().addAll(hBoxNom, hBoxTyoe, hBoxVisibilite);
        vBox.setSpacing(10.0);

        erreurLabel.setId("erreur");

        buttonBar.getButtons().addAll(confirmer, annuler);

        root.getChildren().addAll(vBox, erreurLabel, buttonBar);

        confirmer.setOnAction(event -> {
            creerAttribut();
        });

        annuler.setOnAction(event -> {
            close();
        });

        return root;
    }

    public Attribut getAttribut() {
        return attribut;
    }

    private void afficherAttribut() {
        textFieldNom.setText(attribut.getNom());
        comboBoxType.getSelectionModel().select(attribut.getType());
        comboBoxVisibilite.getSelectionModel().select(attribut.getVisibilite());
    }

    private void creerAttribut() {
        if (estValide()) {
            attribut = new Attribut();
            attribut.setNom(textFieldNom.getText());
            attribut.setVisibilite(comboBoxVisibilite.getValue());
            attribut.setType(comboBoxType.getValue());
            close();
        }
    }

    private boolean estValide() {
        if (textFieldNom.getText() == null || textFieldNom.getText().isEmpty() || comboBoxVisibilite.getSelectionModel().getSelectedItem() == null
                || comboBoxType.getSelectionModel().getSelectedItem() == null) {
            erreurLabel.setText("Tous les champs doivent etre remplis");
            return false;
        }
        erreurLabel.setText("");
        return true;
    }
}
