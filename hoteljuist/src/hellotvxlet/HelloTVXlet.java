package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;
import org.havi.ui.HScreenDimension;
import org.havi.ui.HScreenPoint;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {

    private HScene scene;
    private HStaticText tekstLabel;
    HTextButton knop1 = new HTextButton("3", 100,250,100,100);
    HTextButton knop2 = new HTextButton("5", 250,250,100,100);
    HTextButton knop3 = new HTextButton("0", 400,250,100,100);
    HTextButton knop4 = new HTextButton("2", 550,250,100,100);
    
    public void initXlet(XletContext context) {
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
    }


    
    public void startXlet() {
        scene.validate();
        scene.setVisible(true);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
        System.out.println(arg0.getActionCommand());
        
        if(arg0.getActionCommand().equals("Juist"))
        {    
            scene.remove(knop1);
            scene.remove(knop2);
            scene.remove(knop3);
            scene.remove(knop4);
            scene.repaint();
        }
        else{
          scene.remove(knop1);
          scene.remove(knop2);
          scene.remove(knop3);
          scene.remove(knop4);
          scene.repaint();
        }
       
    }

}
