/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // TODO test other vertex label types in Problem 3.2

    /*
     * 分别用int型和double型测试各种方法；
     */
    @Test
    public void testothervertex_int(){
        Graph<Integer> graph = Graph.empty();
        assertTrue(graph.add(1));
        assertFalse(graph.add(1));
        assertEquals(0,graph.set(1,2,1));
        assertEquals(1,graph.set(1,2,2));
        assertFalse(graph.remove(3));
        assertTrue(graph.remove(2));
        assertEquals(0,graph.set(1,2,3));
        Graph<Integer> graph1 = Graph.empty();
        Set<Integer> graph1_set = new HashSet<>();
        assertEquals(graph1_set,graph1.vertices());
        graph1_set.add(1);
        graph1_set.add(2);
        assertEquals(graph1_set,graph.vertices());
    }
    
}
