package hellotvxlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.bluray.ui.event.HRcEvent;
import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HBackgroundConfigTemplate;
import org.havi.ui.HBackgroundDevice;
import org.havi.ui.HBackgroundImage;
import org.havi.ui.HConfigurationException;
import org.havi.ui.HPermissionDeniedException;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HScreen;
import org.havi.ui.HStaticText;
import org.havi.ui.HStillImageBackgroundConfiguration;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import org.havi.ui.event.HBackgroundImageEvent;
import org.havi.ui.event.HBackgroundImageListener;


public class HelloTVXlet implements Xlet, HActionListener, HBackgroundImageListener, ResourceClient , UserEventListener{

    int imageGeladen = 0;
    int imagenr=0;
    HBackgroundImage image[] = new HBackgroundImage[4];
    HStillImageBackgroundConfiguration hsbconfig;
    HStaticText hst;
    HScene scene;
    
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
      image[0]=new HBackgroundImage("pizza1.m2v");
      image[1]=new HBackgroundImage("pizza2.m2v");
      image[2]=new HBackgroundImage("pizza3.m2v");
      image[3]=new HBackgroundImage("pizza4.m2v");
      for(int i=0;i<4;i++){
          image[i].load(this);
      } 
      
      UserEventRepository repo = new UserEventRepository("repo");
      repo.addAllArrowKeys();
      repo.addKey(HRcEvent.VK_ENTER);
      EventManager manager = EventManager.getInstance();
      manager.addUserEventListener(this, repo);
      
      scene = HSceneFactory.getInstance().getDefaultHScene();
      hst = new HStaticText("Bestelling:\n", 400,50,300,400);
      hst.setFont(new Font("Teletext Mosaics",Font.PLAIN,12));
      hst.setVerticalAlignment(HVisible.VALIGN_TOP);
      hst.setHorizontalAlignment(HVisible.HALIGN_LEFT);
      scene.add(hst);
      scene.validate();
      scene.setVisible(true);
    }

    public void imageLoaded(HBackgroundImageEvent e) {
        System.out.println("Image geladen");
        imageGeladen++;
        if(imageGeladen == 4){
           try{
            hsbconfig.displayImage(image[0]);
            }catch(Exception ex){
                ex.printStackTrace();
            } 
        }
        
    }
    
    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
       
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

    public void userEventReceived(UserEvent e) {
        if(e.getType()==HRcEvent.KEY_PRESSED){
            if(e.getCode()==HRcEvent.VK_LEFT){
                imagenr--;
            }
            if(e.getCode()==HRcEvent.VK_RIGHT){
                imagenr++;
            }
            if(e.getCode()==HRcEvent.VK_ENTER){
                String tekst=hst.getTextContent(HVisible.NORMAL_STATE);
                if(imagenr==0) tekst=tekst+"Meat Lovers\n";
                if(imagenr==1) tekst=tekst+"Pepperoni\n";
                if(imagenr==2) tekst=tekst+"Cheese Lovers\n";
                if(imagenr==3) tekst=tekst+"Vegi Lovers\n";
                hst.setTextContent(tekst, HVisible.NORMAL_STATE);
                scene.repaint();
            }
            if(imagenr>3) imagenr=0;
            if(imagenr<0) imagenr=3;
            try{
                hsbconfig.displayImage(image[imagenr]);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
