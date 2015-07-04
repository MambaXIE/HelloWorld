package sample;

/**
 * Created by davidperrey on 21/02/15.
 */
public class Note {


    private int key;
    private int duration;
    private int velocity;

    public Note(int k,int d,int v){
        this.key=k;
        this.duration=d;
        this.velocity=v;
    }

    public int getKey(){
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }
}
