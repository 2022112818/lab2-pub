/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import P1.graph.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(corpus));
        String strings = "";
        String strings1 = "";
        String vertex[] = {};
        String vertex1[] = {};
        while ((strings = reader.readLine())!=null){
            strings1 = strings;
            strings.replaceAll("\\pP"," ");
            strings.replaceAll("\\pP",", ");
            vertex = strings.split(" ");
            vertex1 = strings1.split(" ");
            for(int i = 0;i<vertex.length;i++){
                graph.add(vertex[i].toLowerCase());              //将字符串中的字母均转换为小写
                if(i<(vertex.length-1)&& vertex1[i].equalsIgnoreCase(vertex[i])){              //我们认为，连续两个有联系的词间不应该有标点符号
                    int weight = graph.set(vertex[i].toLowerCase(),vertex[i+1].toLowerCase(),1);
                    if(weight>0){
                        graph.set(vertex[i].toLowerCase(),vertex[i+1].toLowerCase(),weight+1);
                    }
                }
            }
        }
        checkRep();
    }
    
    // TODO checkRep
    /*
     * 检查图非空
     * 检查各个顶点不为null
     * 检查各个顶点的sources与targets的顶点和边
     */
    private void checkRep(){
        assert this.graph!=null;
        for(String vertex:graph.vertices()){
            assert vertex!=null;
            Set<Map.Entry<String, Integer>> entroyset = graph.sources(vertex).entrySet();
            for (Object o : entroyset) {
                Map.Entry entry = (Map.Entry) o;
                assert entry.getValue() != "0";
                assert entry.getKey() != null;
            }
            Set entroyset1 = graph.targets(vertex).entrySet();
            Iterator iterator1 = entroyset1.iterator();
            while (iterator1.hasNext()){
                Map.Entry entry = (Map.Entry)iterator1.next();
                assert entry.getValue()!="0";
                assert entry.getKey()!=null;
            }
        }
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String poem = "";
        String input1 = input;
        String input2 = input1;
        input1.replaceAll("//pP"," ");
        String vertex1[] = input1.split(" ");
        String vertex2[] = input2.split(" ");
        for (int i = 0;i<vertex1.length-1;i++){
            poem = poem+vertex2[i]+" ";
            if(Objects.equals(vertex2[i], vertex1[i]) && graph.vertices().contains(vertex1[i].toLowerCase())){
                Set<String> targets = graph.targets(vertex1[i].toLowerCase()).keySet();
                Set<String> sources = graph.sources(vertex1[i+1].toLowerCase()).keySet();
                int bridge_length = 0;
                int max = 0;
                String bridge_vertex = null;
                for (String target:targets){
                    if(sources.contains(target)){
                        bridge_length = graph.targets(vertex1[i].toLowerCase()).get(target)+graph.sources(vertex1[i+1].toLowerCase()).get(target);
                    }
                    if(bridge_length>max){
                        max = bridge_length;
                        bridge_vertex = target;
                    }
                }
                if(bridge_vertex!=null){
                    poem = poem+bridge_vertex+" ";
                }
            }
        }
        if(vertex1.length>0){
            poem = poem+vertex2[vertex2.length-1];
        }
        return poem;
    }
    
    // TODO toString()
    public String toString(){
        return graph.toString();
    }
    
}
