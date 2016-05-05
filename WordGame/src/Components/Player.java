/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import gui.MainForm;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import strategies.RandomlyRecursive;
import strategies.Strategy;
import wordgame.Title;
import wordgame.WordGame;

/**
 *
 * @author tifui
 */
public class Player implements Runnable{

    private Title[] curentTitles;
    private String name;
    private int score;
    private final WordGame game;
    private final JLabel label_nume;
    private final JLabel label_score;
    private final JLabel label_titles;
    private String curent_construction;
    private JLabel label_curent_construction;
    private String curentWord;
    private final  Strategy strategy;
    private JTextArea text_area;
    /**
     * Creaza un player nou.
     * @param numberofPlayer
     * @param name Numele playerului
     * @param game Jocul.
     */
    public Player(int numberofPlayer,String name,WordGame game)
    {
        
        this.name=name;
        this.game=game;
        MainForm game_ui=game.getGameUi();
        label_nume=(JLabel)game_ui.getLabel_namePlayer(numberofPlayer);
        label_score=(JLabel)game_ui.getLabel_scorePlayer(numberofPlayer);
        label_titles=(JLabel)game_ui.getLabel_titlesPlayer(numberofPlayer);
        label_nume.setText(name);
        label_curent_construction=game_ui.getLabel_curentPlayer(numberofPlayer);
        //JScrollPane panel=(JScrollPane)pane_player.getComponent(9);
        //strategy=new AutomatedStrategy(game.getGame_vocabuVocabulary(), this);
        strategy=new RandomlyRecursive(this.game.getGame_vocabuVocabulary(), this);
 
    }
    @Override
    public void run() {
        
        while(curentTitles!=null&&!game.getStoped())
        {
          
        
        curentWord=strategy.getWord(curentTitles,game);
        setScore(getScore());
        game.SubmitWord(this);
        setScore(getScore());
        getLabel_score().setText(getScore()+"");
        
        }
        
    }
    
    
    

    /**
     * @return rerturneaza numele jucatorului,
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returneaza literele curente de construire a unui cuvant
     * ca si string.
     * @param titles
     * @return 
     */
    public String getStringTitles(Title[] titles)
    {
        String res=new String();
        for (Title title : titles) {
            res += title.toString();
        }
        return res;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the curentWord
     */
    public String getCurentWord() {
        return curentWord;
    }

    /**
     * @return the label_curent_construction
     */
    public JLabel getLabel_curent_construction() {
        return label_curent_construction;
    }

    /**
     * @param label_curent_construction the label_curent_construction to set
     */
    public void setLabel_curent_construction(JLabel label_curent_construction) {
        this.label_curent_construction = label_curent_construction;
    }

    /**
     * @param curentWord the curentWord to set
     */
    public void setCurentWord(String curentWord) {
        this.curentWord = curentWord;
    }

    /**
     * @return the curentTitles
     */
    public Title[] getCurentTitles() {
        
        return curentTitles;
    }

    /**
     * @param curentTitles the curentTitles to set
     */
    public void setCurentTitles(Title[] curentTitles) {
        String str="";
        if(curentTitles==null)
        {
            this.curentTitles=null;
            return;
        }
        for(Title title:curentTitles)
            str+=title.toString();
        label_titles.setText(str);
        this.curentTitles = curentTitles;
    }

    /**
     * @return the label_score
     */
    public JLabel getLabel_score() {
        return label_score;
    }

    /**
     * @return the label_titles
     */
    public JLabel getLabel_titles() {
        return label_titles;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
        label_score.setText(this.score+"");
    }

    /**
     * @return the text_area
     */
    public JTextArea getText_area() {
        return text_area;
    }
    
}
