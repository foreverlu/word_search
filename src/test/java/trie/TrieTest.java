package trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrieTest {

    private Trie trie;

    @Before
    public void setUp(){
        trie = new Trie();
        String[] strs = {"dalianligong","beijingyoudian","qinghuadaxue","beijingdaxue","beijinghangkonghangtiandaxue","dongjingdaxue","xianggangdaxue"};

        for(String s : strs){
            trie.addString(s);
        }
    }

    @Test
    public void tridAddAndFindTest(){
        String[] strs = {"hello","we","word","are"};
        for(String s : strs){
            trie.addString(s);
        }
        Assert.assertTrue(trie.findWord("hello"));
        Assert.assertTrue(trie.findWord("we"));
        Assert.assertTrue(!trie.findWord("wor"));
        Assert.assertTrue(!trie.findWord("area"));
    }

    @Test
    public void findChildrenStringTest(){
        String[] arr = {"word","world"};
        System.out.println(trie.findChildrenString("wo"));
    }



}
