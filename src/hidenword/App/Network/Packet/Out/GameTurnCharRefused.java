/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hidenword.App.Network.Packet.Out;

import hidenword.App.Network.Session.Session;

/**
 *
 * @author Gaëtan
 */
public class GameTurnCharRefused {
    final private Session session;
    private final char c;
    

    public GameTurnCharRefused(Session session,char c) {
        this.session = session;
        this.c = c;
    }

    @Override
    public String toString() {
        return "GTCRR " + c;
    }
}
