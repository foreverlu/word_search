package trie;

import java.util.*;

public class Trie {
    private TrieNode root;

    private static final char a = 'a';

    public Trie() {
        root = new TrieNode('/');
    }

    private void addChars(char[] chars) {
        if (null == chars || chars.length == 0) {
            return;
        }

        TrieNode p = root;
        for (char c : chars) {
            int index = c - a;
            TrieNode[] children = p.children;
            if (children[index] == null) {
                TrieNode trieNode = new TrieNode(c);
                children[index] = trieNode;
            }
            p = children[index];
        }
        p.isEndChar = true;
    }

    public void addString(String str) {
        if (null == str || str.length() < 1) {
            return;
        }
        addChars(str.toLowerCase().toCharArray());
    }

    public void addStringList(Collection<String> strs){
        if(strs == null || strs.size()<1){
            return;
        }

        Iterator<String> iterator = strs.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            addString(str);
        }
    }

    /**
     * 精确查找单词
     *
     * @param str
     * @return
     */
    public boolean findWord(String str) {
        if (null == str || str.length() < 1) {
            return false;
        }
        return findWord(str.toLowerCase().toCharArray());
    }

    private boolean findWord(char[] chars) {
        if (null == chars || chars.length == 0) {
            return false;
        }

        TrieNode p = root;
        for (char c : chars) {
            int index = c - a;
            TrieNode[] children = p.children;
            if (children[index] == null) {
                return false;
            }
            p = children[index];
        }
        if (p.isEndChar) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据前缀查找单词
     *
     * @param str
     * @return
     */
    public Set<String> findChildrenString(String str) {
        if (null == str || str.length() < 1) {
            return null;
        }
        return findChildrenString(str.toLowerCase().toCharArray());
    }

    private Set<String> findChildrenString(char[] chars) {
        if (chars == null || chars.length < 1) {
            return null;
        }
        TrieNode p = root;
        for (char c : chars) {
            int index = c - a;
            TrieNode[] children = p.children;
            if (children[index] == null) {
                return null;
            }
            p = children[index];
        }
        return buildStrings(p, chars);
    }

    private Set<String> buildStrings(TrieNode p, final char[] base) {
        if (p == null || null == base || base.length < 1) {
            return null;
        }

        String baseString = new String(base);

        Set<String> repo = new HashSet<String>();
        if (p.isEndChar) {
            repo.add(baseString);
        }

        TrieNode[] pp = p.children;

        for (TrieNode node : pp) {
            buildRepo(node, baseString, repo);
        }

        return repo;
    }

    private void buildRepo(TrieNode node, String base, Set<String> repo) {
        if (null == node) {
            return;
        }
        StringBuilder sb = new StringBuilder(base);
        sb.append(node.val);
        if (node.isEndChar) {
            repo.add(sb.toString());
        }

        TrieNode[] children = node.children;

        for (TrieNode child : children) {
            buildRepo(child, sb.toString(), repo);
        }
    }

    class TrieNode {
        char val;
        TrieNode[] children = new TrieNode[26];
        boolean isEndChar;

        public TrieNode(char val) {
            this.val = val;
        }
    }
}
