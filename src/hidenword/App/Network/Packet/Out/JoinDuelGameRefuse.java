/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hidenword.App.Network.Packet.Out;

import hidenword.App.Network.Session.Session;

/**
 * Answer of Packet when a Player want to start a duel game
 * Response for {@link JoinDuelGame}
 * @author Gaëtan Perrot, Barbaria
 */
final public class JoinDuelGameRefuse {
    final private Session session;  

    public JoinDuelGameRefuse(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "DUELRJ " + session;
    }
}