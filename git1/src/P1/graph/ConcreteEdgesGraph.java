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
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    @Override public boolean add(L vertex) {
        if(vertices.contains(vertex)){
            return false;
        }else {
            vertices.add(vertex);
            return true;
        }
    }
    
    @Override public int set(L source, L target, int weight) {

        //遍历边集是否有该边，如果有，则先删除该边（边是不可变的）
        int previous_weight = 0;
        Iterator<Edge<L>> iterator = edges.iterator();
        while (iterator.hasNext()){
            Edge<L> bian = iterator.next();
            if(bian.getSource().equals(source)&&bian.getTarget().equals(target)){
                previous_weight = bian.getWeight();
                iterator.remove();
            }
        }

        //如果新加入的边不为0，则将其加入边集
        if(weight>0){
            //如果起始点和目标点不在图中，则将其加入
            if(vertices.contains(source)){
            }else{
                vertices.add(source);
            }
            if (vertices.contains(target)){
            }else{
                vertices.add(target);
            }

            edges.add(new Edge(source,target,weight));
        } else if (weight<0) {
            throw new RuntimeException("输入不合法！");
        }
        return previous_weight;
    }
    
    @Override public boolean remove(L vertex) {
        //判断该顶点是否在图中
        if(!vertices.contains(vertex)){
            return false;
        }
        vertices.remove(vertex);          //移除该顶点

        //移除带有该顶点的边
        Iterator<Edge<L>> iterator = edges.iterator();
        while (iterator.hasNext()){
            Edge bian = iterator.next();
            if(bian.getSource().equals(vertex)||bian.getTarget().equals(vertex)){
                iterator.remove();
            }
        }
        return true;
    }

    @Override public Set<L> vertices() {
        return new HashSet<>(vertices);
    }

    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> sources = new HashMap<>();
        for(Edge<L> iterater:edges){
            if(iterater.getTarget().equals(target)){
                sources.put((L) iterater.getSource(),iterater.getWeight());
            }
        }
        return sources;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer> targets = new HashMap<>();
        for(Edge<L> iterater:edges){
            if (iterater.getSource().equals(source)){
                targets.put((L) iterater.getTarget(),iterater.getWeight());
            }
        }
        return targets;
    }
    
    @Override
    public String toString() {
        String toString = "";
        //我们按照起始点为顶点集的顺序输出边集
        for(L iterater:vertices){
            toString = toString+"起始点为"+iterater+": ";
            for(Edge<L> e:edges){
                if(e.getSource().equals(iterater)){
                    toString = toString+e.toString()+" ";
                }
            }
            toString = toString+"\n";
        }
        return toString;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    
    // Abstraction function:
    private final L source,target;
    private final int weight;

    public Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        CheckRep();
    }
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    private void CheckRep(){
        assert this.source!=null;
        assert this.target!=null;
        assert weight>0;
    }
    
    // TODO methods

    /**
     * get方法
     * getSource:返回source
     * getTarget:返回target
     * getWeight:返回weight
     */
     public L getSource(){
         final L Source = source;
         return Source;
     }
     public L getTarget(){
         final L Target = target;
         return Target;
     }
     public int getWeight(){
         final int Weight = weight;
         return Weight;
     }
    // TODO toString()
    @Override
    public String toString(){
         final L Source = source;
         final L Target = target;
         final int Weight = weight;
        return Source.toString()+"->"+Target.toString()+":"+Weight;
    }
    
}
