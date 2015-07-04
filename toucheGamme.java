package sample;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.swing.*;

/**
 * Created by davidperrey on 13/03/15.
 */



public class toucheGamme extends Parent {

    static int selectedNote;

    Rectangle rect;
    Text note_text;

    int note;
    
    toucheGamme(int dist,int i,String s){

        // rectangle pour séléctionner la note
        note=i;
        rect=new Rectangle();
        rect.setHeight(35);
        rect.setWidth(35);
        rect.setFill(Color.rgb(140, 140, 140));
        rect.setX(65 + (i * 35) + i * 15);
        rect.setY(150);

        note_text = new Text(s);
        note_text.setFont(new Font(20));
        note_text.setFill(Color.BLACK);
        note_text.setX(68 + (i * 35) + i * 15 + dist);
        note_text.setY(175);

        // rectangles pour séléctionner la gamme

        this.getChildren().addAll(rect,note_text);

        // animations


        final EventHandler<MouseEvent> cursorEntered = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rect.setFill(Color.rgb(175,129,254));
                note_text.setFill(Color.WHITE);
            }
        };
        final EventHandler<MouseEvent> cursorExited = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(selectedNote != note) {
                    rect.setFill(Color.rgb(140, 140, 140));
                    note_text.setFill(Color.BLACK);
                }

            }
        };
        final EventHandler<MouseEvent> clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedNote=note;
            }
        };

        this.setOnMouseClicked(clicked);
        this.setOnMouseEntered(cursorEntered);
        this.setOnMouseExited(cursorExited);

    }
    public void isSelected(){
        rect.setFill(Color.rgb(175,129,254));
    }
    
}
