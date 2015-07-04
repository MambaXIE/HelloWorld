package sample;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import javax.sound.midi.*;

public class SeqCreator extends Parent {

    Sequence sequence;
    Track trackTab[] = new Track[4];
    MidiEvent eventTabON_guitar[] = new MidiEvent[48];
    MidiEvent eventTabOFF_guitar[] = new MidiEvent[48];
    MidiEvent eventTabON_keyboard[][] = new MidiEvent[8][3];
    MidiEvent eventTabOFF_keyboard[][] = new MidiEvent[8][3];
    Sequencer sequencer = MidiSystem.getSequencer();

    // changer le nom de la classe

    Gamme scale;
    Keyboard KB;
    Guitar GT;
    Drum DR;
    Saxo SX;
    Tempo TP;


    public SeqCreator() throws MidiUnavailableException{

        scale = new Gamme(0,0);
        KB = new Keyboard();
        GT = new Guitar();
        DR = new Drum();
        SX = new Saxo();
        TP = new Tempo();

        try
        {
            sequence = new Sequence(Sequence.PPQ, 1);
        }
        catch (InvalidMidiDataException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        for(int i=0;i<4;i++) {
            trackTab[i] = sequence.createTrack();
        } 

        try {
            sequencer.setSequence(sequence);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        //bank de sons
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        synthesizer.getChannels()[1].programChange(55);
        synthesizer.getChannels()[2].programChange(13);
        synthesizer.getChannels()[3].programChange(9);
        synthesizer.getChannels()[4].programChange(9);

        sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());



        try {
            sequencer.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }


        addKeyboard();
        addGuitar(false);
        //addDrum();

        EventHandler<MouseEvent> posTempo = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                sequencer.setTempoInBPM((int)(TP.vignette.getTranslateX())+415);

            }
        };
        this.TP.vignette.setOnMouseDragged(posTempo);


    }

    public Keyboard getKB(){
        return KB;
    }
    public Drum getDR() {
        return DR;
    }
    public Guitar getGT() {
        return GT;
    }
    public Saxo getSX() {
        return SX;
    }
    public Tempo getTP() {
        return TP;
    }

    public void addKeyboard(){

        KB.compoTrack(scale);
        int tick=0;

        for(int i=0;i<8 ;i++){
            for(int j=0 ; j<3; j++){

                eventTabON_keyboard[i][j]=createNoteEvent(ShortMessage.NOTE_ON, 1,
                        KB.getTrack()[i].getKeys()[j].getKey(),
                        KB.getTrack()[i].getKeys()[j].getVelocity(),
                        tick);

                trackTab[1].add(eventTabON_keyboard[i][j]);

                eventTabOFF_keyboard[i][j]=createNoteEvent(ShortMessage.NOTE_OFF, 1,
                        KB.getTrack()[i].getKeys()[j].getKey(),
                        KB.getTrack()[i].getKeys()[j].getVelocity(),
                        tick+KB.getTrack()[i].getKeys()[j].getDuration());

                trackTab[1].add(eventTabOFF_keyboard[i][j]);
            }
            tick=tick+KB.getTrack()[i].getKeys()[0].getDuration();
        }
    }

    public void addGuitar(boolean rm){

        if(rm == false){
            GT.compoTrack(scale,KB.getTrack(),100);
        }
        else{
            for(int i=0;i<GT.getNbNote();i++){
                trackTab[2].remove(eventTabON_guitar[i]);
                trackTab[2].remove(eventTabOFF_guitar[i]);
            }
        }

        int tick=0;
        for(int i=0;i<GT.getNbNote();i++){

            eventTabON_guitar[i]=createNoteEvent(ShortMessage.NOTE_ON, 2,
                    GT.getTrack()[i].getKey(),
                    GT.getTrack()[i].getVelocity(),
                    tick);

            System.out.println(GT.getTrack()[i].getKey());
            trackTab[2].add(eventTabON_guitar[i]);

            eventTabOFF_guitar[i]=(createNoteEvent(ShortMessage.NOTE_OFF, 2,
                    GT.getTrack()[i].getKey(),
                    GT.getTrack()[i].getVelocity(),
                    tick+GT.getTrack()[i].getDuration()));

            trackTab[2].add(eventTabOFF_guitar[i]);

            tick=tick+GT.getTrack()[i].getDuration();
        }
    }

    public void addDrum(){
        DR.compoTrack();
        int tick=0;

        for(int i=0;i<48;i++){

            if(DR.getTrack()[i][0].getKey() != 0 ){

                trackTab[3].add(createNoteEvent(ShortMessage.NOTE_ON, 9,
                        DR.getTrack()[i][0].getKey(),
                        DR.getTrack()[i][0].getVelocity(),
                        tick));

                trackTab[3].add(createNoteEvent(ShortMessage.NOTE_OFF, 9,
                        DR.getTrack()[i][0].getKey(),
                        DR.getTrack()[i][0].getVelocity(),
                        tick+DR.getTrack()[i][0].getDuration()));
            }
            tick++;
        }
    }

    private static MidiEvent createNoteEvent(int nCommand,
                                             int nChannel,
                                             int nKey,
                                             int nVelocity,
                                             long lTick)
    {
        ShortMessage message = new ShortMessage();
        try
        {
            message.setMessage(nCommand,nChannel,nKey, nVelocity);
        }
        catch (InvalidMidiDataException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        MidiEvent	event = new MidiEvent(message,
                lTick);
        return event;
    }

    public void playSequence(){
        //System.out.print(TP.getBPM());
        sequencer.setTempoInBPM(TP.getBPM());
        sequencer.start();
        sequencer.addMetaEventListener(new MetaEventListener() {
            public void meta(MetaMessage event) {
                if (event.getType() == 47) {
                    sequencer.setMicrosecondPosition(0);
                    sequencer.setTempoInBPM(TP.getBPM());
                    sequencer.start();
                    addGuitar(true);
                }
            }
        });
    }

    public void stopSequence(){
        sequencer.stop();
        sequencer.setMicrosecondPosition(0);

    }

    public void resetTracks(){

        //suppression guitare
        for(int i=0;i<48;i++){
            trackTab[2].remove(eventTabON_guitar[i]);
            trackTab[2].remove(eventTabOFF_guitar[i]);
        }
        //suprression piano
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                trackTab[1].remove(eventTabON_keyboard[i][j]);
                trackTab[1].remove(eventTabOFF_keyboard[i][j]);
            }
        }
        addKeyboard();
        addGuitar(false);
        System.out.print("new compo here");
    }

    public void changeScale(int s,int n){
        scale.changeGamme(s,n);
    }



    private static void out(String strMessage)
    {
        System.out.println(strMessage);
    }

}
