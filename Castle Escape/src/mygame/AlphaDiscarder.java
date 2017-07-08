/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.material.RenderState;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;

/**
 *
 * @author bob
 */
public class AlphaDiscarder extends AbstractAppState {
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        discardAlpha(((SimpleApplication) app).getRootNode());
    }
    
    private Node discardAlpha(Node node) {
      
        SceneGraphVisitor sgv = new SceneGraphVisitor() {
 
            @Override
            public void visit(Spatial spatial) {
        
                if (spatial instanceof Geometry) {
                    ((Geometry) spatial).getMaterial().setFloat("AlphaDiscardThreshold", 0.5f);
                    ((Geometry) spatial).getMaterial().getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
                }
       
            }
    
        };
    
        node.depthFirstTraversal(sgv);
    
        return node;
    
    }
    
}
