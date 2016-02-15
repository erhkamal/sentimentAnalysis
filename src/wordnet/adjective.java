package wordnet;

public class adjective
{
    String adjective;
    double score;
    
    adjective(String adjective, double score)
    {
        this.adjective = adjective;
        this.score = score;
    }
    
    adjective(adjective instance)
    {
        this.adjective = instance.adjective;
        this.score = instance.score;
    }
    
    public String toString()
    {
        return this.adjective + " " + this.score;
    }

}
