package cn.ixan.search.java8;

import java.util.List;

public class TfldfCal {
    public double tf(List<String> doc,String term){
        double termFrequency = 0;
        for(String str:doc){
            if(str.equalsIgnoreCase(term)){
                termFrequency++;
            }
        }
        return termFrequency;
    }
}
