/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.scene.Node;

/**
 *
 * @author bob
 */
public abstract class Entity {

    private final Node model;
    private boolean inRange;
    
    public Entity(Node model) {
        this.model = model;
    }
    
    public void act() {
    }
    
    public Node getModel() {
        return model;
    }
    
    public void setInRange(boolean inRange) {
        this.inRange = inRange;
    }
    
    public boolean inRange() {
        return inRange;
    }
    
}
