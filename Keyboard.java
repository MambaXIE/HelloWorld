package sample;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java. util. logging. Level;
import java. util. logging. Logger;
import javax. sound. midi. MidiSystem;
import javax. sound. midi. MidiChannel;
import javax. sound. midi. MidiUnavailableException;
import javax. sound. midi. Synthesizer;

/**
 * Created by davidperrey on 20/12/2014.
 */

public class Keyboard extends Parent {

    private ImageView vignette;
    // ci dessous : ne contient qu'une suite de 8 notes à envoyer au sequenceur. TEST
    private Chord listChord[];
    private String textX;
    private String textY;

    public Keyboard(){

        // changement à faire pour la version finale.
        listChord = new Chord[16];
        textX="";
        textY="";
        final Color shadowColor = Color.BLACK.deriveColor(0, 0, 0, 0.75);
        final DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, shadowColor, 50, 0, 0, 0);

        vignette = new javafx.scene.image.ImageView(new Image(Keyboard.class.getResourceAsStream("img/pianoOnClick2.png")));
        vignette.setFitHeight(110);
        vignette.setPreserveRatio(true);
        vignette.setEffect(dropShadow);

        this.setTranslateX(175);
        this.setTranslateY(180);

        final Text tPosX = new Text(-165,45,"");
        tPosX.setFont(Font.font("Verdana",14));
        tPosX.setFill(Color.rgb(160, 160, 160));
        tPosX.setVisible(false);

        final Text tPosY = new Text(65,-120,"");
        tPosY.setFont(Font.font("Verdana",14));
        tPosY.setFill(Color.rgb(160, 160, 160));
        tPosY.setVisible(false);


        final Line lineX =  new Line(-175,55,720,55);
        lineX.setStroke(Color.rgb(148, 201, 141));
        lineX.setStrokeLineCap(StrokeLineCap.ROUND);
        lineX.getStrokeDashArray().addAll(5d, 5d, 5d, 5d, 5d);
        lineX.setStrokeWidth(1);
        lineX.setVisible(false);

        final Line lineY =  new Line(55,-180,55,580);
        lineY.setStroke(Color.rgb(148, 201, 41));
        lineY.setStrokeLineCap(StrokeLineCap.ROUND);
        lineY.getStrokeDashArray().addAll(5d, 5d, 5d, 5d, 5d);
        lineY.setStrokeWidth(1);
        lineY.setVisible(false);


        this.getChildren().add(tPosX);
        this.getChildren().add(tPosY);
        this.getChildren().add(lineX);
        this.getChildren().add(lineY);
        this.getChildren().add(vignette);


        EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                vignette.setTranslateX(mouseEvent.getX()-50);
                vignette.setTranslateY(mouseEvent.getY() - 50);
                vignette.setRotate(vignette.getRotate() + 5);
                lineX.setTranslateY(mouseEvent.getY() - 50);
                lineY.setTranslateX(mouseEvent.getX() - 50);
                tPosX.setTranslateY(mouseEvent.getY() - 50);
                tPosY.setTranslateX(mouseEvent.getX() - 50);

                textX = "X : "+(int)(vignette.getTranslateX()+175);
                tPosX.setText(textX);
                textY = "Y : "+(int)(vignette.getTranslateY()+180);
                tPosY.setText(textY);

            }
        };

        EventHandler<MouseEvent> mouseHandler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                lineX.setVisible(false);
                lineY.setVisible(false);
                tPosX.setVisible(false);
                tPosY.setVisible(false);
            }
        };

        EventHandler<MouseEvent> mouseHandler3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                lineX.setVisible(true);
                lineY.setVisible(true);
                tPosX.setVisible(true);
                tPosY.setVisible(true);
            }
        };

        this.setOnMouseDragged(mouseHandler);
        this.setOnMouseExited(mouseHandler2);
        this.setOnMouseEntered(mouseHandler3);
    }

    public void compoTrack(Gamme scale){
        int restD = 16; // 16 pour le nombre de doubles croches possible dans une mesure
        // Ce qui correspond a 1 Tick dans le sequenceur.

        // TEST ---------------- //

        for(int i=0 ; i<16 ; i++){
            listChord[i]= new Chord(scale,(int)(Math.random()*6),'n',6,100);
            System.out.println(listChord[i].getKeys()[0].getKey());
        }
        // FIN TEST ------------ //
    }

    public Chord[] getTrack(){
        return listChord;
    }

}
