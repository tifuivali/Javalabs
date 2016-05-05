/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

import Components.Player;
import dictionar.TreeVocabulary;
import dictionar.Vocabulary;
import gui.MainForm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tifui
 */
public class WordGame implements Runnable{
    private int numberOfPlayers;
    private LeterBag leterBag;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Thread[] playerTreads;
    private JTable table_Titles;
    private Vocabulary game_vocabuVocabulary;
    private Player[] players;
    MainForm game_ui;
    TimeKeeper timeKeeper;
    private int dificulty;
    private boolean stoped=false;
    /**
     *Creaza un nou obiect WordGame primind ca 
     * parametru numarul jucatorilor
     * @param game_ui
     * @param numberOfPlayers Numarul de jucatori
     * @param table
     * @param player1
     * @param player2
     * @param player3
     * @param player4
     * @param dificulty
     */
    public WordGame(MainForm game_ui,int numberOfPlayers,JTable table,JPanel player1,JPanel player2,JPanel player3,JPanel player4,int dificulty)
    {
        loadDictionaryFromFile("words.in",dificulty);
        this.leterBag=new LeterBag();
        this.game_ui=game_ui;
        table_Titles=table;
        this.numberOfPlayers=numberOfPlayers;
        this.player1=new Player(1,"Player1", this);
        this.player1.setCurentTitles(leterBag.getSevenRandomTitles());
        RefreshTableTitles();
        this.player2=new Player(2, "Player2", this);
        this.player2.setCurentTitles(leterBag.getSevenRandomTitles());
        RefreshTableTitles();
        this.player3 = new Player(3,"Player3", this);
        this.player3.setCurentTitles(leterBag.getSevenRandomTitles());
        RefreshTableTitles();
        this.player4=new Player(4,"Player4", this);
        this.player4.setCurentTitles(leterBag.getSevenRandomTitles());
        RefreshTableTitles();
        playerTreads=new Thread[4];
        playerTreads[0]=new Thread(this.player1);
        playerTreads[1]=new Thread(this.player2);
        playerTreads[2]=new Thread(this.player3);
        playerTreads[3]=new Thread(this.player4);
        players=new Player[4];
        players[0]=this.player1;
        players[1]=this.player2;
        players[2]=this.player3;
        players[3]=this.player4;
        timeKeeper=new TimeKeeper(game_ui.getTileLabel());
        this.dificulty=dificulty;
        
       
    }
    
    public void StopGame()
    {
        
        for(Thread thread :playerTreads)
           {
               thread.interrupt();
           }
        stoped=true;
        timeKeeper.Stop();
        
    }
    
    public WordGame(MainForm game_ui,int numberOfPlayers,JTable table_Titles,JPanel player1,JPanel player2,JPanel player3,int dificulty)
    {
        loadDictionaryFromFile("words.in",dificulty);
        this.table_Titles=table_Titles;
        this.game_ui=game_ui;
        this.leterBag=new LeterBag();
        this.numberOfPlayers=numberOfPlayers;
        this.player1=new Player(1,"Player1", this);
        this.player1.setCurentTitles(leterBag.getSevenRandomTitles());
        RefreshTableTitles();
        this.player2=new Player(2, "Player2", this);
        this.player2.setCurentTitles(leterBag.getSevenRandomTitles());
        RefreshTableTitles();
        this.player3 = new Player(3,"Player3", this);
        this.player3.setCurentTitles(leterBag.getSevenRandomTitles());
        RefreshTableTitles();
        playerTreads=new Thread[3];
        playerTreads[0]=new Thread(this.player1);
        playerTreads[1]=new Thread(this.player2);
        playerTreads[2]=new Thread(this.player3);
        players=new Player[3];
        players[0]=this.player1;
        players[1]=this.player2;
        players[2]=this.player3;
        timeKeeper=new TimeKeeper(game_ui.getTileLabel());
        this.dificulty=dificulty;
    }
    
       public WordGame(MainForm game_ui,int numberOfPlayers,JTable table_titles,JPanel player1,JPanel player2,int dificulty)
    {
        loadDictionaryFromFile("words.in",dificulty);
        
        this.table_Titles=table_titles;
        this.game_ui=game_ui;
        this.leterBag=new LeterBag();
        this.numberOfPlayers=numberOfPlayers;
        this.player1=new Player(1,"Player1", this);
        this.player1.setCurentTitles(leterBag.getSevenRandomTitles());
        this.player2=new Player(2, "Player2", this);
        this.player2.setCurentTitles(leterBag.getSevenRandomTitles());
        playerTreads=new Thread[2];
        playerTreads[0]=new Thread(this.player1);
        playerTreads[1]=new Thread(this.player2);
        players=new Player[2];
        players[0]=this.player1;
        players[1]=this.player2;
        timeKeeper=new TimeKeeper(game_ui.getTileLabel());
        this.dificulty=dificulty;
        
    }
    
    
    private boolean available=true;
    
    public void FinishGame()
    {
        stoped=true;
    }
    
    public MainForm getGameUi()
    {
        return game_ui;
    }
    
    public synchronized void SubmitWord(Player player)
    {
        while(!available)
        {           
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(WordGame.class.getName()).log(Level.SEVERE, null, ex);
                }   
        }
        available=false;
        //cod sincronizat
       
        player.setScore(player.getScore()+player.getCurentWord().length()*2);
        
        System.out.println(player.getName()+" Gasit: "+player.getCurentWord());
        if(player.getCurentTitles()!=null)
        game_ui.getTextWordsCreated().append(player.getName()+"->"+player.getCurentWord()+"\r\n "
                               + "Tiles:"+player.getStringTitles(player.getCurentTitles())+"\r\n");
         Title[] newTitles=leterBag.getSevenRandomTitles();
        RefreshTableTitles();
        System.out.println("new new "+newTitles.length);
       if(newTitles.length<=dificulty)
       { 
           this.stoped=true;
         
           timeKeeper.Stop();
           FinishGame();
           for(Player play:players)
           {
               play.setCurentTitles(null);
           }
           
           game_ui.getInitButton().setEnabled(true);
           game_ui.getDifiSpinner().setEnabled(true);
           game_ui.getPlayersSpinner().setEnabled(true);
           game_ui.getStopButton().setEnabled(false);
           int[] scores=new int[players.length];
           Arrays.fill(scores, 0);
           for(int i=0;i<scores.length;i++)
           {
               scores[i]=players[i].getScore();
           }
           Arrays.sort(scores);
           int k=0;
           System.out.println(scores.length);
           for(int i=scores.length-1;i>=0;i--)
           {
               System.out.println(scores[i]);
               game_ui.getLabelsClasament()[k++].setText(getPlayerByScore(scores[i]).getName()+"->"+scores[i]);
           }
         
       }
       else    
            player.setCurentTitles(newTitles);
       
        available=true;
        notifyAll();
    }
    
    
    
    private void RefreshTableTitles()
    {
        DefaultTableModel model=(DefaultTableModel) table_Titles.getModel();
        
        for(int i=0;i<model.getRowCount();i++)
            model.removeRow(i);
        setLettersOntable(model,leterBag.getLeterDistribution());
    }
   
     /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
    }

    /**
     * Returneaza numarul de jucatori.
     * @return the numberOfPlayers
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    /**
     * Incarcarea dictionarului de cuvunte din fisier.
     * @param String file 
     */
    private void loadDictionaryFromFile(String filePath,int dificulty)
    {
        List<String> words=new ArrayList<>();
        
       
        
        try {
            File file=new File(filePath);
            Scanner scan=new Scanner(file);
            while(scan.hasNextLine())
            {
               String word=scan.nextLine();
               if(word.length()>=dificulty)
                 words.add(word.toUpperCase());
            }
            Collection<String> vocabulary=words;
             game_vocabuVocabulary=new TreeVocabulary(vocabulary);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
     private void setLettersOntable(DefaultTableModel model,char[][] letterDist)
    {
        for(int i=0;i<model.getRowCount();i++)
            model.removeRow(i);
       
        for (char[] letterRow : letterDist) {
           
            String[] strings=new String[letterDist.length];
            for(int i=0;i<letterRow.length;i++)
            {
                strings[i]=""+letterRow[i];
            }
            model.addRow(strings);
           
           
        }
    }

    /**
     * Seteaza numarul de jucatori
     * @param numberOfPlayers the numberOfPlayers to set
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    
    
   
    
    @Override
    public void run() {
        System.out.println("numar jucatori "+numberOfPlayers);
        timeKeeper.Start();
        for(int i=0;i<numberOfPlayers;i++)
        {       
            getPlayerTreads()[i].start();
        }
        
    }
    
    public synchronized boolean  getStoped()
    {
        return this.stoped;
    }
    
    private Player getPlayerByScore(int score)
    {
        if(player1.getScore()==score)
            return player1;
        if(player2.getScore()==score)
            return player2;
        if(player3.getScore()==score)
            return player3;
        if(player4.getScore()==score)
            return player4;
        return null;
    }
    


    /**
     * @return the leterBag
     */
    public LeterBag getLeterBag() {
        return leterBag;
    }

    /**
     * @return the game_vocabuVocabulary
     */
    public Vocabulary getGame_vocabuVocabulary() {
        return game_vocabuVocabulary;
    }

    /**
     * @return the playerTreads
     */
    public Thread[] getPlayerTreads() {
        return playerTreads;
    }
    
}
