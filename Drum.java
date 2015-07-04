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

/**
 * Created by davidperrey on 20/12/2014.
 */
public class Drum extends Parent {

    ImageView vignette;
    // matrice pour pouvoir supperposer plusieurs "note" à la fois
    Note mesure1[][] = new Note[48][4];

    private String textX;
    private String textY;

    public Drum(){


        final Color shadowColor = Color.BLACK.deriveColor(0, 0, 0, 0.75);
        final DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, shadowColor, 50, 0, 0, 0);

        vignette = new javafx.scene.image.ImageView(new Image(Drum.class.getResourceAsStream("img/drumOnClick3.png")));
        vignette.setFitHeight(110);
        vignette.setPreserveRatio(true);
        vignette.setEffect(dropShadow);


        this.setTranslateX(55);
        this.setTranslateY(180);

        final Text tPosX = new Text(-45,45,"");
        tPosX.setFont(Font.font("Verdana", 14));
        tPosX.setFill(Color.rgb(160,160,160));
        tPosX.setVisible(false);

        final Text tPosY = new Text(65,-120,"");
        tPosY.setFont(Font.font("Verdana",14));
        tPosY.setFill(Color.rgb(160,160,160));
        tPosY.setVisible(false);

        final Line lineX =  new Line(-55,55,720,55);
        lineX.setStroke(Color.rgb(253, 151, 51));
        lineX.setStrokeLineCap(StrokeLineCap.ROUND);
        lineX.getStrokeDashArray().addAll(5d, 5d, 5d, 5d, 5d);
        lineX.setStrokeWidth(1);
        lineX.setVisible(false);

        final Line lineY =  new Line(55,-180,55,580);
        lineY.setStroke(Color.rgb(253, 151, 51));
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
                lineX.setTranslateY(mouseEvent.getY()-50);
                lineY.setTranslateX(mouseEvent.getX()-50);
                tPosX.setTranslateY(mouseEvent.getY()-50);
                tPosY.setTranslateX(mouseEvent.getX()-50);

                textX = "X : "+(int)(vignette.getTranslateX()+55);
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

    /*
        Notes number used for Drums

        35 Accoustic bass drum (B0)
        36 Bass Drum (c1)
        37 Side Stick (c#1)
        38 Acoustic snare (D1)
        39 Hand Clap (D#1)
        40 Electronic Snare (E1)
        41 Low Floor Tom (F1)
        42 closed Hit hat (F#1)
        43 High floor tom (G1)
        44 pedal hit hat (G#1)
        45 low tom (A1)
        46 open hit hat (A#1)
        47 low mid tom (B1)
        48 high mid tom (C2)
        49 crash cymbal 1 (C#2)
        50 high tom (D2)
        51 ride cymbal 1 (D#2)
        52 chinese cymbal (E2)
        53 ride bell (F2)
        54 tambourine (F#2)
        55 splash cymbal (G2)
        56 cowbell (G#2)
        57 crash cymbal 2 (A2)
        58 vibraslap (A#2)
        59 ride cymbal 2 (B2)
        60 high bongo (C3)
        61 low bongo (C#3)
        62 mute high conga (D3)
        63 open high conga (D#3)
        64 low conga (E3)
        65 high timbale (F3)
        66 low timbale (F#3)
        67 high agogo (G3)
        68 low agogo (G#3)
        69 cabasa (A3)
        70 maracas (A#3)
        71 short whisle (B3)
        72 long whislte (C4)
        73 short guiro (C#4)
        74 long guiro (D4)
        75 claves (D#4)
        76 high wood block (E4)
        77 low wood block (F4)
        78 mute cuica (F#4)
        79 open cuica (G4)
        80 mute triangle (G#4)
        81 open triangle (A4)
     */

    public void compoTrack(){

        int placeBass = (int)(Math.random()*8)+4;
        if(placeBass%2 != 0){
            placeBass ++ ;
        }
        int dontPlaceBass = placeBass ;

        int pos=0;

        mesure1[pos][0] = new Note(35,1,100);


        for(int i=0 ; i<47 ; i++){

            pos++;
            if(dontPlaceBass == 0){
                dontPlaceBass = placeBass;
                mesure1[pos][0] = new Note(36,2,100);
            }
            else if( dontPlaceBass*2 == placeBass){
                mesure1[pos][0] = new Note(38,2,100);
            }
            else{
                mesure1[pos][0] = new Note(0,1,100);
            }

            dontPlaceBass--;
            System.out.println("drum"+mesure1[pos][0].getKey());
        }
    }

    public Note[][] getTrack(){
        return mesure1;
    }
}
