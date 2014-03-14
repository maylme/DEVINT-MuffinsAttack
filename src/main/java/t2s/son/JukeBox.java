/*
* ﻿Copyright 2004-2007, Christian BREL, Hélène COLLAVIZZA, Sébastien MOSSER, Jean-Paul STROMBONI,
* 
* This file is part of project 'VocalyzeSIVOX'
* 
* 'VocalyzeSIVOX' is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* 'VocalyzeSIVOX'is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with 'VocalyzeSIVOX'. If not, see <http://www.gnu.org/licenses/>.
*/

package t2s.son;

import java.applet.*;
import java.net.*;

/**
 * Classe permettant de jouer un fichier son au format wave
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class JukeBox {

    private AudioClip ac ;
    
    /** 
     * Construit un JuxeBox pour un fichier donne
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param s le chemin d'acces au fichier
     */
    public JukeBox(String s)
    {
    	ac = null;
    	try{
    		URL u = new URL("file:"  + s);
    		ac = Applet.newAudioClip(u);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Methode pour lire le fichier contenu dans le jukebox
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    public void playSound()
    {
    	if (ac != null)
    	{
    		try {
    			ac.stop();
    			ac.play();
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    }
    
    /**
     * Methode qui lit en boucle
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    public void loopSound()
    {
    	if (ac != null)
    	{
    		try {
    			ac.stop();
    			ac.loop();
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    }
    
    /**
     * Methode qui stoppe la lecture
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    public void stop()
    {
    	if (ac != null)
    	{
    		try {
    			ac.stop();
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    }
}
