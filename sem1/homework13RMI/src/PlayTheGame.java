import java.rmi.RemoteException;
		
public interface PlayTheGame extends java.rmi.Remote
{
	String connected() throws RemoteException;
	void playTheGame() throws RemoteException;
	
	int getRound() throws RemoteException;
	int getChance() throws RemoteException;
	String getPredictedWord() throws RemoteException;
	void nextInputCharFromUser(char c) throws RemoteException;
	boolean checkPrediction() throws RemoteException;
	boolean wordPredicted() throws RemoteException;
	int getScore() throws RemoteException;
	public boolean gameIsOver() throws RemoteException;
	public void setWordToPredict() throws RemoteException;
	public String getWordToPredict() throws RemoteException;
	
	public boolean gameOverForAll() throws RemoteException;
	public String[] scoreBoard() throws RemoteException;
	
	
}