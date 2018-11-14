/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hidenword.Model.Game.GameFactory;

import hidenword.Model.Game.Game;

/**
 * GameFactory is an Interface to create the correct Game Mode
 * @author Gaëtan
 */
public interface GameFactory {
    public Game create();
}