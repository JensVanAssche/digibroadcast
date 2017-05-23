package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;
import org.dvb.ui.DVBColor;
import org.havi.ui.HBackgroundConfigTemplate;
import org.havi.ui.HBackgroundDevice;
import org.havi.ui.HBackgroundImage;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;
import org.havi.ui.HScreen;
import org.havi.ui.HScreenDimension;
import org.havi.ui.HScreenPoint;
import org.havi.ui.HStaticText;
import org.havi.ui.HStillImageBackgroundConfiguration;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import org.havi.ui.event.HBackgroundImageEvent;
import org.havi.ui.event.HBackgroundImageListener;


public class HelloTVXlet implements Xlet, HActionListener, HBackgroundImageListener, ResourceClient {

    HScene scene;
    HStaticText tekst2 = new HStaticText("Juist",100, 400, 550, 100);
    HTextButton knop1 = new HTextButton("3", 100,250,100,100);
    HTextButton knop2 = new HTextButton("5", 250,250,100,100);
    HTextButton knop3 = new HTextButton("0", 400,250,100,100);
    HTextButton knop4 = new HTextButton("2", 550,250,100,100);
    
    HBackgroundImage image = new HBackgroundImage("pizza1.m2v");
    HStillImageBackgroundConfiguration hsbconfig;
    
    public void initXlet(XletContext context) {
      HScreen screen=HScreen.getDefaultHScreen();
      HBackgroundDevice hbgDev=screen.getDefaultHBackgroundDevice();
      if (hbgDev.reserveDevice(this))
      {
          System.out.println("Achtergrond device succesvol geresereveerd");
      }
      HBackgroundConfigTemplate bgTemplate=new HBackgroundConfigTemplate();
      bgTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, HBackgroundConfigTemplate.REQUIRED);
      hsbconfig=(HStillImageBackgroundConfiguration) hbgDev.getBestConfiguration(bgTemplate);
    
      try {
         hbgDev.setBackgroundConfiguration(hsbconfig); 
      } catch(Exception ex){
          ex.printStackTrace();
      } 
      
      image.load(this);
      
      scene = HSceneFactory.getInstance().getDefaultHScene();
      
      HStaticText tekst1 = new HStaticText("Hotel Roomservice",100, 20, 550, 100);
      tekst1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      tekst1.setBackground(new DVBColor(168, 234, 226, 255));
      tekst1.setForeground(new DVBColor(0, 0, 0, 255));
      scene.add(tekst1);      
      
      HTextButton knop1=new HTextButton("Informatie en Contact",100,150,300,50);
      knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop1.setBackground(Color.BLUE);
      scene.add(knop1);
      
      HTextButton knop2=new HTextButton("Openingsuren",100,220,300,50);
      knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop2.setBackground(Color.BLUE);
      scene.add(knop2);
      
      HTextButton knop3=new HTextButton("Eten en drinken",100,290,300,50);
      knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop3.setBackground(Color.BLUE);
      scene.add(knop3);
      
      HTextButton knop4=new HTextButton("Kamer kuisen",100,360,300,50);
      knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop4.setBackground(Color.BLUE);
      scene.add(knop4);
      
      knop1.setFocusTraversal(knop4,knop2,null,null);
      knop2.setFocusTraversal(knop1,knop3,null,null);
      knop3.setFocusTraversal(knop2,knop4,null,null);
      knop4.setFocusTraversal(knop3,knop1,null,null);
      
      knop1.addHActionListener(this);
      knop2.addHActionListener(this);
      knop3.addHActionListener(this);
      knop4.addHActionListener(this);
      
      knop1.requestFocus();        
      
      scene.validate();
      scene.setVisible(true);
      
    }


    
    public void startXlet() {
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
        System.out.println(arg0.getActionCommand());
        
        
        if(arg0.getActionCommand().equals("help"))
        {
          scene.remove(knop1);
          scene.remove(knop3);
          knop2.setFocusTraversal(null, null, knop4, knop4);
          knop4.setFocusTraversal(null, null, knop2, knop2);
          scene.repaint();
          return;
        }
        
        if(arg0.getActionCommand().equals("Juist"))
        {    
          tekst2.setBackgroundMode(HVisible.BACKGROUND_FILL);
          tekst2.setTextContent("Juist", HVisible.NORMAL_STATE);
          tekst2.setBackground(Color.GREEN);
          scene.add(tekst2);
          scene.repaint();
        }
        else{
          tekst2.setBackgroundMode(HVisible.BACKGROUND_FILL);
          tekst2.setTextContent("Fout", HVisible.NORMAL_STATE);
          tekst2.setBackground(Color.RED);
          scene.add(tekst2);
          scene.repaint();
        }
       
    }

    public void imageLoaded(HBackgroundImageEvent e) {
        System.out.println("Image geladen");
           try{
            hsbconfig.displayImage(image);
            }catch(Exception ex){
                ex.printStackTrace();
            } 
    }

    public void imageLoadFailed(HBackgroundImageEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean requestRelease(ResourceProxy proxy, Object requestData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void release(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyRelease(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
