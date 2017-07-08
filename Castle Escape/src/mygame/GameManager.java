/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.math.Vector3f;

/**
 *
 * @author bob
 */
public class GameManager extends AbstractAppState {
    
    private final SimpleApplication  app;
    private final SceneManager       sceneManager;
    private final InteractionManager interactionManager;
    private final CameraManager      cameraManager;
    private final PhysicsManager     physicsManager;
    private final EntityManager      entityManager;
    private final Gui                gui;
    
    public GameManager(SimpleApplication app) {
        this.app           = app;
        sceneManager       = new SceneManager(app);
        interactionManager = new InteractionManager(app);
        entityManager      = new EntityManager(app.getStateManager(), interactionManager);
        cameraManager      = new CameraManager(app, entityManager.getPlayerManager().getPlayer());
        physicsManager     = new PhysicsManager(app.getStateManager());
        gui                = new Gui(app.getStateManager());
        changeScene("Scenes/Dungeon.j3o", null);
    }
    
    public void changeScene(String scenePath, Vector3f startSpot) {
        
        Player player = entityManager.getPlayerManager().getPlayer();
        physicsManager.clearPhysics(app.getStateManager(), app.getRootNode());
        
        if (startSpot == null)
            startSpot = new Vector3f(0,0,0);
        
        sceneManager.setScene(scenePath);
        app.getRootNode().attachChild(player.getModel());
        physicsManager.addToPhysics(sceneManager.getScene());
        physicsManager.getPhysics().getPhysicsSpace().add(player.getPhys());
        player.getPhys().warp(startSpot);
        entityManager.initEntities();
        
    }
    
    public InteractionManager getInteractionManager() {
        return interactionManager;
    }
    
    @Override
    public void update(float tpf) {
        
        cameraManager.update(tpf);
        entityManager.update(tpf);
        
    }
    
}
