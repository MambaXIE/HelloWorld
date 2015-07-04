package sample;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Created by davidperrey on 20/12/2014.
 */
public class ControlPanel extends Parent {

    /* Private var declaration */

    boolean top = false;
    boolean clicPlay = false;
    int volume = 100;
    int scaleChoice=0;

    public ControlPanel(final SeqCreator SC){

        /* ----- Création des différents éléments visuel du panneau -----*/

        // Ombres
        final Color shadowColor = Color.BLACK.deriveColor(0, 0, 0, 0.75);
        final DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, shadowColor, 50, 0, 0, 0);


        // Blur
        final GaussianBlur gaussianBlur = new GaussianBlur(5);

        // fleche


        final ImageView btnRoll = new javafx.scene.image.ImageView(new Image(Saxo.class.getResourceAsStream("img/roll.png")));
        btnRoll.setFitHeight(30);
        btnRoll.setPreserveRatio(true);
        btnRoll.setX(330);
        btnRoll.setY(5);

        // Panneau
        Rectangle bottomRect = new Rectangle();
        bottomRect.setWidth(720);
        bottomRect.setHeight(580);
        bottomRect.setFill(Color.rgb(51,51,51,0.65));
        bottomRect.setX(0);
        bottomRect.setY(0);
        bottomRect.setEffect(dropShadow);

        // Icone volume
        ImageView icnVol = new javafx.scene.image.ImageView(new Image(Saxo.class.getResourceAsStream("img/icnVol.png")));
        icnVol.setFitHeight(30);
        icnVol.setPreserveRatio(true);
        icnVol.setX(10);
        icnVol.setY(5);

        // btn play polygone
        final Polygon btnPlay = new Polygon();
        btnPlay.getPoints().addAll(new Double[]{
                0.0, 0.0,
                15.0, 10.0,
                0.0, 20.0});
        btnPlay.setFill(Color.rgb(140, 140, 140));
        btnPlay.setTranslateX(200);
        btnPlay.setTranslateY(10);

        // btn play circle
        final Circle btnPlayCircle =  new Circle();
        btnPlayCircle.setFill(Color.rgb(140,140,140,0));
        btnPlayCircle.setRadius(15);
        btnPlayCircle.setTranslateX(205);
        btnPlayCircle.setTranslateY(20);
        btnPlayCircle.setStroke(Color.rgb(175,129,254));
        btnPlayCircle.setStrokeWidth(1);

        // btn rest

        ImageView icnReset = new javafx.scene.image.ImageView(new Image(Saxo.class.getResourceAsStream("img/icnReset.png")));
        icnReset.setFitHeight(30);
        icnReset.setPreserveRatio(true);
        icnReset.setX(450);
        icnReset.setY(5);

        // SliderLine
        Rectangle slider = new Rectangle();
        slider.setWidth(100);
        slider.setHeight(2);
        slider.setFill(Color.rgb(140,140,140));
        slider.setX(50);
        slider.setY(20);

        // SliderLineColor
        final Rectangle sliderColor = new Rectangle();
        sliderColor.setWidth(100);
        sliderColor.setHeight(2);
        sliderColor.setFill(Color.rgb(175,129,254));
        sliderColor.setX(50);
        sliderColor.setY(20);

        // SliderCursor
        final Circle cursor = new Circle(8,8,8);
        cursor.setFill(Color.rgb(140,140,140));
        cursor.setCenterX(150);
        cursor.setCenterY(21);

        // Select Gamme

        String tabNote[] = {"C","#C","D","#D","E","F","#F","G","#G","A","#A","B" };
        final toucheGamme tg[] = new toucheGamme[12];
        for(int i=0;i<12;i++) {
            if (i == 1 || i == 3 || i == 6 || i == 8 || i == 10) {
                tg[i]=new toucheGamme(0,i,tabNote[i]);
            }else{
                tg[i]=new toucheGamme(7,i,tabNote[i]);
            }
        }

        final Rectangle rectMineurNaturel = new Rectangle();
        rectMineurNaturel.setHeight(40);
        rectMineurNaturel.setWidth(150);
        rectMineurNaturel.setTranslateX(275);
        rectMineurNaturel.setTranslateY(275);
        rectMineurNaturel.setFill(Color.rgb(140,140,140));

        final Text textMiNa = new Text("Mineur Naturel");
        textMiNa.setFont(new Font(20));
        textMiNa.setFill(Color.BLACK);
        textMiNa.setX(282);
        textMiNa.setY(302);

        final Rectangle rectMineurRelatif = new Rectangle();
        rectMineurRelatif.setHeight(40);
        rectMineurRelatif.setWidth(150);
        rectMineurRelatif.setTranslateX(275);
        rectMineurRelatif.setTranslateY(325);
        rectMineurRelatif.setFill(Color.rgb(140,140,140));

        final Text textMiRe = new Text("Mineur Relatif");
        textMiRe.setFont(new Font(20));
        textMiRe.setFill(Color.BLACK);
        textMiRe.setX(285);
        textMiRe.setY(352);

        final Rectangle rectMajeur = new Rectangle();
        rectMajeur.setHeight(40);
        rectMajeur.setWidth(150);
        rectMajeur.setTranslateX(275);
        rectMajeur.setTranslateY(375);
        rectMajeur.setFill(Color.rgb(140,140,140));

        final Text textMa = new Text("Majeur");
        textMa.setFont(new Font(20));
        textMa.setFill(Color.BLACK);
        textMa.setX(320);
        textMa.setY(402);




        /* ---- Ajout des éléments au groupe ----*/

        this.getChildren().add(bottomRect);
        this.getChildren().add(btnRoll);
        this.getChildren().add(icnVol);
        this.getChildren().add(btnPlayCircle);
        this.getChildren().add(btnPlay);
        this.getChildren().add(slider);
        this.getChildren().add(icnReset);
        this.getChildren().add(sliderColor);
        this.getChildren().add(cursor);
        this.getChildren().add(rectMineurNaturel);
        this.getChildren().add(rectMineurRelatif);
        this.getChildren().add(rectMajeur);
        this.getChildren().add(textMiNa);
        this.getChildren().add(textMiRe);
        this.getChildren().add(textMa);


        for (toucheGamme touche: tg){
            this.getChildren().add(touche);
        }

        this.setTranslateX(0);
        this.setTranslateY(540);

        /* ---- Création des animations ----*/


        final RotateTransition rotateUp = new RotateTransition(new Duration(1000));
        rotateUp.setNode(btnRoll);
        rotateUp.setFromAngle(0);
        rotateUp.setToAngle(180);

        final RotateTransition rotateDown = new RotateTransition(new Duration(1000));
        rotateDown.setNode(btnRoll);
        rotateDown.setFromAngle(180);
        rotateDown.setToAngle(0);


        final TranslateTransition translateUp = new TranslateTransition(new Duration(1000));
        translateUp.setNode(this);
        translateUp.setFromY(540.0);
        translateUp.setToY(80.0);
        translateUp.setAutoReverse(false);

        final TranslateTransition translateDown = new TranslateTransition(new Duration(1000));
        translateDown.setNode(this);
        translateDown.setFromY(80);
        translateDown.setToY(540.0);
        translateDown.setAutoReverse(false);



        final EventHandler<MouseEvent> mouseHandlerBtnClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(top==false) {
                    translateUp.play();
                    rotateUp.play();
                    top=true;
                }
                else{
                    translateDown.play();
                    rotateDown.play();
                    top=false;
                }
            }
        };

        final EventHandler<MouseEvent> mineurRelatifClicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleChoice=1;
                rectMineurRelatif.setFill(Color.rgb(175,129,254));
                rectMineurNaturel.setFill(Color.rgb(140,140,140));
                rectMajeur.setFill(Color.rgb(140,140,140));
                textMa.setFill(Color.BLACK);
                textMiRe.setFill(Color.WHITE);
                textMiNa.setFill(Color.BLACK);
                SC.scale.changeGamme(tg[0].note,scaleChoice);

            }
        };

        final EventHandler<MouseEvent> mineurNaturelClicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleChoice=0;
                rectMineurNaturel.setFill(Color.rgb(175,129,254));
                rectMineurRelatif.setFill(Color.rgb(140,140,140));
                rectMajeur.setFill(Color.rgb(140,140,140));
                textMa.setFill(Color.BLACK);
                textMiNa.setFill(Color.WHITE);
                textMiRe.setFill(Color.BLACK);
                SC.scale.changeGamme(tg[0].note,scaleChoice);
            }
        };

        final EventHandler<MouseEvent> majeurClicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleChoice=2;
                rectMineurNaturel.setFill(Color.rgb(140,140,140));
                rectMineurRelatif.setFill(Color.rgb(140,140,140));
                rectMajeur.setFill(Color.rgb(175,129,254));
                textMa.setFill(Color.WHITE);
                textMiNa.setFill(Color.BLACK);
                textMiRe.setFill(Color.BLACK);
                SC.scale.changeGamme(tg[0].note,scaleChoice);
            }
        };


        // la troisieme gamme ici


        final EventHandler<MouseEvent> cursorVolumeEntered = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cursor.setFill(Color.rgb(175,129,254));
            }
        };

        final EventHandler<MouseEvent> cursorVolumeExited = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cursor.setFill(Color.rgb(140,140,140));
            }
        };

        final EventHandler<MouseEvent> cursorVolumeDrag = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getX()>=50 && mouseEvent.getX()<=150) {
                    cursor.setCenterX(mouseEvent.getX() - 4);
                    sliderColor.setWidth(cursor.getCenterX()-50);
                }
            }
        };
        final EventHandler<MouseEvent> playClicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(clicPlay==false) {
                    btnPlay.setFill(Color.rgb(175, 129, 254));
                    clicPlay = true;
                    SC.playSequence();
                }
                else{
                    btnPlay.setFill(Color.rgb(140, 140, 140));
                    clicPlay = false;
                    SC.stopSequence();
                }
            }
        };

        final EventHandler<MouseEvent> mouseReset = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SC.changeScale(scaleChoice,tg[0].selectedNote);
                SC.resetTracks();
            }
        };

        // cursorVolume Events
        cursor.setOnMouseEntered(cursorVolumeEntered);
        cursor.setOnMouseExited(cursorVolumeExited);
        cursor.setOnMouseDragged(cursorVolumeDrag);
        // btnRoll Events
        btnRoll.setOnMouseClicked(mouseHandlerBtnClick);
        // btnPlay Events
        btnPlay.setOnMouseClicked(playClicked);
        // selection gamme
        textMiNa.setOnMouseClicked(mineurNaturelClicked);
        textMiRe.setOnMouseClicked(mineurRelatifClicked);
        textMa.setOnMouseClicked(majeurClicked);
        icnReset.setOnMouseClicked(mouseReset);
    }





}
