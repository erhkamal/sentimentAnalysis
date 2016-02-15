package wordnet;

import java.io.*;
import java.net.MalformedURLException;

public class Main
{
    public static void s1() throws MalformedURLException, FileNotFoundException, IOException,Exception
    {
        //TODO code application logic here
    	stopWords sWinstance = new stopWords();
    	sWinstance.readStopWords();
    	sWinstance.removeStopWords();
    	POSTag posTagInstance = new POSTag();
    	posTagInstance.tagPOS();
    }
}