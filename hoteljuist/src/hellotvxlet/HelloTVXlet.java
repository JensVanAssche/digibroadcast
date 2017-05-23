package hellotvxlet;

import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;
import org.havi.ui.HScreenDimension;
import org.havi.ui.HScreenPoint;
import org.havi.ui.HStaticText;
import org.havi.ui.HVisible;


public class HelloTVXlet implements Xlet{

    private HScene scene;
    private HStaticText tekstLabel;
    
    public void initXlet(XletContext context) {
      //Template
      HSceneTemplate sceneTemplate = new HSceneTemplate();
      
      //Grootte
      sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
      //Positie
      sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
      
      //Maak Scene
      scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
      
      //Object aanmaken
      tekstLabel = new HStaticText("TEKST");
      
      //Eigenschappen Tekstlabel
      tekstLabel.setLocation(100, 100);
      tekstLabel.setSize(400, 250);
      tekstLabel.setBackground(new DVBColor(255, 255, 255, 179));
      tekstLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      
      //Add tekstlabel to Scene
      scene.add(tekstLabel);
              
    }


    
    public void startXlet() {
        scene.validate();
        scene.setVisible(true);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

}
