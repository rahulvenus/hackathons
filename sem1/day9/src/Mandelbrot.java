/* 
 * Mandelbrot.java 
 * 
 * @version: $Id: Mandelbrot.java,v 1.70 2015/10/26 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/10/26 12:00:00 
 */

import java.awt.Color;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import javax.net.ssl.ManagerFactoryParameters;
import javax.swing.JFrame;

/* ThreadManager class for managing the threads created
 * 
 */
class ThreadManager extends Thread{
	
	static int x = 0;
	static int y = 0;
	static int width;
	static int height;
	
	private BufferedImage theImage;
	static Mandelbrot mandelbrot;
	int numMaxThreads;
	private PixelThread[] pixelThread;
	static boolean isTaskPend = true;
	public ThreadManager(Mandelbrot M ,BufferedImage image,int bufferWidth,int bufferHeight){
		mandelbrot = M;
		theImage = image;
		width = bufferWidth;
		height = bufferHeight;
		numMaxThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("numMaxThreads :"+numMaxThreads);
        pixelThread = new PixelThread[numMaxThreads];
	}
	
	@Override
	public void run() {
		
		for(int i = 0; i< numMaxThreads;i++){
			pixelThread[i] = new PixelThread(this,theImage);
			pixelThread[i].start();
			
		}

	}
	
	public boolean isTaskPending(){

		return isTaskPend;
	}
	
	public static synchronized int getX(){
		int oldX;
		oldX = x;
		if(y<height){
			if(x<width){
				x++;
				if(x == width){
					x =0;
					y++;
					mandelbrot.repaint();
				}
				if(y == height){
					isTaskPend = false;
					y = -1;
					return -1;
				}
					
			}		
		}
		
		return oldX;
	}
	
	public static int getY(){

		return y;
	}
	
}

/* PixelThread class for managing each thread and drawing each pixel
 * 
 */
class PixelThread extends Thread{
	
    private final int MAX 	= 5000;
    private final int LENGTH   	= 800;
    private final double ZOOM  	= 1000;
	private BufferedImage theImage;
	private int x;
	private int y;
	private ThreadManager managerThread;
	
	public PixelThread(ThreadManager T , BufferedImage I ) {
		managerThread = T;
		theImage = I;
	}
	
	
	@Override
	public void run() {
		//System.out.println(" started the task");
		while(true){
			
			synchronized (this) {
				if(managerThread.isTaskPending()){
					//System.out.println(" Doing the task");
					x = managerThread.getX();
					y = managerThread.getY();
					
					if((x == -1) || ( y== -1)){
						try {

							this.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					//System.out.println(" Doing the task x ="+x+" y="+y + " height = "+ThreadManager.height+"width = "+ThreadManager.width);
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
			        	theImage.setRGB(x, y, managerThread.mandelbrot.colors[iter]);			// this is the part for the parallel part
			        else								// this is the part for the parallel part
			        	theImage.setRGB(x, y, iter | (iter << 8));		// this is the part for the parallel part
	
					
				}
				else{
					try {
						this.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(managerThread.isAlive())
						try {
							managerThread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}
		}

	}
}

//Main class 
public class Mandelbrot extends JFrame {
 
    private final int MAX 	= 5000;
    private final int LENGTH   	= 800;
    private final double ZOOM  	= 1000;
    private BufferedImage theImage;
    public int[] colors = new int[MAX];
    private ThreadManager threadManager;
 
    public Mandelbrot() {
        super("Mandelbrot Set");
	
	initColors();
        setBounds(100, 100, LENGTH, LENGTH);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void createSet()	{
        theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        threadManager = new ThreadManager(this,theImage,getWidth(), getHeight());
        threadManager.start();
        
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
    }
}