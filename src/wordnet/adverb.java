package wordnet;

public class adverb
{
    String adverb;
    double score;
    String type;
    
    public adverb(){
    	this.adverb = "";
    	this.score = 0.0;
    	this.type = "";
    }
    
    adverb(String adverb,double score)
    {
        this.adverb = adverb;
        this.score = score;
    }

}
