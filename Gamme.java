package sample;

/**
 * Created by davidperrey on 05/01/15.
 */

public class Gamme {

    /*  Matrice 2D conteannt toute les notes de la gamme naturel
        Mineur de LA. Le nombre d'octave a été limité à 7 pour
        que le rendu sonore en MIDI reste ressemblant au son de
        l'instrument joué.
     */

    private int scale[][] = new int [6][7];



    private int scaleTab[][] =  {{2,2,1,2,2,2,1},
                                {2,2,1,2,2,2,1},
                                {2,1,2,2,1,2,2}};
    /*
        Constructeur de la classe GAMME
        int selectedScale : type de gamme
                0 = mineur naturelle
                1 = majeur
                2 = mineur relative
        int n : fondamentale
                0 = DO
                1 = DO#
                2 = RE
                3 = RE#
                4 = MI
                5 = FA
                ...
     */

    public Gamme(int selectedScale,int n){
        int note = 24+n;
        int k = 0;

        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                scale[i][j]=note;
                System.out.print(note+" ");
                note=scale[i][j]+scaleTab[selectedScale][k];
                k++;
                if(k==7){ k=0; }


            }
            System.out.println();
        }


    }

    /*
        Retourne une note aléatoire contenue dans la gamme
     */

    public int getRandNote(int oct){
        return scale[oct][(int)(Math.random()*7)];
    }

    /*
        retourne dans un tableau, les notes composant l'accord
        dont le numéro , le style et la hauteur sont passé en parrametre.
        "oct" doit être compris entre 1 et 7 ( nombre d'octaves utilisés ).
     */


    public int[] getchordNotes(char style,int key, int oct){

        int chord[] = new int [4];

            for (int i=0 ;  i<4 ; i++) {
                if(key>6){
                    key=key-7;
                    oct++;
                }
                chord[i]=scale[oct][key];
                key+=2;
            }
        return chord;
    }

    /*
        retourne la position en 'x' de la note donné en parrametre
     */
    public int getNoteY(int note){
        for (int i=0 ; i<6 ; i++){
            for(int j=0 ; j<7 ; j++){
                if (scale[i][j]==note){
                    return i;
                }
            }
        }
        return 0; // impossible (condition de retour obligatoire)
    }

    /*
        retourne la position en 'y' de la note donné en parrametre
    */
    public int getNoteX(int note){
        for (int i=0 ; i<6 ; i++){
            for(int j=0 ; j<7 ; j++){
                if (scale[i][j]==note){
                    return j;
                }
            }
        }
        return 0; // impossible (condition de retour obligatoire)
    }

    /*
        Retourne la note situé à l'écart (en nombre de note)
        passé en parrametre (peu être négatif)de la note
        passée en parrametre
     */
    public int getNoteDistance(int n,int e){
        if(this.getNoteX(n)+e>6){
            return scale[getNoteY(n)+1][getNoteX(n)+e-7];
        }
        else if(this.getNoteX(n)+e<0){
            return scale[getNoteY(n)-1][getNoteX(n)+e+7];
        }
        else{
            return scale[getNoteY(n)][getNoteX(n)+e];
        }
    }

    public void changeGamme(int selectedScale,int n){
        int note = 24+n;
        int k = 0;

        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                scale[i][j]=note;
                System.out.print(note+" ");
                note=scale[i][j]+scaleTab[selectedScale][k];
                k++;
                if(k==7){ k=0; }


            }
            System.out.println();
        }
    }

}