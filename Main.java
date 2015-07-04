package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws MidiUnavailableException {
        primaryStage.setTitle("MUnG Interface de test");
        Group root = new Group();
        Scene scene = new Scene(root, 720, 580, Color.rgb(50,50,50)); // 64 de base

        SeqCreator SC = new SeqCreator();
        ControlPanel CP = new ControlPanel(SC);


        // Bandeau suppérieur

        final Color shadowColor = Color.BLACK.deriveColor(0, 0, 0, 0.75);
        final DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, shadowColor, 50, 0, 0, 0);


        final ImageView head = new javafx.scene.image.ImageView(new Image(Tempo.class.getResourceAsStream("img/head.png")));
        head.setTranslateX(0);
        head.setTranslateY(0);
        head.setPreserveRatio(true);
        head.setEffect(dropShadow);

        final ImageView icn = new javafx.scene.image.ImageView(new Image(Saxo.class.getResourceAsStream("img/icn1.png")));
        icn.setFitHeight(30);
        icn.setPreserveRatio((true));
        icn.setX(685);
        icn.setY(6);

        final TranslateTransition translateDown = new TranslateTransition(new Duration(1000));
        translateDown.setNode(head);
        translateDown.setFromY(-40);
        translateDown.setToY(0);
        translateDown.setAutoReverse(false);

        root.getChildren().addAll(SC.getKB(),SC.getDR(),SC.getGT(),SC.getTP(),SC.getSX());
        root.getChildren().add(CP);
        root.getChildren().add(head);
        root.getChildren().add(icn);

        translateDown.play();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /*public static void main(String[] args){
        launch(args);
    }*/
}
