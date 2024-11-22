/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    /*
     * 从读取文件上划分应分为是否为空，是否为单行
     * 从读取内容上来分应为是否有自反的单词（例如hello->hello)
     * 从输入诗词上来分应为诗词是否为空，是否只有一个单词，是否有不在亲和图中的单词
     *
     * 根据上述标准，我们准备至少三个txt文件，以及至少三组诗词输入
     * 需要注意的是，spec中的例子都应测试并得到相应输出
     *
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // TODO tests
    @Test
    public void testpoet() throws IOException {
        final GraphPoet graphPoet = new GraphPoet(new File("test/P1/poet/empty.txt"));
        //final Graph<String> graph = Graph.empty();
        //assertEquals(true,graphPoet.equals((GraphPoet)graph));
        final GraphPoet graphPoet1 = new GraphPoet(new File("test/P1/poet/only_one_rol.txt"));
        final String input = "Test the system.";
        assertEquals("Test of the system.",graphPoet1.poem(input));
        assertEquals("",graphPoet1.poem(""));
        assertEquals("out",graphPoet1.poem("out"));
        assertEquals(input,graphPoet.poem(input));
        final GraphPoet graphPoet2 = new GraphPoet(new File("test/P1/poet/only_one_word.txt"));
        assertEquals(input,graphPoet2.poem(input));
        final GraphPoet graphPoet3 = new GraphPoet(new File("test/P1/poet/at_least_tworol.txt"));
        assertEquals("love love love",graphPoet3.poem("love love"));
        assertEquals("to love",graphPoet3.poem("to love"));
        assertEquals("be is",graphPoet3.poem("be is"));
        assertEquals("be, is",graphPoet3.poem("be, is"));
    }

    /*
     *toStringTest
     */
    @Test
    public void test_toString() throws IOException{

    }
}
