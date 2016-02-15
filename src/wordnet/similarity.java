package wordnet;

import edu.sussex.nlp.jws.HirstAndStOnge;
import edu.sussex.nlp.jws.JWS;
import java.util.ArrayList;
import java.util.TreeMap;

public class similarity
{
	JWS	wordNet = new JWS("C:/Program Files (x86)/WordNet","2.1");
	HirstAndStOnge  hStOngeInstance = wordNet.getHirstAndStOnge();
	ArrayList adjList;
	ArrayList advAdjPairList;
	ArrayList advScoreList;
	ArrayList advAdjScoreGood = new ArrayList();
	ArrayList advAdjScoreBad = new ArrayList();
	ArrayList overallScoreGood = new ArrayList();
	ArrayList overallScoreBad = new ArrayList();
    

    similarity(ArrayList adj, ArrayList advAdj) throws Exception
    {
         this.adjList=adj;
         this.advAdjPairList=advAdj;
    }

    double calculatePositiveScore(ArrayList list,int size)
    {
        if(size == 0)
            return 0;
        adjective adjInstance;
        double sum = 0;
        double score;
        for(int i = 0; i<list.size(); i++)
        {
            adjInstance = (adjective) list.get(i);
            sum = (int) (sum + adjInstance.score);
        }
        score = sum / size;
        score = score / 16;
        return score;
    }

    adverb retrieveAdverb(String s)
    {
    	adverb advInstance;
        adverb tempAdverb = null;
        for(int i = 0; i<advScoreList.size(); i++)
        {
            advInstance = (adverb) advScoreList.get(i);
            if(advInstance.adverb.equals(s) == true)
            {
                tempAdverb.adverb = advInstance.adverb;
                tempAdverb.score = advInstance.score;
                tempAdverb.type = advInstance.type;
            }
        }
        return tempAdverb;
    }

    void assignScoresForAdj(){
        String s;
        ArrayList adjPositiveScores = new ArrayList();
        ArrayList adjNegativeScores = new ArrayList();
        TreeMap <String, Double> mapGood;
        TreeMap <String, Double> mapBad;
        double score;

        for(int i = 0; i < adjList.size(); i++){
        	adjPositiveScores.clear();
            s = (String) adjList.get(i);
            if(s.equals("bad") == true)
            {
                score = 0;
            }
            else
            {
            mapGood = hStOngeInstance.hso(s, "good", "a");
            for(String s1 : mapGood.keySet())
            {
                if(mapGood.get(s1) != 0)
                {
                    adjPositiveScores.add(new adjective(s1,mapGood.get(s1)));
                }
            }
            score = calculatePositiveScore(adjPositiveScores,adjPositiveScores.size());
            }
            overallScoreGood.add(new adjective(s,score));
            }

        for(int i = 0;i < adjList.size(); i++){
            adjNegativeScores.clear();
            s = (String)adjList.get(i);
            if(s.equals("good")){
                score = 0;
            }

            mapBad = hStOngeInstance.hso(s, "bad", "a");

            for(String s1 : mapBad.keySet()){
                if(mapBad.get(s1) != 0)
                {
                    adjNegativeScores.add(new adjective(s1,mapBad.get(s1)));
                }
            }
            score=calculatePositiveScore(adjNegativeScores,adjNegativeScores.size());
            overallScoreBad.add(new adjective(s,score));
        }
    }

    void assignScoreForAdvAdjPair(){
    	TreeMap<String, Double> mapGood;
        TreeMap<String, Double> mapBad;
        ArrayList adjPositiveScores = new ArrayList();
        ArrayList adjNegativeScores = new ArrayList();
        adverbAdjectivePair advAdjInstance;
        String s;
        scoredAdverbAdjectivePair scoredAdvAdjInstance;
        adverb advInstance;
        double score;
        for(int i=0;i<advAdjPairList.size();i++){
        	advAdjInstance = (adverbAdjectivePair) advAdjPairList.get(i);
            s = advAdjInstance.adjective;
            if(s.equals("bad")){
                score = 0;
            }
            else{
            	mapGood = hStOngeInstance.hso(s, "good", "a");
            	for(String s1 : mapGood.keySet()){
                    if(mapGood.get(s1) != 0){
                        adjPositiveScores.add(new adjective(s1,mapGood.get(s1)));
                    }
                }
                score = calculatePositiveScore(adjPositiveScores,adjPositiveScores.size());
            }
                advInstance = retrieveAdverb(advAdjInstance.adverb);
                advAdjScoreGood.add(new scoredAdverbAdjectivePair(advInstance.adverb,advAdjInstance.adjective,advInstance.score,score,advInstance.type));
        }
        for(int i = 0; i < advAdjPairList.size(); i++){
        	advAdjInstance = (adverbAdjectivePair) advAdjPairList.get(i);
            s = advAdjInstance.adjective;
            if(s.equals("good")){
                score = 0;
            }
            else{
            	mapBad = hStOngeInstance.hso(s, "good","a");
            	for(String s1 : mapBad.keySet()){
                    if(mapBad.get(s1) != 0){
                        adjNegativeScores.add(new adjective(s1,mapBad.get(s1)));
                    }
                }
                score = calculatePositiveScore(adjPositiveScores,adjNegativeScores.size());
            }
            advInstance = retrieveAdverb(advAdjInstance.adverb);
            advAdjScoreBad.add(new scoredAdverbAdjectivePair(advInstance.adverb,advAdjInstance.adjective,advInstance.score,score,advInstance.type));
        }
    }
    
    ArrayList consolidatedScore(){
    	if(overallScoreGood.size()==0)
            return new ArrayList(1);
        ArrayList overallAdjScore = new ArrayList();
        adjective adjInstance1 = null;
        adjective adjInstance2 = null;
        for(int i = 0; i < overallScoreGood.size(); i++){
        	adjInstance1 = (adjective) overallScoreGood.get(i);
            adjInstance2 = (adjective) overallScoreBad.get(i);
            overallAdjScore.add(i, new adjective(adjInstance1.adjective,adjInstance1.score-adjInstance2.score));
        }
        return overallAdjScore;
   }

    void consolidatedAdvAdjScores()
    {
        ArrayList consolidatedScoreList = new ArrayList();
        scoredAdverbAdjectivePair instance1;
        scoredAdverbAdjectivePair instance2;
        ArrayList finalScoreList = new ArrayList();
        for(int i = 0; i < overallScoreGood.size(); i++){
            instance1 = (scoredAdverbAdjectivePair) advAdjScoreGood.get(i);
            instance2 = (scoredAdverbAdjectivePair) advAdjScoreBad.get(i);
            consolidatedScoreList.add(i, new scoredAdverbAdjectivePair(instance2.adverb,instance2.adjective,instance2.adverbScore,(instance1.adjectiveScore-instance2.adjectiveScore),instance1.adverbType));
        }
        variableScore v = new variableScore();
        finalScoreList = v.compute(consolidatedScoreList);
    }
}
