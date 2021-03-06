
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!
//TODO: OTTIMIZZARE I THREAD!!!!!!!!

/*
 * GameBoard.java
 * E' la classe che costruisce il tavolo da gioco
 *
 * Created on 27-apr-2010, 11.05.27
 */

package connectFour.GUI;


import connectFour.ControllerInterface;
import connectFour.GameState;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author Ross
 */

/**
 * Classe interna che rappresenta un casella di gioco
 * @author Ross
 */
class Square extends JLabel {
    public Square() {
        super();
        setIcon(new ImageIcon(getClass().getResource("/img/blank.JPG")));
    }
}

/*
 * La classe move crea i pulsanti per effettuare le mosse,
 * ogni pulsante memorizza in una costante il suo indice di colonna
 * e tiene traccia della prossima mossa ossia del prossimo spazio disponibile
 * nella propria colonna. Quando nextMove è uguale ad 8 la colonna è piena
 */
class Move extends JLabel{

    //index corrisponde alla colonna del campo da gioco
    private final int index;
    private final ImageIcon insertButtonPassive=new ImageIcon(getClass().getResource("/img/insertButtonPassive.png"));
    private final ImageIcon insertButtonActive=new ImageIcon(getClass().getResource("/img/insertButtonActive.png"));
    //nextMove corrisponde alla riga, il contatore è al contrario
    //va da 7 a 0, 7 è la prima riga 0 l'ultima
    private int nextMove=7;
         
    public Move(int i){
        super();
        setIcon(insertButtonPassive);
        index=i;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return the nextMove
     */
    public int getNextMove() {
        return nextMove;
    }

    /**
     * @param nextMove the nextMove to set
     */
    public void setNextMove(int nextMove) {
        this.nextMove = nextMove;
    }

    /**
     * @return the insertButtonPassive
     */
    public ImageIcon getInsertButtonPassive() {
        return insertButtonPassive;
    }

    /**
     * @return the insertButtonActive
     */
    public ImageIcon getInsertButtonActive() {
        return insertButtonActive;
    }

    public void setEnable(boolean flag){
        if(nextMove>=0){
            this.setEnabled(flag);
        }
    }

}



public class GameBoard extends javax.swing.JPanel implements MouseListener, ActionListener {

    private ControllerInterface controller;

    /**
     * Flag utilizzata per indicare se utilizzare o no il pulsante next,
     * quando la flag è false il pulsante resterà disattivato, quando è true
     * si attiverà
     */
    private boolean usesNextButton=false;
   
    private final ImageIcon red=new ImageIcon(getClass().getResource("/img/red.JPG"));
    private final ImageIcon yellow=new ImageIcon(getClass().getResource("/img/yellow.JPG"));

    private String yellowType="";
    private String redType="";

    /**
     *contiene la matrice del campo da gioco,8 righe (verticale) 
     * e 7 colonne (orizzontale) 
     */
    private Square[][] board= new Square[8][7];
    
    private Move[] moves= new Move[7];

    /**
     * Pulsante next, di default è disabilitato per le partite umano contro cpu
     * è disabilitato sempre per le partite umano contro umano, è abilitato
     * sempre per le partite cpu contro cpu
     */
    private JButton next= new JButton("next");

    private JLabel turnLabel=new JLabel("Tocca al Giocatore Giallo");

    /*memorizza il turno di gioco
     * turn=yellow tocca al giocatore giallo
     * turn=red tocca al giocatore rosso
     */
    private ImageIcon turn=yellow;

    //Finestra per informare che la partita è finita
    private EndDialog endDialog;


    /** Creates new form GameBoard */
    public GameBoard(ControllerInterface c) {
        super();

        GridBagConstraints layoutCostraints = initLayout();

        initMoves(layoutCostraints);

        initCpuMove(layoutCostraints);

        initBoard(layoutCostraints);

        initTurnLabel(layoutCostraints);

        initOtherComponents(c);

        setEnable(false);
    }



    /**
     * Inizializza il layout
     * @return
     */
    private GridBagConstraints initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints layoutCostraints = new GridBagConstraints();
        layoutCostraints.fill = GridBagConstraints.BOTH;
        return layoutCostraints;
    }


    /**
     * Inizializza il pulsante per far muovere la Cpu
     * @param layoutCostraints
     */
    private void initCpuMove(GridBagConstraints layoutCostraints) {
        next.addActionListener(this);
        layoutCostraints.gridy = 9;
        layoutCostraints.gridx = 0;
        layoutCostraints.gridwidth = 7;
        this.add(next, layoutCostraints);
    }

    /**
     * Inizializza i pulsanti per il giocatore umano
     * @param layoutCostraints
     */
    private void initMoves(GridBagConstraints layoutCostraints) {
        /*la prima riga del layout deve contenere i pulsanti
         * per effettuare le mosse, se la partita è AI vs AI
         * allora bisogna visualizzare un solo pulsante per far
         * andare avanti la partita. MIGLIORARE QUESTA VISUALIZZAZIONE
         */
        for (int i = 0; i < 7; i++) {
            moves[i] = new Move(i);
            //Il pannello si registra ad ogni pulsante per
            //animare il campo da gioco ad ogni turno
            moves[i].addMouseListener(this);
            //aggiunta di ogni pulsante al pannello
            layoutCostraints.gridy = 8;
            layoutCostraints.gridx = i;
            this.add(moves[i], layoutCostraints);
        }
    }

    /**
     * Inizializza il tavolo da gioco
     * @param layoutCostraints
     */
    private void initBoard(GridBagConstraints layoutCostraints) {
        /*
         * Inseriamo nel pannello il tavolo da gioco
         */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new Square();
                layoutCostraints.gridy = i;
                layoutCostraints.gridx = j;
                this.add(board[i][j], layoutCostraints);
            }
        }
    }

    /**
     * Inizializza la label che tiene traccia del turno
     * @param layoutCostraints
     */
    private void initTurnLabel(GridBagConstraints layoutCostraints) {

        layoutCostraints.gridy = 10;
        layoutCostraints.gridx = 0;
        layoutCostraints.gridwidth = 7;
        this.add(turnLabel, layoutCostraints);
    }

    /**
     * Inizializza tutte le altre componenti
     * @param c
     */
    private void initOtherComponents(ControllerInterface c) {

        controller=c;

        endDialog = new EndDialog((JFrame) this.getParent(), true);
        endDialog.setLocationRelativeTo(this.getParent());
    }

     /*
     * Il seguente metodo abilita/disabilita tutti i bottoni
      *
     */
    
      public void setEnable(boolean flag){
           enabledHumanButton(flag);
           enabledCpuButton(flag);

       }
    
    /**
     * i seguenti due metodi servono per abilitare/disabilitare
     * rispettivamente i bottoni per il giocatore umano e la CPU
     * @param flag
     */
    public void enabledHumanButton(boolean flag){
        for(int i=0;i<7;i++)
            moves[i].setEnable(flag);
    }

    public void enabledCpuButton(boolean flag){
        next.setEnabled(flag);
    }


    /**
     * i seguenti metodi vengono richiamati all'inizializzazione della partita
     */
     public void enableHumanVsCpuButton(){
            enabledHumanButton(true);
            if(usesNextButton)
                enabledCpuButton(false);
      }

      public void enableCpuVsHumanButton(){
            enabledHumanButton(false);
            if(usesNextButton)
                enabledCpuButton(true);
      }

      public void enableCpuVsCpuButton(){
            enabledCpuButton(true);
      }

       public void enalbeHumanVsHumanButton() {
            enabledHumanButton(true);
    }

      

      //Metodo per cambiare il turno di gioco
      public void changeTurn(){
          if (turn.equals(yellow)){
            turnRed();
            turnLabel.setText("Tocca al Giocatore Rosso");
          }

          else{
            turnYellow();
            turnLabel.setText("Tocca al Giocatore Giallo");
          }


      }

    /**
     * Setta il turno per il giocatore giallo controllando anche se è di tipo
     * umano o cpu
     */
    private void turnYellow() {
        turn = yellow;
        if (yellowType.equals(ControllerInterface.human)) {
            enabledHumanButton(true);
        } else {
            if(usesNextButton)
                enabledCpuButton(true);
        }
    }
    
    /**
     * Setta il turno per il giocatore rosso controllando anche se è di tipo
     * umano o cpu
     */
    private void turnRed() {
        turn = red;
        if (redType.equals(ControllerInterface.human)) {
            enabledHumanButton(true);
        } else {
            if(usesNextButton)
                enabledCpuButton(true);
        }
    }

    /**
     * Metodo invocato quando viene cliccato il pulsante che permette di
     * effettuare una mossa al giocatore umano
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
        Move buttonPressed= (Move)e.getSource();
        GameState s;

        if(buttonPressed.isEnabled()){

            setEnable(false);
            
            int col = doNextHumanMoveOnGui(buttonPressed);

            s = doNextHumanMoveOnCore(col);
                    //se la partita non è finita
                    if(!afterMoveActions(s)){
                        //se il pulsante next è disabilitato devi fare la mossa successiva
                        if(!usesNextButton){
                            /*Creo un nuovo thread per fare in modo che l'interfaccia
                             * si aggiorni e poi venga effettuata la mossa della
                             * cpu
                             */
                            Thread threadThink=new Thread(new Runnable() {

                                public void run() {
                                try {
                                    Thread.sleep(250);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                //Se uno dei due giocatori non è umano
                                if(!yellowType.equals(ControllerInterface.human) || (!redType.equals(ControllerInterface.human))){
                                    //Effettua automaticametne la mossa della cpu
                                    GameState s = doNextCpuMoveOnCore();

                                    doNextCpuMoveOnGui(s);

                                    afterMoveActions(s);
                                    }


                                }
                            });

                
                        threadThink.start();
                        }
            }


            

        }
    }

    private String getWinnerAsString(Integer win){
          String winner;
            if(win==-1)
                winner="Rosso";
            else
                if (win==0)
                    winner="Pareggio";
                else
                    winner="Giallo";
          return winner;
    }
    /**
     * Controlla se lo stato è terminale, se è così fine partita
     * altrimenti cambia turno
     * @param s
     * @return true se la partita è finita
     */
    private boolean afterMoveActions(GameState s) {
        if (s.isTerminal()) {
            endDialog.setWinner(getWinnerAsString(s.getWinner()));
            

            endDialog.setVisible(true);
            this.setEnable(false);
        } else {
            changeTurn();
        }
        return s.isTerminal();
    }

    /**
     * Notifica al controllore la nuova mossa, ritorna lo stato della partita
     * @param col
     * @return
     */
    private GameState doNextHumanMoveOnCore(int col) {
        GameState s;
        if (turn.equals(yellow)) {
            s = controller.sendHumanMove(col, ControllerInterface.yellow);
        } else {
            s = controller.sendHumanMove(col, ControllerInterface.red);
        }
        return s;
    }

    /**
     * Effettua graficamente la mossa del giocatore umano
     * @param buttonPressed
     * @return
     */
    private int doNextHumanMoveOnGui(Move buttonPressed) {
        int row = buttonPressed.getNextMove();
        int col = buttonPressed.getIndex();
        board[row][col].setIcon(turn);
        buttonPressed.setNextMove(row - 1);
        return col;
    }

    public void mousePressed(MouseEvent e) {
     
    }

    public void mouseReleased(MouseEvent e) {
     
    }

    /**
     * Effetto grafico quando il mouse è sopra il pulsante per la prossima
     * mossa dell'umano
     * @param e
     */
    public void mouseEntered(MouseEvent e) {
        
        Move button= (Move)e.getSource();
        if(button.isEnabled())
            button.setIcon(button.getInsertButtonActive());

    }

    /**
     * Effetto grafico quando il mouse è fuori dal pulsante per la prossima
     * mossa dell'umano
     * @param e
     */
    public void mouseExited(MouseEvent e) {

        Move button= (Move)e.getSource();
        button.setIcon(button.getInsertButtonPassive());

    }

    /**
     * @param yellowType the yellowType to set
     */
    public void setYellowType(String yellowType) {
        this.yellowType = yellowType;
    }

    /**
     * @param redType the redType to set
     */
    public void setRedType(String redType) {
        this.redType = redType;
    }

    /**
     * Invocato quando viene cliccato il pulsante next,
     * fa eseguire la mossa alla cpu e aggiorna l'intefraccia grafica
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        
        setEnable(false);
        GameState s;

        s = doNextCpuMoveOnCore();
        
        doNextCpuMoveOnGui(s);

        afterMoveActions(s);


 }

    /**
     * Aggiorna l'intefraccia grafica con la nuova mossa effettuata dalla CPU
     * @param s
     */
    private void doNextCpuMoveOnGui(GameState s) {

        Integer col = s.getMove();
        int row = moves[col].getNextMove();
        board[row][col].setIcon(turn);
        moves[col].setNextMove(row - 1);
    }

    /**
     * Invoca il controllore per il calcolo della nuova mossa della CPU
     * @return
     */
   private GameState doNextCpuMoveOnCore() {
        GameState s;
        if (turn.equals(yellow)) {
            s = controller.calculateAIMove(ControllerInterface.yellow);
        } else {
            s = controller.calculateAIMove(ControllerInterface.red);
        }
        return s;
    }

    /**
     * @param usesNextButton the usesNextButton to set
     */
    public void setUsesNextButton(boolean usesNextButton) {
        if(yellowType.equals(ControllerInterface.human) && (redType.equals(ControllerInterface.human)))
            this.usesNextButton = false;
        else
            if(!yellowType.equals(ControllerInterface.human) && (!redType.equals(ControllerInterface.human)))
                this.usesNextButton = true;
            else
                this.usesNextButton=usesNextButton;
    }

    



}


class Think implements Runnable{

    public void run() {

    }

}

