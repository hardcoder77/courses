package com.manage.courses.internal.datastore.trie;

import com.google.common.collect.Maps;

import java.util.Map;

public class TrieNode {

    private char c;
    private Map<Character, TrieNode> children = Maps.newConcurrentMap();
    private boolean isLeaf;

    public TrieNode(char c) {
        this.c = c;
    }
}
