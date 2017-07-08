/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author bob
 */
public class PlayerManager {
    
    private final Player             player;
    private final InteractionManager interactionManager;
    private       boolean            intPressed;
    
    public PlayerManager(InteractionManager interactionManager) {
        player                  = new Player();
        this.interactionManager = interactionManager;
    }
    
    private void fallCheck() {
        if (player.getModel().getLocalTranslation().y < -5)
            player.getPhys().warp(player.getModel().getLocalTranslation().add(0,10,0));
    }    
    
    public Player getPlayer() {
        return player;
    }
    
    private void updateKeys() {
        
        if (interactionManager.getIsPressed("Interact")) {
            intPressed = true;
        }
        
        else if (intPressed) {
            intPressed = false;
            player.setInteracting(true);
        }
        
    }
    
    public void update(float tpf) {
        fallCheck();
        updateKeys();
    }
    
}
