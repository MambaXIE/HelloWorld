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

import javax.swing.plaf.multi.MultiTableHeaderUI;

/**
 * Created by davidperrey on 20/12/2014.
 */
public class Guitar extends Parent {

    ImageView vignette;
    Note listNote[] = new Note[48];
    int nbNote;
    int oct;


    private String textX;
    private String textY;


    public Guitar(){

        final Color shadowColor = Color.BLACK.deriveColor(0, 0, 0, 0.75);
        final DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, shadowColor, 50, 0, 0, 0);

        vignette = new javafx.scene.image.ImageView(new Image(Guitar.class.getResourceAsStream("img/guitarOnClick3.png")));
        vignette.setFitHeight(110);
        vignette.setPreserveRatio(true);
        vignette.setEffect(dropShadow);

        this.setTranslateX(295);
        this.setTranslateY(180);

        final Text tPosX = new Text(-285,45,"");
        tPosX.setFont(Font.font("Verdana", 14));
        tPosX.setFill(Color.rgb(160, 160, 160));
        tPosX.setVisible(false);

        final Text tPosY = new Text(65,-120,"");
        tPosY.setFont(Font.font("Verdana",14));
        tPosY.setFill(Color.rgb(160, 160, 160));
        tPosY.setVisible(false);


        final Line lineX =  new Line(-295,55,720,55);
        lineX.setStroke(Color.rgb(83, 209, 237));
        lineX.setStrokeLineCap(StrokeLineCap.ROUND);
        lineX.getStrokeDashArray().addAll(5d, 5d, 5d, 5d, 5d);
        lineX.setStrokeWidth(1);
        lineX.setVisible(false);

        final Line lineY =  new Line(55,-180,55,580);
        lineY.setStroke(Color.rgb(83, 209, 237));
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

                textX = "X : "+(int)(vignette.getTranslateX()+295);
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

        // sortie du glissé déposé
        EventHandler<MouseEvent> mouseHandler4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                oct=(int)vignette.getTranslateY()/100;
                reCompTrack(oct);
            }
        };



        this.setOnMouseDragged(mouseHandler);
        this.setOnMouseExited(mouseHandler2);
        this.setOnMouseEntered(mouseHandler3);
        // sortie du glissé déposé
        this.setOnMouseReleased(mouseHandler4);


    }
    public void compoTrack(Gamme scale,Chord List[],int vol){

        int pos=0;
        nbNote=0;
        int chordCompleted=0;
        int keyDuration=0;

        for(int i=0 ; i < 48 ; i ++){
            listNote[i]=null;
        }

        for(int i=0 ; i<8 ; i++){
            for(int j=0 ; j<List[i].getKeys()[0].getDuration(); j++){



                if(j!=6) {
                    listNote[pos] = new Note(scale.getNoteDistance(List[i].getKeys()[0].getKey(), (int) (Math.random() * j)),keyDuration , vol);
                    pos++;
                    nbNote++;
                    System.out.print(listNote[pos - 1].getKey() + " ");
                }

                switch(chordCompleted){
                    case 4:
                        keyDuration = (int)(Math.random()*2)+1;
                        break;
                    case 5:
                        keyDuration = 1;
                        break;
                    case 6:
                        j=6;
                        chordCompleted=0;
                        break;
                    default:
                        keyDuration = (int)(Math.random()*3)+1;
                        break;
                }

                chordCompleted=chordCompleted+keyDuration;
            }
            System.out.print("\n");
        }
    }

    public void reCompTrack(int pOct){
        if(oct !=0) {
            for (int i = 0; i < nbNote; i++) {
                listNote[i].setKey(listNote[i].getKey() + (pOct * 12));
            }
        }
    }

    public int getNbNote(){
        return nbNote;
    }

    public Note[] getTrack(){
        return listNote;
    }
}
