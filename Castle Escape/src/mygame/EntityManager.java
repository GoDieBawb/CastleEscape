/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;

/**
 *
 * @author bob
 */
public class EntityManager {
    
    private final ArrayList<Entity> entities;
    private final AppStateManager   stateManager;
    private final Camera            cam;
    private final PlayerManager     playerManager;
    
    public EntityManager(AppStateManager stateManager, InteractionManager interactionManager) {
        entities          = new ArrayList<>();
        playerManager     = new PlayerManager(interactionManager);
        this.stateManager = stateManager;
        cam               = stateManager.getApplication().getCamera();
    }
    
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    
    public void initEntities() {
        
        entities.clear();
        Node scene = (Node) ((SimpleApplication)stateManager.getApplication()).getRootNode().getChild("Scene");
        
        for (Spatial child : ((Node) scene.getChild("Misc")).getChildren()) {
            
            String type = child.getUserData("Type");
            
            if (type != null) {
                
                Entity entity = null;
                
                if (type.equals("Door")) {
                    entity = new Door((Node) child, stateManager);
                }   
                
                entities.add(entity);
                
            }
            
        }
        
    }
    
    private void checkEntities() {
        
        for (int i = 0; i < entities.size(); i++) {
            
            Entity entity = entities.get(i);
            
            CollisionResults results = new CollisionResults();
            Ray              ray     = new Ray(cam.getLocation(), cam.getDirection());
            ray.setLimit(1.5f);
            entity.getModel().collideWith(ray, results);
            
            if (results.getClosestCollision() != null) {
                
                if (!entity.inRange()) {
                    entity.setInRange(true);
                }
                
            }
            
            else {
                
                if (entity.inRange()) {
                    entity.setInRange(false);
                }
                
            }
            
            if (playerManager.getPlayer().isInteracting()) {
                
                if (entity.inRange()) {
                    entity.act();
                    playerManager.getPlayer().setInteracting(false);
                }
                
            }
            
        }
        
        if (playerManager.getPlayer().isInteracting()) {
            playerManager.getPlayer().setInteracting(false);
        }
        
    }
    
    public void update(float tpf) {
        playerManager.update(tpf);
        checkEntities();
    }
    
}
