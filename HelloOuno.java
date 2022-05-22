package TPOuno;

import java.util.Arrays;
import plum.console.Clavier;

public class HelloOuno {

    public static void main(String[] args) {
        
        String[]tCarte= new String[76];
        String[]tJoueur= new String[7];
        String[]tMachine= new String[7];
        String[]tPioche= new String[62];
        String[]tCartePose= new String[0];
        String[] tNomCarte= new String[76];
        tNomCarte=Ouno.distributionNomCarte(tNomCarte);
        Ouno.distributionDesCartes(tCarte, tJoueur, tMachine);
        Ouno.constitutionDeLaPioche(tCarte, tPioche);
        System.out.println("Carte du joueur : " + Arrays.toString(tJoueur));
        System.out.println("Carte de la Machine " + Arrays.toString(tMachine));
        
        int indiceCartePosee;
        boolean carteValideM=false;
        
        tCartePose=Ouno.ajouteUneCarteAuJeuDunJoueur(tCartePose, tPioche[0]);
        tPioche=Ouno.enleverCartePiochée(tPioche);
       
        System.out.println("Carte posée : " + Arrays.toString(tCartePose));
        do {
            String action=Clavier.lireTexte("Action ( !:ouno ; p:pioche; i:indice carte posée ) ?");

            switch (action){
                case"i":
                    String cartePoseeJ=Clavier.lireTexte("Indice de la carte ? ");
                    indiceCartePosee=Ouno.rechercheIndiceCarte(tJoueur, cartePoseeJ);
                    boolean carteValideJ=Ouno.laCartePoséeEstElleValide(tCartePose, cartePoseeJ);
                    if(carteValideJ==false){
                        System.out.println("carte invalide");
                    } else {
                        tCartePose[0]=tJoueur[indiceCartePosee];
                        tJoueur=Ouno.enleveUneCarteAuJeuDunJoueur(tJoueur, tJoueur[indiceCartePosee]);
                        System.out.println("Carte posée :" + Arrays.toString(tCartePose));
                    }
                    for (int b=0;b<tMachine.length-1;b++){
                        carteValideM=Ouno.laCartePoséeEstElleValide(tCartePose, tMachine[b]);
                        if(carteValideM==true){
                            tCartePose[0]=tMachine[b];
                            tMachine=Ouno.enleveUneCarteAuJeuDunJoueur(tMachine, tMachine[b]);
                            break;
                        }
                        
                    }
                    if(carteValideM==false){
                        tMachine=Ouno.ajouteUneCarteAuJeuDunJoueur(tMachine, tPioche[0]);
                        tPioche=Ouno.enleverCartePiochée(tPioche);
                    }
                    System.out.println("Carte posée :" + Arrays.toString(tCartePose));
                    System.out.println("Carte du joueur : " + Arrays.toString(tJoueur));
                    System.out.println("Carte de la Machine " + Arrays.toString(tMachine));
                    break;
                case "p":
                    if(tPioche.length==0){
                        Ouno.constitutionDeLaPioche(tCarte, tPioche);
                    }
                    tJoueur=Ouno.ajouteUneCarteAuJeuDunJoueur(tJoueur, tPioche[0]);
                    tPioche=Ouno.enleverCartePiochée(tPioche);

                    for (int b=0;b<tMachine.length;b++){
                        carteValideM=Ouno.laCartePoséeEstElleValide(tCartePose, tMachine[b]);
                        if(carteValideM==true){
                            tCartePose[0]=tMachine[b];
                            tMachine=Ouno.enleveUneCarteAuJeuDunJoueur(tMachine, tMachine[b]);
                            break;
                        }
                    }
                    if(carteValideM==false){
                        tMachine=Ouno.ajouteUneCarteAuJeuDunJoueur(tMachine, tPioche[0]);
                        tPioche=Ouno.enleverCartePiochée(tPioche);
                    }
                    System.out.println("Carte posée :" + Arrays.toString(tCartePose));
                    System.out.println("Carte du joueur : " + Arrays.toString(tJoueur));
                    System.out.println("Carte de la Machine " + Arrays.toString(tMachine));
                    break;
                case "!":
                    if(tJoueur.length==1){
                        tCartePose[0]=tJoueur[0];
                        tJoueur=Ouno.enleveUneCarteAuJeuDunJoueur(tJoueur, tJoueur[0]);
                        System.out.println("Carte posée :" + Arrays.toString(tCartePose));
                        System.out.println("Le Joueur à gagné !");
                        System.out.println("Main de la Machine :" + Arrays.toString(tMachine));
                    }
        }
    }while(tJoueur.length!=0);
}
}
