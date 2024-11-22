/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    /**
     * Testing strategy
     * 根据Gragh的不同操作方法确定测试类，考虑不同操作方法可能产生的输出结果,对不同情况加以测试
     *
     * addtest方法应测试顶点是否在图中
     *
     * settest方法应测试边的权重，以及添加边时是否添加新的顶点
     *
     * removetest方法应测试删除的点是否在图中，以及相应的边是否被删除
     *
     * verticestest方法应测试空图与非空图
     *
     * sourcestest方法应测试有无边连接的target,以及是否在图中的target
     *
     * targetstest方法应测试有无边连接的source,以及是否在图中的source
     */
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    Graph<String> graph = emptyInstance();
    /**
     * 建立一个只有a,b,c三个顶点没有边的图，
     * 依次添加a,b,c三点，添加两次b点，
     * 是否得到期望输出
     */
    @Test
    public void addtest(){
        assertEquals(true,graph.add("a"));
        assertEquals(true,graph.add("b"));
        assertEquals(true,graph.add("c"));
        assertEquals(false,graph.add("b"));
    }

    @Test
    public void settest(){
        assertEquals(0,graph.set("a","b",3));
        assertEquals(3,graph.set("a","b",2));
        assertEquals(0,graph.set("b","a",2));
        assertEquals(0,graph.set("c","d",1));
        assertEquals(false,graph.add("d"));
        assertEquals(0,graph.set("e","d",3));
        assertEquals(false,graph.add("e"));
    }

    @Test
    public void removetest(){
        assertEquals(0,graph.set("e","d",3));
        assertEquals(true,graph.remove("e"));
        assertEquals(false,graph.remove("e"));
        assertEquals(0,graph.set("e","d",3));
    }

    @Test
    public void verticestest(){
        assertEquals(0,graph.set("b","a",2));
        assertEquals(0,graph.set("c","d",1));
        Set<String> test_A = new HashSet<>();
        test_A.add("a");
        test_A.add("b");
        test_A.add("c");
        test_A.add("d");
        assertEquals(test_A,graph.vertices());
        Graph<String>graph1 = emptyInstance();
        assertEquals(new HashSet<>(),graph1.vertices());
    }

    @Test
    public void sourcestest(){
        assertEquals(0,graph.set("c","d",1));
        assertEquals(0,graph.set("e","d",3));
        Map<String,Integer> test_sources = new HashMap<>();
        test_sources.put("c",1);
        test_sources.put("e",3);
        assertEquals(test_sources,graph.sources("d"));
        graph.add("f");
        assertEquals(new HashMap<>(),graph.sources("f"));
        assertEquals(new HashMap<>(),graph.sources("l"));
    }

    @Test
    public void targetstest(){
        assertEquals(0,graph.set("a","b",2));
        Map<String,Integer> test_targets = new HashMap<>();
        test_targets.put("b",2);
        assertEquals(test_targets,graph.targets("a"));
        graph.add("f");
        assertEquals(new HashMap<>(),graph.targets("f"));
        assertEquals(new HashMap<>(),graph.targets("l"));
    }
}
