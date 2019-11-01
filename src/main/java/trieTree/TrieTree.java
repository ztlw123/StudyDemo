package trieTree;

/**
 * @Author zjh
 * @Date 2019/08/06,21:35
 */
public class TrieTree {

    private final int SIZE = 26;
    private Node root;

    private class Node {
        private boolean isTerminal;
        private int num;
        private Node[] child;

        public Node() {
            child = new Node[SIZE];
            isTerminal = false;
            num = 1;
        }
    }


    public TrieTree() {
        root = new Node();
    }

    //插入
    public void insert(String word) {
        if(word == null || word.isEmpty())
            return;

        Node rNode = this.root;
        for(int i=0; i<word.length(); i++) {

            int index = word.charAt(i) - 'a';

            if(root.child[index] == null) {

                Node temp = new Node();
                rNode.child[index] = temp;
            }
            else {
                rNode.child[index].num++;
            }
            rNode = rNode.child[index];
        }

        rNode.isTerminal = true;
    }

    //查找是否包含
    public boolean isContains(String word) {
        Node rNode = this.root;

        for(int i=0; i<word.length(); i++) {

            int index = word.charAt(i) - 'a';
            if(rNode.child[index] != null) {
                if(rNode.child[index].isTerminal && word.length() == i+1)
                    return true;
                else
                    rNode = rNode.child[index];
            }
            else {
                return false;
            }
        }

        return true;
    }

    //查找有几个前缀
    public int countPrefix(String str) {
        Node rNode = this.root;

        for(int i=0; i<str.length(); i++) {

            int index = str.charAt(i) - 'a';
            if (rNode.child[index] == null) {
                return 0;
            }
            else {
                rNode = rNode.child[index];
            }
        }

        return rNode.num;
    }

    //遍历
    public  void prePrint(Node rNode) {

        for(int i=0; i<SIZE; i++) {
            if(rNode.child[i] != null) {
                System.out.print((char)('a'+i) + "--");
                prePrint(rNode.child[i]);
            }
        }
    }

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        trieTree.insert("ab");
        trieTree.insert("bc");
        trieTree.insert("abc");
        trieTree.insert("cde");
        trieTree.insert("a");

        System.out.println(trieTree.countPrefix("a"));
        System.out.println(trieTree.countPrefix("ab"));
        System.out.println(trieTree.isContains("abc") + "---" + trieTree.isContains("de"));
        trieTree.prePrint(trieTree.root);
    }
}


