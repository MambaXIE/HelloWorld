package sample;

/**
 * Created by davidperrey on 21/02/15.
 */
public class Chord {

    private int key;
    private int duration;
    private int velocity;
    private char style;
    private Note chordKey[] = new Note[4];

    public Chord(Gamme scale,int k,char s,int d,int v){
        this.key=k;
        this.style=s;
        this.duration=d;
        this.velocity=v;
        createChord(scale);
    }

    public int getKey() {
        return key;
    }

    public Note[] getKeys(){
        return chordKey;
    }


    private void createChord(Gamme scale){
        chordKey[0]=new Note(scale.getchordNotes('n',key,3)[0],duration,velocity);
        chordKey[1]=new Note(scale.getchordNotes('n',key,3)[1],duration,velocity);
        chordKey[2]=new Note(scale.getchordNotes('n',key,3)[2],duration,velocity);
        chordKey[3]=new Note(scale.getchordNotes('n',key,3)[3],duration,velocity);
    }

}
