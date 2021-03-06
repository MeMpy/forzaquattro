package connectFour;

import java.util.Random;

/**
 * AIPlayerInterface.java
 *
 * File generated from the ConnectFour::AIPlayerInterface uml Interface
 * Generated by the Acceleo UML 2.1 to Java generator module (Obeo)
 * $ Date : 23/04/10 15.19.33 (23 aprile 2010) $
 */

/**
 * TODO: RINOMINARE AIPlayerInterface IN AIAbstractPlayer
 */
/**
 * Description of the interface AIPlayerInterface.
 *
 */

public  abstract class AIPlayerInterface{
    public static final Integer maxUtilityValue = 2000;
    private Integer playerId;
    private Heuristic heuristic = new Heuristic();
    private Integer horizon = 0;
    private Integer examinatedNodeNumber = 0;
    private Integer[] fromCenterNextMoves;

    /**
     * Constructor.
     */

    public AIPlayerInterface(Integer playerId, Integer horizon) {
        setPlayerId(playerId);
        if(horizon>0)setHorizon(horizon);
    }

    /**
     * metodo che determina la prossima mossa da eseguire e restituisce il nuovo stato
     * @param gameState Stato attuale del gioco
     * @return nuovo stato raggiunto applicando la mossa scelta
     */
    public abstract GameState nextMove(GameState gameState);

    /**
     * @return  il numero di nodi esaminati nel calcolo dell'ultima mossa effettuata
     */
    public Integer getExaminatedNodeNumber() {
        return examinatedNodeNumber;
    }

    /**
     * restituisce un array di dimensione dim contenente interi da 0 a dim-1 in
     * ordine casuale
     * @param dim
     * @return
     */
    protected Integer[] randomMoves(Integer dim){
        Random r=new Random();
        Integer[] nextMoves = new Integer[dim];
        Integer j=0;
        Integer temp;
        for(j=0;j<dim;j++)nextMoves[j]=j;
        j=0;
        while(j<dim){
            Integer move=r.nextInt(dim-j);
            //scambia
            temp=nextMoves[move];
            nextMoves[move]=nextMoves[dim-j-1];
            nextMoves[dim-j-1]=temp;
            j++;
        }

        return nextMoves;
    }

    /**
     * restituisce un array di interi di dimensione dim contenente numeri
     * tra 0 e dim-1 ordinati dal centro verso gli estremi dell'intervallo
     * @param dim
     * @return
     */
    protected Integer[] getFromCenterNextMoves(Integer dim){
        Integer[] nextMoves;
        Integer i;
        Integer j;
        Integer center;

        if(this.fromCenterNextMoves!=null && dim.equals(this.fromCenterNextMoves.length)) return this.fromCenterNextMoves;

        nextMoves= new Integer[dim];
        center = dim / 2;
        nextMoves[0]=center;
        j=1;
        for(i=1;i<=center;i++){
            if(center+i>=0 && center+i<dim) nextMoves[j++]=center+i;
            if(center-i>=0 && center-i<dim) nextMoves[j++]=center-i;
        }

        this.fromCenterNextMoves=nextMoves;
        return nextMoves;
    }


    /**
     * valuta la funzione di utilità
     * @param gameState stato di cui si vuole valutare la funzione di utilità
     * @return intero che rappresenta l'utilità dello stato
     */
    public Integer utility(GameState gameState){
        return maxUtilityValue*getPlayerId()*gameState.getWinner();
    }

    /**
     * @return the playerId
     */
    public Integer getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerId to set
     */
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the horizon
     */
    public Integer getHorizon() {
        return horizon;
    }

    /**
     * @param horizon the horizon to set
     */
    public void setHorizon(Integer horizon) {
        this.horizon = horizon;
    }

    /**
     * @return the heuristic
     */
    public Heuristic getHeuristic() {
        return heuristic;
    }

    /**
     * @param examinatedNodeNumber the examinatedNodeNumber to set
     */
    public void setExaminatedNodeNumber(Integer examinatedNodeNumber) {
        this.examinatedNodeNumber = examinatedNodeNumber;
    }

}