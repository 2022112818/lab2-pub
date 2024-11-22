/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    /*
     *测试空图与非空图,有无边相连的点
     */
    
    // TODO tests for ConcreteEdgesGraph.toString()
    
    /*
     * Testing Edge...
     */
    @Test
    public void toStringTest(){
        assertEquals("",graph.toString());
        graph.set("a","b",3);
        graph.set("b","a",2);
        graph.set("a","c",1);
        assertEquals("起始点为a: a->b:3 a->c:1 "+"\n"+"起始点为b: b->a:2 "+"\n起始点为c: "+"\n",graph.toString());
        Graph<String> graph1 = new ConcreteEdgesGraph();
        graph1.add("a");
        graph1.add("b");
        assertEquals("起始点为a: \n"+"起始点为b: \n",graph1.toString());
    }
    // Testing strategy for Edge
    /*
     *测试边集空与非空，边长是否合法,顶点是否合法
     */
    
    // TODO tests for operations of Edge
    @Test
    public void Edge_toStringTest(){
        Edge edge = new Edge("a","b",3);
        assertEquals("a->b:"+3,edge.toString());
        //Edge edge1 = new Edge(null,null,3);
        //assertEquals(null,edge1.toString());
        //Edge edge2 = new Edge("a","b",0);
        //assertEquals("a->b:-1",edge2.toString());
    }
}
