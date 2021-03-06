/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Menu.java
 *
 * Created on 27-apr-2010, 10.58.25
 */

package connectFour.GUI;

import connectFour.ControllerInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

/**
 *
 * @author Ross
 */
public class Menu extends javax.swing.JPanel implements ActionListener {

  
    /** Creates new form Menu */
    public Menu() {
        initComponents();
        setComponentProperty();
        setEnable(true);
    }

    /**
     * setta le proprietà e i listener dei componenti
     */
    private void setComponentProperty() {
        yellowPlayerCombo.addActionListener(this);
        redPlayerCombo.addActionListener(this);
    }

    /**
     * Controlla se il tipo di giocatore è Cpu o umano e
     * abilita/Disabilita la combo box dell'orizzonte del giocatore giallo
     * @param yellow
     */
    private void setYellowHorizontComboEnable(JComboBox yellow){
        if(yellow.getSelectedIndex()!=0)
            yellowHorizontCombo.setEnabled(true);
        else
            yellowHorizontCombo.setEnabled(false);
    }

    /**
     * Controlla se il tipo di giocatore è Cpu o umano e
     * abilita/Disabilita la combo box dell'orizzonte del giocatore rosso
     * @param yellow
     */
    private void setRedHorizontComboEnable(JComboBox red){
        if(red.getSelectedIndex()!=0)
            redHorizontCombo.setEnabled(true);
        else
            redHorizontCombo.setEnabled(false);
    }

    public void setEnable(boolean flag){
        redPlayerCombo.setEnabled(flag);
        setRedHorizontComboEnable(redPlayerCombo);
        yellowPlayerCombo.setEnabled(flag);
        setYellowHorizontComboEnable(yellowPlayerCombo);
        startButton.setEnabled(flag);
    }

    public void setEnableInitGame(boolean flag){
        redPlayerCombo.setEnabled(flag);
        redHorizontCombo.setEnabled(flag);
        yellowPlayerCombo.setEnabled(flag);
        yellowHorizontCombo.setEnabled(flag);
        startButton.setEnabled(flag);
    }

    public String getYellowPlayer(){
       return yellowPlayerCombo.getSelectedItem().toString();
    }

    public String getRedPlayer(){
       return redPlayerCombo.getSelectedItem().toString();
    }

     /**
     * @return the yellowPlayerHorizont
     */
    public int getYellowPlayerHorizont() {
        return yellowHorizontCombo.getSelectedIndex()+1;
    }

    /**
     * @return the redPlayerHorizont
     */
    public int getRedPlayerHorizont() {
        return redHorizontCombo.getSelectedIndex()+1;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        yellowPlayerCombo = new javax.swing.JComboBox();
        startButton = new javax.swing.JButton();
        yellowPlayerLabel = new javax.swing.JLabel();
        redPlayerLabel = new javax.swing.JLabel();
        redPlayerCombo = new javax.swing.JComboBox();
        redHorizontCombo = new javax.swing.JComboBox();
        redHorizontLabel = new javax.swing.JLabel();
        yellowHorizontLabel = new javax.swing.JLabel();
        yellowHorizontCombo = new javax.swing.JComboBox();

        yellowPlayerCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ControllerInterface.human, ControllerInterface.alphaBeta, ControllerInterface.miniMax}));
        yellowPlayerCombo.setName("yellow"); // NOI18N

        startButton.setText("Inizia!");
        startButton.setName("start"); // NOI18N

        yellowPlayerLabel.setText("Giocatore Giallo");

        redPlayerLabel.setText("Giocatore Rosso");

        redPlayerCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ControllerInterface.human, ControllerInterface.alphaBeta, ControllerInterface.miniMax }));
        redPlayerCombo.setName("red"); // NOI18N

        redHorizontCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        redHorizontCombo.setEnabled(false);

        redHorizontLabel.setText("Difficoltà");

        yellowHorizontLabel.setText("Difficoltà");

        yellowHorizontCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        yellowHorizontCombo.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(redHorizontCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(redPlayerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(redHorizontLabel)
                    .addComponent(yellowHorizontLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(yellowHorizontCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yellowPlayerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(yellowPlayerLabel)
                    .addComponent(redPlayerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(yellowPlayerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yellowPlayerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yellowHorizontLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yellowHorizontCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(redPlayerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(redPlayerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(redHorizontLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redHorizontCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(startButton)
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox redHorizontCombo;
    private javax.swing.JLabel redHorizontLabel;
    private javax.swing.JComboBox redPlayerCombo;
    private javax.swing.JLabel redPlayerLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JComboBox yellowHorizontCombo;
    private javax.swing.JLabel yellowHorizontLabel;
    private javax.swing.JComboBox yellowPlayerCombo;
    private javax.swing.JLabel yellowPlayerLabel;
    // End of variables declaration//GEN-END:variables


    /**
     * Invocato quando viene selezionato un valore dalla combobox relativa
     * alla scelta del tipo di giocatore, abilita la combo dell'orizzonte se
     * il giocatore selezionato è di tipo CPU altrimenti la disabilita
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox combo=(JComboBox) e.getSource();

        if (combo.getName().equals("yellow")){

            setYellowHorizontComboEnable(combo);

        }else{
        
            setRedHorizontComboEnable(combo);

        }
    }

    /**
     * registra il frame contenitore allo start button
     * @param connect4Frame
     */
    void registerToStartButton(ActionListener connect4Frame) {
        startButton.addActionListener(connect4Frame);
    }



}
