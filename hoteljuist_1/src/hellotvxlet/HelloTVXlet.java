package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {

        HScene scene;
        HStaticText tekst2 = new HStaticText("Juist",100, 400, 550, 100);
        HTextButton knop1 = new HTextButton("3", 100,250,100,100);
        HTextButton knop2 = new HTextButton("5", 250,250,100,100);
        HTextButton knop3 = new HTextButton("0", 400,250,100,100);
        HTextButton knop4 = new HTextButton("2", 550,250,100,100);
        HTextButton help = new HTextButton("help", 550, 25,100,50);

    public void initXlet(XletContext context) {
      scene = HSceneFactory.getInstance().getDefaultHScene();
      
      HStaticText tekst1 = new HStaticText("Hoeveel letters heeft de kleur blauw?",100, 100, 550, 100);
      tekst1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      tekst1.setBackground(Color.BLUE);
      scene.add(tekst1);
      
      help.setBackgroundMode(HVisible.BACKGROUND_FILL);
      help.setBackground(Color.ORANGE);
      scene.add(help);
      
      
      knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop1.setBackground(Color.BLACK);
      scene.add(knop1);
      
      knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop2.setBackground(Color.BLACK);
      scene.add(knop2);
      
      knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop3.setBackground(Color.BLACK);
      scene.add(knop3);
      
      knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop4.setBackground(Color.BLACK);
      scene.add(knop4);
      
      knop1.setFocusTraversal(help, help, knop4, knop2);
      knop2.setFocusTraversal(help, help, knop1, knop3);
      knop3.setFocusTraversal(help, help, knop2, knop4);
      knop4.setFocusTraversal(help, help, knop3, knop1);
      help.setFocusTraversal(knop1, knop4, null, null);
      
      knop1.setActionCommand("Fout");
      knop2.setActionCommand("Juist");
      knop3.setActionCommand("Fout");
      knop4.setActionCommand("Fout");
      help.setActionCommand("help");
      
      knop1.addHActionListener(this);
      knop2.addHActionListener(this);
      knop3.addHActionListener(this);
      knop4.addHActionListener(this);
      help.addHActionListener(this);
      
      knop1.requestFocus();
      
    }

    public void startXlet() {
    
     scene.validate(); scene.setVisible(true);
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
          scene.remove(help);
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
}