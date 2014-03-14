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
package t2s.exception;

import java.io.*;

/**
 * Classe generale des Exception de l'application SIVOX
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXException extends Exception {

	private static final long serialVersionUID = 1L;
	protected String messageErreur;
	protected String messageSonoreErreur;
	
	/**
	 * Constructeur par defaut de SIVOXException
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public SIVOXException()
	{
		messageErreur = "Erreur inconnue";
		messageSonoreErreur = "erreur inconnu";
	}
	
	/**
	 * Constructeur par parametre de SIVOXException
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param message Le message contenu dans l'exception
	 * @param messageSonore Le message en version sonore
	 */
	public SIVOXException(String message, String messageSonore)
	{
		messageErreur = message;
		messageSonoreErreur = messageSonore;
	}
	
	/**
	 * Methode de modification du message ecrit
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param message Le nouveau message de l'exception
	 */
	public void setMessage(String message)
	{
		messageErreur = message;
	}
	
	/**
	 * Methode de modification du message sonore
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param messageSonore Le nouveau message sonore
	 */
	public void setMessageSonore(String messageSonore)
	{
		messageSonoreErreur = messageSonore;
	}
	
	/**
	 * Methode de récuperation du message ecrit
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 * @return Le message contenu dans l'exception
	 */
	public String getMessage()
	{
		return(messageErreur);
	}
	
	/**
	 * Methode de récuperation du message sonore
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le message sonore contenu dans l'exception
	 */
	public String getMessageSonore()
	{
		return(messageSonoreErreur);
	}
	
	/**
	 * Methode toString
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 * @return Le message contenu dans l'exception
	 */
	public String toString()
	{
		return(messageErreur);
	}
	
	/**
	 * Methode PrintStackStrace
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace()
	 */
	public void printStackTrace()
	{
		System.out.println("Erreur SIVOX : "+messageErreur);
	}
	
	/**
	 * Methode printStackTrace(PrintStream)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
	 */
	public void printStackTrace(PrintStream s)
	{
		s.println("Erreur SIVOX : "+messageErreur);
	}
	
	/**
	 * Methode printStackTrace(PrintWriter)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
	 */
	public void printStackTrace(PrintWriter w)
	{
		w.println("Erreur SIVOX : "+messageErreur);
	}
	
	/**
	 * Methode clone
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 * @return Une copie de l'exception courante
	 */
	public Object clone()
	{
		return(new SIVOXException(messageErreur, messageSonoreErreur));
	}
}
