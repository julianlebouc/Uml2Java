package Views;

import Models.*;
import Views.Controls.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MainView extends Stage {

    private MenuBar menuBar = new MenuBar();

    private Menu ficherMenu = new Menu("Fichier");
    private MenuItem_ nouveau = new MenuItem_("Nouveau", new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
    private MenuItem_ ouvrir = new MenuItem_("Ouvrir", new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
    private MenuItem_ enregistrer = new MenuItem_("Enregistrer", new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
    private MenuItem_ quitter = new MenuItem_("Quitter", new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));

    private Menu aideMenu = new Menu("Aide");
    private MenuItem_ aide = new MenuItem_("Aide");
    private MenuItem_ aPropos = new MenuItem_("A Propos");

    private TreeView treeView = new TreeView();

    private TabPane tabPane = new TabPane();
    private Tab umlTab = new Tab("UML");
    private Tab javaTab = new Tab("Java");

    private ToolBar umlToolBar = new ToolBar();

    private BorderPane rootUml = new BorderPane();
    private Pane umlPane = new Pane();

    private TextArea javaCode = new TextArea("Java Code ...");

    private ContextMenu contextMenu = new ContextMenu();

    private Button nouvClasse = new Button("Classe");

    public MainView(String title) {
        this.setTitle(title);

        Scene scene = new Scene(init(), 800, 700);
        initEvents();
        this.setScene(scene);
    }

    private Parent init() {
        BorderPane root = new BorderPane();

        menuBar.getMenus().addAll(ficherMenu, aideMenu);
        ficherMenu.getItems().addAll(nouveau, ouvrir, enregistrer, quitter);
        aideMenu.getItems().addAll(aide, aPropos);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().addAll(umlTab, javaTab);

        umlTab.setContent(rootUml);
        umlTab.setContextMenu(contextMenu);
        rootUml.setTop(umlToolBar);
        rootUml.setCenter(umlPane);
        umlToolBar.getItems().addAll(nouvClasse);

        javaTab.setContent(javaCode);

        root.setTop(menuBar);
        root.setLeft(treeView);
        root.setCenter(tabPane);
        return root;
    }

    private void initEvents() {
        nouvClasse.setOnMouseClicked(e -> {
            Classe classe = new Classe("test");
            ClassesView classesView = new ClassesView(classe);
            classesView.showAndWait();


            Classe_ classe_ = new Classe_(classe);

            classe_.setId("Classes");

            umlPane.getChildren().addAll(classe_);

            classe_.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    ClassesView edit = new ClassesView(classe);
                    edit.showAndWait();
                }
            });


        });
    }
}
