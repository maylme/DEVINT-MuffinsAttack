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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;

/**
 * Classe Audio Concat
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class AudioConcat {

	/**
	 * Constructeur par defaut
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param travail L'ArrayList de travail
	 */
    public AudioConcat (ArrayList travail) {

	String	strOutputFilename = "discours.wav";
	AudioFormat audioFormat = null;
	List<AudioInputStream> audioInputStreamList = new ArrayList<AudioInputStream>();
	String	strFilename = null;
	for (int i = 0; i < travail.size(); i++)
	{
	    strFilename = (String)travail.get(i);
	    File soundFile = new File(strFilename);
	    AudioInputStream  audioInputStream = null;
	    try { audioInputStream =  AudioSystem.getAudioInputStream(soundFile);
	    } catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	    }
	    AudioFormat format = audioInputStream.getFormat();
	    // The first input file determines the audio format.
	    if (audioFormat == null){audioFormat = format;}
	    else if ( ! audioFormat.matches(format)){
		    out("AudioConcat.main(): WARNING: AudioFormats don't match");
		    out("AudioConcat.main(): master format: " + audioFormat);
		    out("AudioConcat.main(): this format: " + format);
	    }
	    audioInputStreamList.add(audioInputStream);
	}
	if (audioFormat == null) {
	    out("No input filenames!");
	    printUsageAndExit();
	}
	AudioInputStream audioInputStream = null;
	audioInputStream =  new SequenceAudioInputStream(audioFormat, 
					 audioInputStreamList);

	File outputFile = new File(strOutputFilename);
	try {
	    AudioSystem.write(audioInputStream, 
			      AudioFileFormat.Type.WAVE, outputFile);
	} catch (IOException e) {e.printStackTrace();}
    }
    
    /**
     * Methode d affichage d'usage
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    private static void printUsageAndExit()
    {
	out("AudioConcat: usage:");
	out("	java AudioConcat ...");
	out("the first file determines the audio format.");
	System.exit(1);
    }
    
    /**
     * Methode de sortie de message
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param strMessage Le message
     */
    private static void out(String strMessage)
    {
    	System.out.println(strMessage);
    }
    
    /**
     * Un executable
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param args La liste des arguments
     */
    public static void main(String[] args) {
		ArrayList<String> travail= new ArrayList<String>();
		travail.add("lea.wav");
		travail.add("theo.wav");
		out("nombre : " + travail.size());
		for (int i=0;i<travail.size();i++) out("\n"+travail.get(i));
    }
}
