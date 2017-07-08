/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

/**
 *
 * @author bob
 */
public class SceneManager {
    
    private final AssetManager   assetManager;
    
    private final Node           rootNode;
    private Node scene;
    
    public SceneManager(SimpleApplication app) {
        assetManager   = app.getAssetManager();
        rootNode       = app.getRootNode();
    }
    
    public void setScene(String scenePath) {
        
        if (rootNode.hasChild(scene))
            rootNode.detachChild(scene);
        
        scene = (Node) assetManager.loadModel(scenePath);
        //scene = makeUnshaded(scene);
        addLight();
        rootNode.attachChild(scene);
        scene.setName("Scene");
        
    }
    
    public Node getScene() {
        return scene;
    }
    
    private void addLight() {
        AmbientLight al = new AmbientLight();
        scene.addLight(al);
    }
    
    private Node makeUnshaded(Node node) {
      
        SceneGraphVisitor sgv = new SceneGraphVisitor() {
 
            @Override
            public void visit(Spatial spatial) {
        
                if (spatial instanceof Geometry) {
          
                    Geometry geom = (Geometry) spatial;
                    Material mat  = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                    Material tat  = new Material(assetManager, "Common/MatDefs/Terrain/Terrain.j3md");

                    if (geom.getName().equals("Invisible"));
        
                    else if (geom.getMaterial().getTextureParam("DiffuseMap_1") != null) {
            
                        String     alTexPath  = geom.getMaterial().getTextureParam("AlphaMap").getTextureValue().getName().substring(1);
                        TextureKey alkey      = new TextureKey(alTexPath, false);
                        Texture    alTex      = assetManager.loadTexture(alkey);     
          
                        tat.setTexture("Alpha", alTex);
          
                        if (geom.getMaterial().getTextureParam("DiffuseMap") != null) {
           
                            String     d1TexPath  = geom.getMaterial().getTextureParam("DiffuseMap").getTextureValue().getName();
                            TextureKey d1key      = new TextureKey(d1TexPath, false);
                            Texture    d1Tex      = assetManager.loadTexture(d1key);             
                        
                            tat.setTexture("Tex1", d1Tex);
                            tat.getTextureParam("Tex1").getTextureValue().setWrap(Texture.WrapMode.Repeat);
                            tat.setFloat("Tex1Scale", Float.valueOf(geom.getMaterial().getParam("DiffuseMap_0_scale").getValueAsString()));
          
                        }
        
                        if (geom.getMaterial().getTextureParam("DiffuseMap_1") != null) {
              
                            String     d2TexPath  = geom.getMaterial().getTextureParam("DiffuseMap_1").getTextureValue().getName();
                            TextureKey d2key      = new TextureKey(d2TexPath, false);
                            Texture    d2Tex      = assetManager.loadTexture(d2key);                     
                        
                            tat.setTexture("Tex2", d2Tex);
                            tat.getTextureParam("Tex2").getTextureValue().setWrap(Texture.WrapMode.Repeat);
                            tat.setFloat("Tex2Scale", Float.valueOf(geom.getMaterial().getParam("DiffuseMap_1_scale").getValueAsString()));
          
                        }
        
                        if (geom.getMaterial().getTextureParam("DiffuseMap_2") != null) {
              
                            String     d3TexPath  = geom.getMaterial().getTextureParam("DiffuseMap_2").getTextureValue().getName();
                            TextureKey d3key      = new TextureKey(d3TexPath, false);
                            Texture    d3Tex      = assetManager.loadTexture(d3key);              
            
                            tat.setTexture("Tex3", d3Tex);
                            tat.getTextureParam("Tex3").getTextureValue().setWrap(Texture.WrapMode.Repeat);
                            tat.setFloat("Tex3Scale", Float.valueOf(geom.getMaterial().getParam("DiffuseMap_2_scale").getValueAsString()));
          
                        }

                        tat.setBoolean("useTriPlanarMapping", true);
                        geom.setMaterial(tat);
          
                    }
        
                    else if (geom.getMaterial().getTextureParam("DiffuseMap") != null) {
              
                        mat.setTexture("ColorMap", geom.getMaterial().getTextureParam("DiffuseMap").getTextureValue());
                        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
                        mat.setFloat("AlphaDiscardThreshold", .5f);
                        mat.setFloat("ShadowIntensity", 5);
                        mat.setVector3("LightPos", new Vector3f(5,20,5));
                        geom.setMaterial(mat);
              
                    }
       
                }
      
            }
    
        };
    
        node.depthFirstTraversal(sgv);
    
        return node;
    
    }
    
}
