/* 
 * Mandelbrot.java 
 * 
 * @version: $Id: Mandelbrot.java,v 1.70 2015/10/19 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/10/19  12:00:00 
 */


// original from: http://rosettacode.org/wiki/Mandelbrot_set#Java
// modified for color

import java.awt.Color;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;



 
public class Mandelbrot extends JFrame {
 
    private final int MAX 	= 5000;
    private final int LENGTH   	= 800;
    private final double ZOOM  	= 1000;
    private BufferedImage theImage;
    private int[] colors = new int[MAX];
    private int processors;
    private int curThreadCount = -1;
    private pixThread[] pixelThread;
    private int numMinThreads = 0;
    private int numMaxThreads = 0;
    
    
    
    //Class pixThread creates new thread and based on request 
    //process each pixel in individual threads
    private class pixThread implements Runnable{
    	public Thread myThread;
    	private String threadName;
    	int x;
    	int y;
    	BufferedImage theImage;

    	public pixThread(int count) {
    		threadName = Integer.toString(count);
		}
    	
		@Override
		public void run() {
			//System.out.println("Enter run()");
			double zx, zy, cX, cY;
			zx = zy = 0;
            cX = (x - LENGTH) / ZOOM;
			cY = (y - LENGTH) / ZOOM;
			int iter = 0;
			double tmp;
			
	        while ( (zx * zx + zy * zy < 10 ) && ( iter < MAX - 1 ) ) {	// this is the part for the parallel part
                tmp = zx * zx - zy * zy + cX;				// this is the part for the parallel part
                zy = 2.0 * zx * zy + cY;					// this is the part for the parallel part
                zx = tmp;							// this is the part for the parallel part
                iter++;							// this is the part for the parallel part
            }								// this is the part for the parallel part
	        if ( iter > 0 )							// this is the part for the parallel part
	        	theImage.setRGB(x, y, colors[iter]);			// this is the part for the parallel part
	        else								// this is the part for the parallel part
	        	theImage.setRGB(x, y, iter | (iter << 8));		// this is the part for the parallel part

			
			
		}
		
		
		public void start(BufferedImage bI,int x, int y){
			if(myThread == null){
				//System.out.println("Going to create new thread "+ threadName);
				myThread = new Thread (this);
				//System.out.println("Going to create new thread");

			}
			else{
			/*	try {
					myThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
				
				//wait until the thread is alive and breaks if not
				while(myThread.isAlive()){
					//System.out.println("is alive");
				}
				//System.out.println("dead,so creating new");
				
				myThread = new Thread (this);
				//myThread.
			}
			//System.out.println("Going to trigger start");
			theImage = bI;
			this.x = x;
			this.y = y;
			
			
			myThread.start ();
		}
	}
    
    
    
    public Mandelbrot() {
        super("Mandelbrot Set");
        processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors :"+processors);
        numMaxThreads = processors;
        pixelThread = new pixThread[numMaxThreads];
	initColors();
        setBounds(100, 100, LENGTH, LENGTH);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    public void createSet()	{
        theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
            	if(curThreadCount == -1){
        			//System.out.println("Triggering thread 0");
        			++curThreadCount;
        			pixelThread[curThreadCount] = new pixThread(curThreadCount);
        			pixelThread[curThreadCount].start(theImage,x,y);
        			numMinThreads = 0;
        			//System.exit(1);
        		}
        		else{
        			if(curThreadCount == numMaxThreads - 1){
        				//System.out.println("Triggering thread 0");
        				curThreadCount = 0;
        				pixelThread[curThreadCount].start(theImage,x,y);
        			}
        			else{
        				curThreadCount++;
        				//System.out.println("Triggering thread "+curThreadCount);
        				if(pixelThread[curThreadCount] == null){
        					pixelThread[curThreadCount] = new pixThread(curThreadCount);
        					numMinThreads++;
        				}
        				pixelThread[curThreadCount].start(theImage,x,y);
        			}
        		}
		        }
            //wait for all threads to finish
        	for(int i =0;i <= numMinThreads ; i++){
        		try {
        			//System.out.println("Waiting for thread "+i+" to finsih");
        			pixelThread[i].myThread.join();
        		} catch (InterruptedException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	}
            repaint();
        }
    }
    public void initColors() {
        for (int index = 0; index < MAX; index++) {
            colors[index] = Color.HSBtoRGB(index/256f, 1, index/(index+8f));
        }
    }
 
    @Override
    public void paint(Graphics g) {
        g.drawImage(theImage, 0, 0, this);
    }
 
    public static void main(String[] args) {
	    Mandelbrot aMandelbrot = new Mandelbrot();
		aMandelbrot.setVisible(true);
		aMandelbrot.createSet();
		System.out.println("EXIT");
    }
}
