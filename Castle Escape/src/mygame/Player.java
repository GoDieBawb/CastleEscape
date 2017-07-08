/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.scene.Node;

/**
 *
 * @author bob
 */
public class Player {
    
    private final BetterCharacterControl phys;
    private final Node                   model;
    private final float                  speed;
    private       boolean                isInteracting;
    
    
    public Player() {
        phys  = new BetterCharacterControl(.35f, 1.0f, 100);
        model = new Node();
        speed = 2;
        model.addControl(phys);
    }
    
    public BetterCharacterControl getPhys() {
        return phys;
    }
    
    public float getSpeedMult() {
        return speed;
    }
    
    public Node getModel() {
        return model;
    }
    
    public void setInteracting(boolean isInteracting) {
        this.isInteracting = isInteracting;
    }
    
    public boolean isInteracting() {
        return isInteracting;
    }
    
}
