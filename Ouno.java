package TPOuno;

import java.util.Arrays;

/**
 * Bibliothèque régissant les règles du jeu Ouno
 * 
 * @author thierry.bogusz
 */
 
public class Ouno{
 
    /**
    * Efface toutes les données de chaque case du tableau
    *
    * @author Thierry
    *
    * @param tCarte tableau des cartes
    * 
    *
    */
    public static void initTableauCarte(String[] tCarte){
            for(int a=0; a<tCarte.length;a++){
                tCarte[a]="";
            }
    }
    /**
    * 
    * Attribue le nom des cartes dans un tableau
    * 
    *
    */
    public static String[] distributionNomCarte(String[] tNomCarte){
        int i=1;
        tNomCarte[0]="0B";
        for(int a=1;a<=9;a++){                                                                           
            for(int b=0;b<=1;b++){
                tNomCarte[i]=a+"B";
                i++;
            }
        }
        tNomCarte[19]="0R";
        i=20;
        for(int a=1;a<=9;a++){                                                                           
            for(int b=0;b<=1;b++){
                tNomCarte[i]=a+"R";
                i++;
            }
        }
        tNomCarte[38]="0J";
        i=39;
        for(int a=1;a<=9;a++){                                                                           
            for(int b=0;b<=1;b++){
                tNomCarte[i]=a+"J";
                i++;
            }
        }
        tNomCarte[57]="0V";
        i=58;
        for(int a=1;a<=9;a++){                                                                           
            for(int b=0;b<=1;b++){
                tNomCarte[i]=a+"V";
                i++;
            }
        }
        return tNomCarte;
    }
    /**
    * Distribue les cartes au joueur J et à la machine M
    *
    * @author Thierry
    *
    * @param tCarte tableau des cartes
    * @param tJoueur tableau des cartes du joueur J
    * @param tMachine tableau des cartes de la machine M
    * 
    *
    */
    
    
    public static void distributionDesCartes(String[] tCarte, 
                                             String[] tJoueur, 
                                             String[] tMachine){
       
        String[] tNomCarte= new String[76];
        tNomCarte=distributionNomCarte(tNomCarte);
        
        for (int a=0; a<tCarte.length;a++){
            tCarte[a]="P";
        }
        int b=0;
        while (b<=6){
            int nb=(int)(Math.random()*75)+1;
            if(tCarte[nb].equals("P")){
                tCarte[nb]="J";
                tJoueur[b]=tNomCarte[nb];
                b++;
            }
        }
        int c=0;
        while (c<=6){
            int nb=(int)(Math.random()*75)+1;
            if(tCarte[nb].equals("P")){
                tCarte[nb]="M";
                tMachine[c]=tNomCarte[nb];
                c++;
            }
        }

    }
 
    /**
    * Constitue la pioche en choisissant les cartes
    * restantes au hasard
    *
    * @author Thierry
    *
    * @param tCarte tableau des cartes
    * @param tCarte tableau de la pioche
    * 
    *
    */
    public static void constitutionDeLaPioche(String[] tCarte, 
                                             String[] tPioche){
        String[] tNomCarte= new String[76];
        tNomCarte=distributionNomCarte(tNomCarte);
        int b=0;
        for(int a=0;a<tPioche.length;a++){
            int nb=(int)(Math.random()*75)+1;
            if(tCarte[nb].equals("P")){
                tPioche[b]=tNomCarte[nb];
                b++;
            }
        }
 
    }
 
    /**
    * Vérifie que la carte posée est valide
    * ( même numéro et/ou même couleur )
    *
    * @author Thierry
    *
    * @param tCartePose tableau des cartes posées
    * @param cartePosee valeur de la carte posée
    * @return 
    * 
    *
    */
    public static boolean laCartePoséeEstElleValide(String[] tCartePose, 
                                                    String cartePosee){
        boolean carteValide=false;
            if(cartePosee.charAt(1)==tCartePose[tCartePose.length-1].charAt(1) | 
               cartePosee.charAt(0)==tCartePose[tCartePose.length-1].charAt(0)){
                carteValide=true;
            }
       return carteValide; 
    }
    public static int rechercheIndiceCarte(String[] tJoueur, String CartePosee){
        int indiceCartePosee=-1;
        for(int b=0;b<tJoueur.length;b++){
                    if(tJoueur[b].equals(CartePosee)){
                        indiceCartePosee=b;
                        System.out.println(indiceCartePosee);
                        break;
                    }
                }
        return indiceCartePosee;
    }
    
    /**
    * Enlève la carte piochée dans la pioche
    * La carte piochée est toujours celle se trouvant sur le haut du tas
    * à l'indice 0
    *
    * @author Thierry
    *
    * @param tPioche tableau de la pioche
    *
    * @return Le nouveau tableau de la pioche
    * dont on a enlevé l'élément à l'indice 0
    * 
    * 
    *
    */
    public static String[] enleverCartePiochée(String[] tPioche ){
        for (int a=0; a<tPioche.length-1;a++){
            tPioche[a]=tPioche[a+1];
        }
        return tPioche;
    }
    
    /**
    * Ajoute une carte dans le jeu d'un joueur
    *
    * @author Thierry
    *
    * @param tJeuJoueur tableau d'un joueur (tJoueur ou tMachine)
    *
    * @return Le nouveau tableau avec la nouvelle carte
    * la carte est toujours ajoutée à la fin du tableau
    * 
    * 
    *
    */
    public static String[] ajouteUneCarteAuJeuDunJoueur(String[] tJeuJoueur, 
                                                        String carte ){
        tJeuJoueur=Arrays.copyOf(tJeuJoueur, tJeuJoueur.length+1);
        tJeuJoueur[tJeuJoueur.length-1]=carte;
        return tJeuJoueur;
    }

    public static String[] enleveUneCarteAuJeuDunJoueur(String[] tJeuJoueur, 
                                                        String carte ){
        for (int a=0; a<tJeuJoueur.length-1;a++){
            if(tJeuJoueur[a].equals(carte)){
                for (int b=a;b<tJeuJoueur.length-1;b++){
                    tJeuJoueur[b]=tJeuJoueur[b+1];
                }
                tJeuJoueur=Arrays.copyOf(tJeuJoueur, tJeuJoueur.length-1);
                break;    
            }
        }
        if(tJeuJoueur[tJeuJoueur.length-1].equals(carte)){
            tJeuJoueur=Arrays.copyOf(tJeuJoueur, tJeuJoueur.length-1);
        }
        return tJeuJoueur;
    }
}