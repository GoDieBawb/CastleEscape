/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author bob
 */
public class Door extends Entity {
    
    private final String          destination;
    private final Vector3f        startSpot;
    private final AppStateManager stateManager;
    
    public Door(Node model, AppStateManager stateManager) {
        super(model);
        this.stateManager = stateManager;
        destination = model.getUserData("Destination");
        String[] ar = model.getUserData("StartSpot").toString().split(",");
        float     x = Float.valueOf(ar[0]);
        float     y = Float.valueOf(ar[1]);
        float     z = Float.valueOf(ar[2]);
        startSpot   = new Vector3f(x, y, z);
    }
    
    @Override
    public void act() {
        stateManager.getState(GameManager.class).changeScene(destination, startSpot);
    }
    
}
