/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    @Override public boolean add(L vertex) {
        for(Vertex<L> vertex1:vertices){
            if(vertex1.getVertex()==vertex){
                return false;
            }
        }
        vertices.add(new Vertex<L>(vertex));
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        int length = 0,target_exist = 0,source_exist = 0;
        //首先判断目标点是否在图中,若在图中，则利用Vertex类中的set方法处理
        for(Vertex<L> vertex:vertices){
            if(vertex.getVertex().equals(target)){
                length = vertex.set(source,weight);
                target_exist = 1;
            }
            if(vertex.getVertex().equals(source))
            {
                source_exist = 1;
            }
            if(target_exist==1&&source_exist==1){
                break;
            }
        }

        //否则，目标点不在图中，若边长大于0，则在图中建立新点
        if(weight>0&&target_exist==0){
            Vertex<L> vertex = new Vertex<L>(target);
            length = vertex.set(source,weight);
            vertices.add(vertex);
        }
        //若起始点不在图中，且边长大于0，则建立新点
        if(weight>0&&source_exist==0){
            Vertex<L> vertex = new Vertex<L>(source);
            vertex.add_target(target);
            vertices.add(vertex);
        }
        return length;
    }

    /*
     *删点的时候记得分别删除该点作为目标点以及入边点集以及出边点集相应的记录
     */
    @Override public boolean remove(L vertex) {
        for(Vertex<L> target:vertices){
            if(target.getVertex().equals(vertex)){
                Map<L,Integer> sources = target.getSources();
                Set<L> sources_keyset = sources.keySet();
                for (Object source : sources_keyset) {
                    for (Vertex<L> vertex1 : vertices) {
                        if (vertex1.getVertex().equals((L) source)) {
                            vertex1.removeTarget((L) source);
                            break;
                        }
                    }
                }
                Set<L> targets = target.getTargets();
                for(L i:targets){
                    for(Vertex<L> vertex1:vertices){
                        if(vertex1.getVertex().equals(i)){
                            vertex1.set(vertex,0);
                            break;
                        }
                    }
                }
                vertices.remove(target);
                return true;
            }
        }
        return false;
    }
    
    @Override public Set<L> vertices() {
        Set<L> vertexs = new HashSet<>();
        for(Vertex<L> vertex:vertices){
            vertexs.add(vertex.getVertex());
        }
        return vertexs;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> sources = new HashMap<>();
        for(Vertex<L> vertex:vertices){
            if(vertex.getVertex().equals(target)){
                sources = vertex.getSources();
                break;
            }
        }
        return sources;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer> targets = new HashMap<>();
        Set<L> Target = new HashSet<>();
        for(Vertex<L> vertex:vertices){
            if(vertex.getVertex().equals(source)){
                Target = vertex.getTargets();
                break;
            }
        }
        for(L string:Target){
            for(Vertex<L> vertex:vertices){
                if(vertex.getVertex().equals(string)){
                    Map<L,Integer> map = vertex.getSources();
                    targets.put(string,map.get(source));
                    break;
                }
            }
        }
        return targets;
    }
    
    // TODO toString()
    /*
     * 根据Vertex类中的toString方法，按目标点依次输出即可
     */
    @Override
    public String toString(){
        StringBuilder tostring = new StringBuilder();
        for (Vertex<L> vertex:vertices){
            tostring.append(vertex.toString()).append("\n");
        }
        return tostring.toString();
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // TODO fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    /*
     *记录一个顶点
     * 及其入边对应的起始点及边长
     * 以及出边对应的目标点（方便remove)
     */
    private final L vertex;
    private final Map<L,Integer> sources;
    private final Set<L> targets;

    Vertex(L vertex) {
        this.vertex = vertex;
        this.sources = new HashMap<>();
        this.targets = new HashSet<>();
        checkRep();
    }

    /*
     *加入边,对于小于等于0的边应删去
     * 由于Graph类中的set方法需要返回先前的边值，因此Vertex类中的方法应返回先前的边值
     */
    public int set(L source,int weight){
        int previous = 0;
        if (this.sources.containsKey(source)){
            previous = this.sources.get(source);
            this.sources.remove(source);
        }
        if(source!=null&&weight>0){
            this.sources.put(source,weight);
        }
        return previous;
    }

    /*
     * 当该顶点作为起始点时，记录它的目标顶点
     */
    public void add_target(L target){
        this.targets.add(target);
    }

    // TODO checkRep
    /*
     *检查顶点及入边点非空以及边长非0
     */
    private void checkRep(){
        assert this.vertex!=null;
    }
    // TODO methods
    /**
     * get方法
     * 返回顶点及对应的入边点集
     * 返回目标点集
     */
    public L getVertex(){
        final L Vertex = vertex;
        return Vertex;
    }
    public Map<L,Integer> getSources(){
        final Map<L,Integer> Sources = sources;
        return Sources;
    }
    public Set<L> getTargets(){
        final Set<L> Targets = targets;
        return Targets;
    }

    /*
     * remove方法
     * 由于入边集可以通过添加边长为0的边来删去，因此只需设计目标点集的删除方法
     */
    public void removeTarget(L target){
        this.targets.remove(target);
    }

    // TODO toString
    /*
     *遍历入边集中所有顶点键值，依次输出顶点和边
     */
    @Override
    public String toString(){
        final L Vertex = vertex;
        final Map<L,Integer> Sources = sources;
        Set entryset = Sources.entrySet();
        Iterator iterator = entryset.iterator();
        String toString = "目标点为"+Vertex+": ";
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            toString = toString+entry.getKey()+"->"+Vertex+":"+entry.getValue()+" ";
        }
        return toString;
    }

}
