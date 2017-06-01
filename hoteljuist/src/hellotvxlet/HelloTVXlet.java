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
    HStaticText tekst1 = new HStaticText("Hotel Roomservice", 50, 20, 620, 100);
    HTextButton knop1  = new HTextButton("Informatie en Contact",50,150,300,50);
    HTextButton knop2  = new HTextButton("Openingsuren",50,220,300,50);
    HTextButton knop3  = new HTextButton("Eten en drinken bestellen",50,290,300,50);
    HTextButton knop4  = new HTextButton("Bel kamermeid",50,360,300,50);
    HTextButton knopterug  = new HTextButton("Terug",50,500,100,50); //terugknop
    //Hotel Info
    HStaticText tekst2 = new HStaticText("Welkom bij Hotel Erdogan \n \n De meeste kanalen zijn vergrendeld, \n maar het nieuws is nog beschikbaar. \n \n Bij problemen neem contact met ons op 089/456 789",50, 150, 620, 250);
    //Openingsuren
    HStaticText tekst3 = new HStaticText("Om 23u sluiten we de voordeur. \n Om binnen te geraken moet je aanbellen. \n \n Het zwembad is open van 08:00 - 18:00. \n Ontbijt kan vanaf 08:00 tot 11:00.",50, 150, 620, 200);
    //Eten en drinken bestellen
    int eten = 0;
    int drinken = 0;
    HStaticText tekstEten      = new HStaticText("eten €2,30",450, 150, 190, 50);
    HStaticText tekstEtenHoeveelheid  = new HStaticText(Integer.toString(eten),520, 220, 50, 50);
    HTextButton knopEtenMin   = new HTextButton("<",450, 220, 50, 50);
    HTextButton knopEtenPlus   = new HTextButton(">",590, 220, 50, 50);
    HStaticText tekstDrinken      = new HStaticText("drinken €1,70",450, 290, 190, 50);
    HStaticText tekstDrinkenHoeveelheid  = new HStaticText(Integer.toString(drinken),520, 360, 50, 50);
    HTextButton knopDrinkenMin   = new HTextButton("<",450, 360, 50, 50);
    HTextButton knopDrinkenPlus   = new HTextButton(">",590, 360, 50, 50);
    HTextButton knopbestel  = new HTextButton("Bestel",540,500,100,50);
    HStaticText tekstbestel = new HStaticText("Bestelling geplaatst",370, 220, 300, 120);
    double prijs = 0;
    double prijsDrinken = 1.70;
    double prijsEten = 2.30;
    //Kamermeid
    HTextButton knopkuis1 = new HTextButton("Schone lakens en handdoeken",370, 150, 300, 50);
    HTextButton knopkuis2 = new HTextButton("Minimale kuis",370, 220, 300, 50);
    HTextButton knopkuis3 = new HTextButton("Volledige kuis",370, 290, 300, 50);
    //Verander achtergrond
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
      
      tekst1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      tekst1.setBackground(new DVBColor(168, 234, 226, 255));
      tekst1.setForeground(new DVBColor(0, 0, 0, 255));
      scene.add(tekst1);
      
      knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop1.setBackground(Color.BLUE);
      scene.add(knop1);
      
      knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop2.setBackground(Color.BLUE);
      scene.add(knop2);
      
      knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop3.setBackground(Color.BLUE);
      scene.add(knop3);
      
      knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
      knop4.setBackground(Color.BLUE);
      scene.add(knop4);
      
      knop1.setFocusTraversal(knop4,knop2,null,null);
      knop2.setFocusTraversal(knop1,knop3,null,null);
      knop3.setFocusTraversal(knop2,knop4,null,null);
      knop4.setFocusTraversal(knop3,knop1,null,null);
      
      knop1.setActionCommand("knop1");
      knop2.setActionCommand("knop2");
      knop3.setActionCommand("knop3");
      knop4.setActionCommand("knop4");
      knopterug.setActionCommand("knopterug");
      knopEtenMin.setActionCommand("knopEtenMin");
      knopEtenPlus.setActionCommand("knopEtenPlus");
      knopDrinkenMin.setActionCommand("knopDrinkenMin");
      knopDrinkenPlus.setActionCommand("knopDrinkenPlus");
      knopbestel.setActionCommand("knopbestel");
      knopkuis1.setActionCommand("knopkuis1");
      knopkuis2.setActionCommand("knopkuis2");
      knopkuis3.setActionCommand("knopkuis3");
      
      knop1.addHActionListener(this);
      knop2.addHActionListener(this);
      knop3.addHActionListener(this);
      knop4.addHActionListener(this);
      knopterug.addHActionListener(this);
      knopEtenMin.addHActionListener(this);
      knopEtenPlus.addHActionListener(this);
      knopDrinkenMin.addHActionListener(this);
      knopDrinkenPlus.addHActionListener(this);
      knopbestel.addHActionListener(this);
      knopkuis1.addHActionListener(this);
      knopkuis2.addHActionListener(this);
      knopkuis3.addHActionListener(this);
      
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
          
        if(arg0.getActionCommand().equals("knop1")) { //hotelinfo
            scene.remove(knop1);
            scene.remove(knop2);
            scene.remove(knop3);
            scene.remove(knop4);
            scene.remove(tekstEten);
            scene.remove(tekstEtenHoeveelheid);
            scene.remove(knopEtenMin);
            scene.remove(knopEtenPlus);
            scene.remove(tekstDrinken);
            scene.remove(tekstDrinkenHoeveelheid);
            scene.remove(knopDrinkenMin);
            scene.remove(knopDrinkenPlus);
            scene.remove(knopbestel);
            scene.remove(tekstbestel);
            scene.remove(knopkuis1);
            scene.remove(knopkuis2);
            scene.remove(knopkuis3);
            
            knopterug.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopterug.setBackground(Color.ORANGE);
            scene.add(knopterug);
            
            tekst2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekst2.setBackground(new DVBColor(168, 234, 226, 255));
            tekst2.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(tekst2);
            
            knopterug.requestFocus();
            scene.repaint();
        }
        
        if(arg0.getActionCommand().equals("knop2")) { //openingsuren
            scene.remove(knop1);
            scene.remove(knop2);
            scene.remove(knop3);
            scene.remove(knop4);
            scene.remove(tekstEten);
            scene.remove(tekstEtenHoeveelheid);
            scene.remove(knopEtenMin);
            scene.remove(knopEtenPlus);
            scene.remove(tekstDrinken);
            scene.remove(tekstDrinkenHoeveelheid);
            scene.remove(knopDrinkenMin);
            scene.remove(knopDrinkenPlus);
            scene.remove(knopbestel);
            scene.remove(tekstbestel);
            scene.remove(knopkuis1);
            scene.remove(knopkuis2);
            scene.remove(knopkuis3);
            
            knopterug.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopterug.setBackground(Color.ORANGE);
            scene.add(knopterug);
            
            tekst3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekst3.setBackground(new DVBColor(168, 234, 226, 255));
            tekst3.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(tekst3);
            
            knopterug.requestFocus();
            scene.repaint();
        }
        
        if(arg0.getActionCommand().equals("knop3")) { //eten bestellen
            scene.remove(tekstbestel);
            scene.remove(knopkuis1);
            scene.remove(knopkuis2);
            scene.remove(knopkuis3);
            
            tekstEten.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekstEten.setBackground(new DVBColor(168, 234, 226, 255));
            tekstEten.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(tekstEten);
            
            tekstEtenHoeveelheid.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekstEtenHoeveelheid.setBackground(new DVBColor(168, 234, 226, 255));
            tekstEtenHoeveelheid.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(tekstEtenHoeveelheid);
            
            knopEtenMin.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopEtenMin.setBackground(Color.BLUE);
            scene.add(knopEtenMin);
            
            knopEtenPlus.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopEtenPlus.setBackground(Color.BLUE);
            scene.add(knopEtenPlus);
            
            tekstDrinken.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekstDrinken.setBackground(new DVBColor(168, 234, 226, 255));
            tekstDrinken.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(tekstDrinken);
            
            tekstDrinkenHoeveelheid.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekstDrinkenHoeveelheid.setBackground(new DVBColor(168, 234, 226, 255));
            tekstDrinkenHoeveelheid.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(tekstDrinkenHoeveelheid);
            
            knopDrinkenMin.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopDrinkenMin.setBackground(Color.BLUE);
            scene.add(knopDrinkenMin);
            
            knopDrinkenPlus.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopDrinkenPlus.setBackground(Color.BLUE);
            scene.add(knopDrinkenPlus);
            
            knopbestel.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopbestel.setBackground(Color.ORANGE);
            scene.add(knopbestel);
            
            knopEtenMin.setFocusTraversal(null,knopDrinkenMin,knop1,knopEtenPlus);
            knopEtenPlus.setFocusTraversal(null,knopDrinkenPlus,knopEtenMin,null);
            knopDrinkenMin.setFocusTraversal(knopEtenMin,knopbestel,knop1,knopDrinkenPlus);
            knopDrinkenPlus.setFocusTraversal(knopEtenPlus,knopbestel,knopDrinkenMin,null);
            knopbestel.setFocusTraversal(knopDrinkenPlus, null, knop1, null);
            knop1.setFocusTraversal(knop4,knop2,null,knopEtenMin);
            knop2.setFocusTraversal(knop1,knop3,null,knopEtenMin);
            knop3.setFocusTraversal(knop2,knop4,null,knopEtenMin);
            knop4.setFocusTraversal(knop3,knop1,null,knopEtenMin);
            
            knopEtenMin.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("knopEtenMin")) {
            if(eten > 0) {
                eten--;
                prijs -= prijsEten;
            }
            tekstEtenHoeveelheid.setTextContent(Integer.toString(eten), HVisible.NORMAL_STATE);
        }
        if(arg0.getActionCommand().equals("knopEtenPlus")) {
            eten++;
            prijs += prijsEten;
            tekstEtenHoeveelheid.setTextContent(Integer.toString(eten), HVisible.NORMAL_STATE);
        }
        if(arg0.getActionCommand().equals("knopDrinkenMin")) {
            if(drinken > 0) {
                drinken--;
                prijs -= prijsDrinken;
            }
            tekstDrinkenHoeveelheid.setTextContent(Integer.toString(drinken), HVisible.NORMAL_STATE);
        }
        if(arg0.getActionCommand().equals("knopDrinkenPlus")) {
            drinken++;
            prijs += prijsDrinken;
            tekstDrinkenHoeveelheid.setTextContent(Integer.toString(drinken), HVisible.NORMAL_STATE);
        }
        if(arg0.getActionCommand().equals("knop4")) { //kuis
            scene.remove(tekstEten);
            scene.remove(tekstEtenHoeveelheid);
            scene.remove(knopEtenMin);
            scene.remove(knopEtenPlus);
            scene.remove(tekstDrinken);
            scene.remove(tekstDrinkenHoeveelheid);
            scene.remove(knopDrinkenMin);
            scene.remove(knopDrinkenPlus);
            scene.remove(knopbestel);
            scene.remove(tekstbestel);
            
            knopkuis1.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopkuis1.setBackground(new DVBColor(168, 234, 226, 255));
            knopkuis1.setForeground(Color.BLACK);
            scene.add(knopkuis1);
            
            knopkuis2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopkuis2.setBackground(new DVBColor(168, 234, 226, 255));
            knopkuis2.setForeground(Color.BLACK);
            scene.add(knopkuis2);
            
            knopkuis3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knopkuis3.setBackground(new DVBColor(168, 234, 226, 255));
            knopkuis3.setForeground(Color.BLACK);
            scene.add(knopkuis3);
            
            knopkuis1.setFocusTraversal(knopkuis3,knopkuis2,knop1,null);
            knopkuis2.setFocusTraversal(knopkuis1,knopkuis3,knop1,null);
            knopkuis3.setFocusTraversal(knopkuis2,knopkuis1,knop1,null);
            knop1.setFocusTraversal(knop4,knop2,null,knopkuis1);
            knop2.setFocusTraversal(knop1,knop3,null,knopkuis1);
            knop3.setFocusTraversal(knop2,knop4,null,knopkuis1);
            knop4.setFocusTraversal(knop3,knop1,null,knopkuis1);
            
            knopkuis1.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("knopkuis1")) {
            scene.remove(knopkuis1);
            scene.remove(knopkuis2);
            scene.remove(knopkuis3);
            
            tekstbestel.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekstbestel.setBackground(new DVBColor(168, 234, 226, 255));
            tekstbestel.setForeground(Color.BLACK);
            tekstbestel.setTextContent("Bestelling geplaatst." + "\n We zijn zo bij u met \n schone lakens en handdoeken.", HVisible.NORMAL_STATE);
            scene.add(tekstbestel);
            
            knop1.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("knopkuis2")) {
            scene.remove(knopkuis1);
            scene.remove(knopkuis2);
            scene.remove(knopkuis3);
            
            tekstbestel.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekstbestel.setBackground(new DVBColor(168, 234, 226, 255));
            tekstbestel.setForeground(Color.BLACK);
            tekstbestel.setTextContent("Bestelling geplaatst." + "\n We zijn zo bij u voor \n een minimale kuis", HVisible.NORMAL_STATE);
            scene.add(tekstbestel);
            
            knop1.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("knopkuis3")) {
            scene.remove(knopkuis1);
            scene.remove(knopkuis2);
            scene.remove(knopkuis3);
            
            tekstbestel.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekstbestel.setBackground(new DVBColor(168, 234, 226, 255));
            tekstbestel.setForeground(Color.BLACK);
            tekstbestel.setTextContent("Bestelling geplaatst." + "\n We zijn zo bij u voor \n een volledige kuis.", HVisible.NORMAL_STATE);
            scene.add(tekstbestel);
            
            knop1.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("knopterug")) { //terugknop
            knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knop1.setBackground(Color.BLUE);
            scene.add(knop1);

            knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knop2.setBackground(Color.BLUE);
            scene.add(knop2);

            knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knop3.setBackground(Color.BLUE);
            scene.add(knop3);

            knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
            knop4.setBackground(Color.BLUE);
            scene.add(knop4);
            
            scene.remove(tekst2);
            scene.remove(tekst3);
            scene.remove(knopterug);
            
            knop1.requestFocus();
            scene.repaint();
        }
        
        
        if(arg0.getActionCommand().equals("knopbestel")) {
            scene.remove(tekstEten);
            scene.remove(tekstEtenHoeveelheid);
            scene.remove(knopEtenMin);
            scene.remove(knopEtenPlus);
            scene.remove(tekstDrinken);
            scene.remove(tekstDrinkenHoeveelheid);
            scene.remove(knopDrinkenMin);
            scene.remove(knopDrinkenPlus);
            scene.remove(knopbestel);
            
            tekstbestel.setBackgroundMode(HVisible.BACKGROUND_FILL);
            tekstbestel.setBackground(new DVBColor(168, 234, 226, 255));
            tekstbestel.setForeground(Color.BLACK);
            tekstbestel.setTextContent("Bestelling geplaatst. \n totaal: € " + Double.toString(prijs) + "\n We zijn zo bij u.", HVisible.NORMAL_STATE);
            scene.add(tekstbestel);
            
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
