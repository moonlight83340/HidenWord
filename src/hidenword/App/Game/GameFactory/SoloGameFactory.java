/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hidenword.App.Game.GameFactory;

import hidenword.App.Game.Game;
import hidenword.App.Game.GameStrategy.SoloTurnStrategy;

/**
 * Create the Solo Game Mode with the strategy 
 * @author Gaëtan Perrot, Barbaria
 */
public class SoloGameFactory implements GameFactory{

    /**
     * Create a new Solo Game
     * @return Game
     */
    @Override
    public Game create() {
        return new Game(new SoloTurnStrategy());
    }
    
}
