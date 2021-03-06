/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hidenword.App.Network.Packet.In;

import hidenword.App.Game.GameService;
import hidenword.App.Game.Player;
import hidenword.App.Network.Packet.Out.JoinDuelGameAccept;
import hidenword.App.Network.Packet.Out.JoinDuelGameRefuse;
import hidenword.App.Network.Packet.PacketRegistryHandler;
import hidenword.App.Network.Session.Session;

/**
 * Packet when a Player want to join a duel game
 * @author Gaëtan Perrot, Barbaria
 */
final public class JoinDuelGame implements PacketRegistryHandler.PacketHandler {
    final private GameService service;
    
    public JoinDuelGame(GameService service) {
        this.service = service;
    }
    
    @Override
    public void handle(Session session, String packet) {
        String[] parts = packet.split(" ", 2);
        String gameIdSTR = parts[1];
        int gameId = Integer.parseInt(gameIdSTR);
        if(service.join(session, gameId)){
            JoinDuelGameAccept joinDuelGameAccept = new JoinDuelGameAccept(session);
            service.getGame(session).start();
            for(Player player : service.getGame(session).getPlayers()){
                player.getSession().write(joinDuelGameAccept);
            }
            //session.write(joinDuelGameAccept);
        }else{
            JoinDuelGameRefuse joinDuelGameRefuse = new JoinDuelGameRefuse(session);
            session.write(joinDuelGameRefuse);
        }
    }

    @Override
    public String code() {
        return "DUELJ";
    }   
}
