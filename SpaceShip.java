//Name: Pedro Garate
//Date: 04/12/13

//***********************************************************************
//  SpaceShip.java    Author: Pedro Garate     Date: Apr. 12th, 2013
//
//  The program displays a ‘spaceship’ that follows the mouse and 
//  shoots ‘lasers’. In addition, the program keeps track of how many 
//  shots were fired. This ‘shot counter’ is controlled by a button 
//  that resets ‘shot counter’ to zero. There is another button that
//  controls if audio is played or not.
//  1. Creates the applet
//  2. Adds buttons.
//  3. Draws the spaceship 
//  4. Displays counter.
//***********************************************************************

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class SpaceShip extends Applet 
{
   private final int APPLET_WIDTH = 500;
   private final int APPLET_HEIGHT = 500;

   private Point point1 = null;
   private int x, y, counter = 0;
   private boolean fire, sound=false;
   
   private Button reset, soundSwitch;
   
   AudioClip soundFile;
   
   public void init()
   {
      addMouseListener(new MouseHandler());
      addMouseMotionListener(new MouseMotionHandler());
      
      soundFile = getAudioClip(getCodeBase(),"laser.wav"); 
      
      reset = new Button("Reset Shot Counter");
      reset.addActionListener(new ButtonListener());

      soundSwitch = new Button("Sound ON");
      soundSwitch.addActionListener(new ButtonListener());
      
      setBackground(Color.black);
      setSize(APPLET_WIDTH, APPLET_HEIGHT);
      add(soundSwitch);
      add(reset);
   }

   public void paint (Graphics page)
   {
	  page.setColor (Color.lightGray);
      page.fillArc(point1.x-25, point1.y-50, 50, 100, 225, 90);
      if (fire==true)
      {
    	  switch (counter%4)
    	  {
    	  	case 0: page.setColor(Color.blue);
    	  			break;
    	  	case 1: page.setColor(Color.yellow);
    	  			break;
    	  	case 2: page.setColor(Color.red);
    	  			break;
    	  	case 3: page.setColor(Color.green);
    	  			break;
    	  }
    	  x = (int)(Math.random()*APPLET_WIDTH);
    	  y = (int)(Math.random()*APPLET_HEIGHT);
    	  page.drawLine (point1.x, point1.y, x, y);
    	  counter++;
      }
      page.setColor (Color.white);
	  page.drawString("Shots " + Integer.toString(counter), 10, APPLET_HEIGHT-10);
   }

    public boolean isFire() 
    {
    	return fire;
    }

    public void setFire(boolean fire) 
    {
	    this.fire = fire;
    }

   private class MouseHandler implements MouseListener
   {
	   public void mouseClicked (MouseEvent event) 
	   {
		   if (sound==true)
			   soundFile.play();
		   fire=true;
		   repaint();
	   }

	   public void mousePressed (MouseEvent event) {}
	   public void mouseReleased (MouseEvent event) {}
	   public void mouseEntered (MouseEvent event) {}
	   public void mouseExited (MouseEvent event) {}
    }

   private class MouseMotionHandler implements MouseMotionListener
   {
	   public void mouseDragged (MouseEvent event) {}
	   public void mouseMoved (MouseEvent event) 
	   {
		   fire=false;
		   point1 = event.getPoint();
		   repaint();
	   }
   }
   
   private class ButtonListener implements ActionListener
   {
	   public void actionPerformed (ActionEvent event) 
	   {
		   if (event.getSource() == reset)
		   {
			   counter=0;
			   repaint();
		   }
		   else 
		   {
			   if (sound==false)
			   {
				   sound=true;
				   soundSwitch.setLabel("Sound OFF");
			   }			   		
			   else
			   {
				   sound=false;
				   soundSwitch.setLabel("Sound ON");			   
			   }
				   
		   }
	   }
   }
 } 
