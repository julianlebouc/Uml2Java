package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Association;
import model.Attribut;
import model.Classe;
import view.controls.Classe_;

public class FenetreAssociation extends Stage {

    private Pane zoneUML;
	private Uml2Java uml;
    private Classe classe;
    private Association association;

	private HBox hBoxNomAssociation = new HBox();
    private Label associationLabel = new Label("Nom de l'association :");
    private TextField classeTextArea = new TextField();

    private VBox vBoxAssociation = new VBox();
    private ComboBox<Classe> classe1 = new ComboBox();
    
    private VBox vBoxAssociation2 = new VBox();
    private ComboBox<Classe> classe2 = new ComboBox();

    private Classe[] comboClasse = new Classe[20];
    
    private ButtonBar buttonBar = new ButtonBar();
    private Button confirmer = new Button("Confirmer");
    private Button annuler = new Button("Annuler");

    public FenetreAssociation(Uml2Java uml) {
        this.classe = new Classe("temp");
        this.uml = uml;
        this.setTitle("Association");
        this.setResizable(false);

        this.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(init(), 380, 150);
        this.setScene(scene);
        initEvents();
    }


	// Initialise les controls
    private Parent init() {
        GridPane root = new GridPane();
        
        Font police = Font.loadFont(getClass().getResourceAsStream("Comfortaa-Regular.ttf"), 12);
        Font police2 = Font.loadFont(getClass().getResourceAsStream("Comfortaa-Regular.ttf"), 10);
        associationLabel.setFont(police);
        annuler.setFont(police);
        confirmer.setFont(police);
        
        annuler.getStyleClass().add("annuler");
        
        root.setPadding(new Insets(20));
        vBoxAssociation.setPadding(new Insets(5));
        vBoxAssociation2.setPadding(new Insets(5));

        vBoxAssociation.setMargin(classe1, new Insets(5));
        vBoxAssociation2.setMargin(classe2, new Insets(5));
        hBoxNomAssociation.setMargin(associationLabel, new Insets(5));

        classe1.setPrefWidth(170);
        classe2.setPrefWidth(170);
        
        int i = 0;
        
        for(i = 0; i < uml.getClasseList().getItems().size(); i++) {
        	
			comboClasse[i] = uml.getClasseList().getItems().get(i);
			
		}
        
        classe1.getItems().addAll(comboClasse);
        classe2.getItems().addAll(comboClasse);
        
        hBoxNomAssociation.getChildren().addAll(associationLabel, classeTextArea);

        vBoxAssociation.getChildren().addAll(classe1);
        vBoxAssociation2.getChildren().addAll(classe2);

        buttonBar.getButtons().addAll(confirmer, annuler);

        root.add(hBoxNomAssociation, 0, 0, 4, 1);
        root.add(vBoxAssociation, 0, 1);
        root.add(vBoxAssociation2, 1, 1);
        root.add(buttonBar, 1, 2);
        return root;
    }
    
    
    private void initEvents() {
     
        
        annuler.setOnAction(event -> {
            annulerClasse();
        });

        confirmer.setOnAction(event -> {
            Classe c;
        	Classe c2;
        	
        	c = classe1.getSelectionModel().getSelectedItem();
        	c2= classe2.getSelectionModel().getSelectedItem();
        	association = new Association(c,c2,"1","1","Libellé");

        	c.ajoutRelation(association);
        	c2.ajoutRelation(association);

        	association.setClasseDep(c);
        	association.setClasseDest(c2);

        	association.ajoutAssociation();
            ajouterClasse();
        });
    }

    private void ajouterClasse() {
        if (estValide()) {
            
//        	uml.items = FXCollections.observableArrayList(classe);
//            
//        	uml.getClasseList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        	
//        	uml.getClasseList().getItems().add(attribut);
        	
        	//zoneUML.getChildren().add();
        	
        	
        	
            close();
        }

    }
    
    public String trad(Classe classe) {
    	
    	return classe.traductionJava();
    	
    }

    public Classe getClasse() {
		return classe;
	}


	public void setClasse(Classe classe) {
		this.classe = classe;
	}


	private void annulerClasse() {
        close();
    }

    private boolean estValide() {
        if (classe1 == null || classe2 == null) {
            return false;
        }

        return true;
    }
}
