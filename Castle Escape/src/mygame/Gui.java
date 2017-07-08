/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import org.lwjgl.opengl.Display;

/**
 *
 * @author bob
 */
public class Gui {
    
    private final AppStateManager stateManager;
    private BitmapText crossHair;
    
    public Gui(AppStateManager stateManager) {
        this.stateManager = stateManager;
        createCrossHair();
    }
    
    //Creates the Crosshair
    private void createCrossHair() {
        BitmapFont guiFont = stateManager.getApplication().getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        crossHair          = new BitmapText(guiFont, false);
        crossHair.setName("CrossHair");
        crossHair.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        crossHair.setText("+");
        crossHair.setLocalTranslation(Display.getWidth() / 2 - crossHair.getLineWidth()/2, Display.getHeight() / 2 + crossHair.getLineHeight()/2, 0);
        ((SimpleApplication) stateManager.getApplication()).getGuiNode().attachChild(crossHair);
    }
    
}
