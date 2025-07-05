package com.whg.sensitive;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * 文字匹配
 * @author luzj
 */
public class TireTree {

    private int step;
    private int fact;
    private TrieNode[] root;

    private static int getLen(int l) {
        l |= l >>> 1;
        l |= l >>> 2;
        l |= l >>> 4;
        l |= l >>> 8;
        l |= l >>> 16;
        return (l < 1) ? 0 : l;
    }

    public TireTree(List<String> origins) {
        this(origins, 32, 2);
    }
    public TireTree(List<String> origins, int _fact, int step) {
        this.step = step;
        this.fact = getLen(_fact);
        this.root = new TrieNode[this.fact + 1];

        for (int i = 0; i < root.length; i++) {
            root[i] = new TrieNode('\0');
        }

        origins.forEach(w->{
            if(w != null && w.length() > 0) {
                TrieNode node = root[(w.charAt(0) & this.fact)];
                for (int i = 0; i < w.length(); i++) {
                    char c = w.charAt(i);
                    node = putTo(node, c);
                    if(i == w.length() - 1) {//last
                        node.end = true;
                    }
                }
            }
        });
    }

    private TrieNode putTo(TrieNode n, char v) {
        if(n.nexts != null) {
            for (TrieNode next : n.nexts) {
                if(next.val == v) {
                    return next;
                }
            }
        }
        TrieNode t = new TrieNode(v);
        n.add(t);
        return t;
    }

    public List<String> find(String text) {
        return find(text, step);
    }
    public List<String> find(String text, int step) {
        List<String> ret = new LinkedList<>();
        int index = 0;
        while(index < text.length()) {
            IndexBuf buf = findOne(text, index, step);
            if(!buf.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                buf.forEach(i->sb.append(text.charAt(i)));
                ret.add(sb.toString());
                index = buf.last() + 1;
            } else {
                index += 1;
            }
        }
        return ret;
    }

    public String replace(String text, String re) {
        return replace(text, re, step);
    }
    public String replace(String text, String re, int step) {
        int index = 0;
        StringBuilder x = new StringBuilder(text);
        while(index < text.length()) {
            IndexBuf buf = findOne(text, index, step);
            if(!buf.isEmpty()) {
                buf.forEach(i->x.replace(i, i+1, re));
                index = buf.last() + 1 ;
            } else {
                index += 1;
            }
        }
        return x.toString();
    }

    private IndexBuf findOne(String text, int index, int step) {
        IndexBuf buf = new IndexBuf();
        TrieNode t = null;
        int s = step;
        for(int i=index; i<text.length(); i++) {
            char c = text.charAt(i);
            TrieNode n = match(t, c);
            if(n == null) {
                if(n == null && (--s)<0) {
                    break;
                } else {
                    continue;
                }
            }
            s = step;
            t = n;
            buf.write(i);
            if(t.end) {
                return buf;
            }
        }
        return IndexBuf.Empty;
    }

    private TrieNode match(TrieNode n, char c) {
        if(n == null) {
            n = root[(c & fact)];
        }
        if(n.nexts != null) {
            for (TrieNode t : n.nexts) {
                if(t.val == c) {
                    return t;
                }
            }
        }
        return null;
    }

    private static class TrieNode {
        char val;
        boolean end;
        TrieNode[] nexts = null;
        public TrieNode(char val) {
            this.val = val;
        }
        public void add(TrieNode c) {
            if(nexts == null) {
                nexts = new TrieNode[]{c};
            } else {
                TrieNode[] tmp = new TrieNode[nexts.length + 1];
                System.arraycopy(nexts, 0, tmp, 0, nexts.length);
                tmp[nexts.length] = c;
                nexts = tmp;
            }
        }

        @Override
        public String toString() {
            return this.toString("");
        }
        private String toString(String pre) {
            return nextsToString(currentToString(new StringBuilder(), pre), pre + "  ").toString();
        }
        private StringBuilder currentToString(StringBuilder sb, String pre) {
            return sb.append(pre).append("[").append(val).append(" ").append(end?"Y":"N").append("]");
        }
        private StringBuilder nextsToString(StringBuilder sb, String pre) {
            if(nexts != null) for (TrieNode next : nexts) sb.append("\n").append(next.toString(pre)); return sb;
        }
    }

    private static class IndexBuf {//Int Array
        static final IndexBuf Empty = new IndexBuf(0);
        int[] buf;
        int count;
        IndexBuf() {this(8);}
        IndexBuf(int c) {buf = new int[c];}
        void ensureCap(int cap) {
            if (cap > buf.length) grow(cap);
        }
        void grow(int cap) {
            buf = Arrays.copyOf(buf, cap);
        }
        void write(int b) {
            ensureCap(count + 1);
            buf[count] = b;
            count += 1;
        }
        boolean isEmpty() {
            return count == 0;
        }
        int last() {
            return buf[count-1];
        }
        void forEach(IntConsumer ic) {
            for (int i = 0; i < count; i++) {
                ic.accept(buf[i]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TrieNode mc : root) {
            if (mc.nexts != null) {
                for (TrieNode n : mc.nexts) {
                    sb.append(n.toString()).append("\n");
                }
            }
        }
        return sb.toString();
    }

}
