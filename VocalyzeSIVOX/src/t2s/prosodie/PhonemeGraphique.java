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
package t2s.prosodie;

import java.util.*;

/**
 * Classe qui definit un phoneme graphique
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class PhonemeGraphique {
	
	private String pho;
	private Vector<CoupleProsodieGraphique> prosodieGraphique = null;
	private int longueur;
	private int departX;
	private int incrementX;
	
	/**
	 * Constructeur par defaut de PhonemeGraphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param pho Le phoneme
	 */
	public PhonemeGraphique(String pho)
	{
		this.pho = pho;
		prosodieGraphique = new Vector<CoupleProsodieGraphique>();
		longueur = 0;
		departX = 0;
		incrementX = 0;
	}
	
	/**
	 * Constructeur par parametre
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param pho Le phoneme
	 * @param longueur La longueur
	 */
	public PhonemeGraphique(String pho, int longueur)
	{
		this.pho = pho;
		this.longueur = longueur;
		prosodieGraphique = new Vector<CoupleProsodieGraphique>();
		departX = 0;
		incrementX = 0;
	}
	
	/**
	 * Methode qui retourne le phoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le phoneme
	 */
	public String getPhoneme()
	{
		return(pho);
	}
	
	/**
	 * Methode qui modifie le phoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param s Le nouveau phoneme
	 */
	public void setPhoneme(String s)
	{
		if(s != null)
		{
			pho = s;
		}
	}
	
	/**
	 * Methode qui retourne le depart sur X
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return
	 */
	public int getDeprtX()
	{
		return(departX);
	}
	
	/**
	 * Methode qui modifie le depart sur X
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param departX Le nouveau depart
	 */
	public void setDepartX(int departX)
	{
		if(departX >= 0)
		{
			this.departX = departX;
		}
	}
	
	/**
	 * Methode qui retourne l increment sur X
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return L'increment sur X
	 */
	public int getIncrementX()
	{
		return(incrementX);
	}
	
	/**
	 * Methode qui modifie l increment sur X
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param incrementX Le nouvel increment
	 */
	public void setIncrmeentX(int incrementX)
	{
		if(incrementX >= 0)
		{
			this.incrementX = incrementX;
		}
	}
	
	/**
	 * Methode qui retourne la longueur du phoneme graphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La longueur du phoneme
	 */
	public int getLongueur()
	{
		return(longueur);
	}
	
	/**
	 * Methode qui modifie la longueur du phoneme graphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param l La nouvelle longueur
	 */
	public void setLongueur(int l)
	{
		if(l > 0)
		{
			longueur = l;
		}
	}
	
	/**
	 * Methode qui retourne la liste des couple pourcentage-frequence
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return un vecteur de CoupleProsodieGraphique
	 */
	public Vector<CoupleProsodieGraphique> getProsodie()
	{
		return(prosodieGraphique);
	}
	
	
	/**
	 * Methode qui ajoute un couple graphique en le triant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param p Le pourcentage
	 * @param f La frequence
	 * @param x La position X
	 * @param y La position Y
	 */
	public void ajouterCoupleGraphique(int p, int f, int x, int y)
	{
		CoupleProsodieGraphique cpg = null;
		if(prosodieGraphique != null)
		{
			int indice = 0;
			for(Iterator<CoupleProsodieGraphique> it = prosodieGraphique.iterator() ; it.hasNext() ; )
			{
				cpg = it.next();
				if(cpg.getPourcentage() < p)
				{
					indice++;
				}
				else
				{
					break;
				}
			}
			prosodieGraphique.add(indice, new CoupleProsodieGraphique(p, f, x, y));
		}
	}
	
	
	/**
	 * Methode qui supprime tous les couples graphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void supprimerAllCoupleGraphique()
	{
		if(prosodieGraphique != null)
		{
			prosodieGraphique.removeAllElements();
		}
	}
	
	
}
