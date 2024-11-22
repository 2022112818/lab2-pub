/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    /*
     * 测试空图与非空图，有无边相连的点
     */
    @Test
    public void toStringTest(){
        assertEquals("",graph.toString());
        graph.set("a","b",3);
        graph.set("b","a",2);
        graph.set("a","c",1);
        assertEquals("目标点为b: a->b:3 \n目标点为a: b->a:2 \n目标点为c: a->c:1 \n",graph.toString());
        Graph<String> graph1 = new ConcreteVerticesGraph();
        graph1.add("a");
        graph1.add("b");
        assertEquals("目标点为a: \n目标点为b: \n",graph1.toString());
    }
    
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
     * Testing Vertex...
     *
     * 测试添加vertex以及入边集方法
     * 测试set方法
     * 测试get方法
     * 测试toString
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    @Test
    public void Vertex_toString(){
        Vertex a = new Vertex("a");
        assertEquals(0,a.set("b",3));
        assertEquals(3,a.set("b",2));
        assertEquals("a",a.getVertex());
        Map<String,Integer> sources = new HashMap<>();
        sources.put("b",2);
        assertEquals(sources,a.getSources());
        assertEquals("目标点为a: "+"b->a:2 ",a.toString());
    }
}
