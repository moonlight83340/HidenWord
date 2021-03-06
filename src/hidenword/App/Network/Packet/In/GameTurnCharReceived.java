/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hidenword.App.Network.Packet.In;

import hidenword.App.Game.GameService;
import hidenword.App.Game.Player;
import hidenword.App.Game.Player.PlayerGameState;
import hidenword.App.Network.Packet.Out.GameLost;
import hidenword.App.Network.Packet.Out.GameTurnCharAccepted;
import hidenword.App.Network.Packet.Out.GameTurnCharRefused;
import hidenword.App.Network.Packet.Out.GameWin;
import hidenword.App.Network.Packet.Out.NextTurn;
import hidenword.App.Network.Packet.Out.WaitTurn;
import hidenword.App.Network.Packet.PacketRegistryHandler;
import hidenword.App.Network.Session.Session;
import java.util.List;

/**
 * Packet when a Player send a Char after play his turn
 * @author Gaëtan Perrot, Barbaria
 */
final public class GameTurnCharReceived implements PacketRegistryHandler.PacketHandler {
    final private GameService service;
    
    public GameTurnCharReceived(GameService service) {
        this.service = service;
    }
    
    @Override
    public void handle(Session session, String packet) {
        String[] parts = packet.split(" ", 2);
        String receivedChar = parts[1];
        char c = receivedChar.charAt(0);
        Player player = service.getPlayer(session);
        player.setCurrentChar(c);
        if(service.getGame(session).nextTurn()){
            List<Player> players = player.getGame().getPlayers();
            if(player.getPlayerGameState() == PlayerGameState.RUN){
                GameTurnCharAccepted gameTurnCharAccepted = new GameTurnCharAccepted(session,player.getSearchWord().toString());
                session.write(gameTurnCharAccepted);
                if(players.size() == 2){
                    WaitTurn waitTurn = new WaitTurn(session,player.getSearchWord().toString());
                    session.write(waitTurn);
                    for(Player playerGame : players){
                        if(!playerGame.equals(player)){
                            NextTurn nextTurn = new NextTurn(playerGame.getSession(),playerGame.getSearchWord().toString());
                            playerGame.getSession().write(nextTurn);
                        }
                    }
                }else{
                    NextTurn nextTurn = new NextTurn(session,player.getSearchWord().toString());
                    session.write(nextTurn);
                }
            }else if (player.getPlayerGameState() == PlayerGameState.WIN){
                GameWin gameWin = new GameWin(session,player.getSearchWord().toString());
                session.write(gameWin);
                if(players.size() == 2){
                    for(Player playerGame : players){
                        if(!playerGame.equals(player)){
                            GameLost gameLost = new GameLost(playerGame.getSession());
                            playerGame.getSession().write(gameLost);
                            service.remove(playerGame.getSession());
                        }
                    }
                }
                service.remove(session);
            }else if(player.getPlayerGameState() == PlayerGameState.LOST){
                GameLost gameLost = new GameLost(session);
                session.write(gameLost);
                service.remove(session);
            } 
        }else{
            GameTurnCharRefused gameTurnCharRefused = new GameTurnCharRefused(session,c);
            session.write(gameTurnCharRefused);
            List<Player> players = player.getGame().getPlayers();
            if(players.size() == 2){
                for(Player playerGame : players){
                    if(!playerGame.equals(player)){
                        NextTurn nextTurn = new NextTurn(playerGame.getSession(),playerGame.getSearchWord().toString());
                        playerGame.getSession().write(nextTurn);
                    }
                }
            }
        } 
    }

    @Override
    public String code() {
        return "GTCR";
    }   
}