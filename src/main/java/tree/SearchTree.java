package tree;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:tree
 * @description
 * @date 2018/6/7 16:58
 */
public class SearchTree {

    public static void main(String[] args) {
        SearchTree searchTree = new SearchTree();
     /*   for (int i = 0; i < 55; i++) {
            searchTree.insert(new Random().nextInt(1000));
        }*/
        System.out.printf(searchTree.find(233) + "\t");
        searchTree.insert(555);
        searchTree.insert(555);

        System.out.printf(searchTree.find(233) + "\t");
        System.out.printf(searchTree.find(555) + "\t");
        searchTree.removed(555);
        System.out.printf(searchTree.find(555) + "\t");
        searchTree.removed(555);
        System.out.printf(searchTree.find(555) + "");
    }

    private Note root;


    public void insert(int x) {
        Note note = new Note();
        note.setVal(x);
        if (root == null) {
            root = note;
            return;
        }
        Note r = root;
        while (r != null) {
            if (x > r.val) {
                if (r.getRight() == null) {
                    r.setRight(note);
                    break;
                }
                r = r.getRight();
            } else {
                if (r.getLeft() == null) {
                    r.setLeft(note);
                    break;
                }
                r = r.getLeft();
            }
        }

    }


    public boolean find(int x) {
        Note r = root;
        while (r != null) {
            if (r.getVal() == x) {
                return true;
            } else if (r.left != null && r.getVal() > x) {
                r = r.left;
            } else {
                r = r.right;
            }
        }
        return false;
    }


    public void removed(int x) {
        Note r = root;
        Note parent = null;
        while (r != null) {
            if (r.getVal() == x) {
                break;
            } else if (r.left != null && r.getVal() > x) {
                parent = r;
                r = r.left;
            } else {
                parent = r;
                r = r.right;
            }
        }
        while (r != null) {
            if (r.left == null && r.right == null) {
                if (parent == null) {
                    if (r.getVal() == x) {
                        root = null;
                    }
                    break;
                }
                if (parent.left == r) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                break;
            } else if (r.left != null) {
                parent = r;
                r = r.left;
            } else {
                parent = r;
                r = r.right;
            }
        }
    }


    static class Note {
        private int val;
        private Note left;
        private Note right;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Note getLeft() {
            return left;
        }

        public void setLeft(Note left) {
            this.left = left;
        }

        public Note getRight() {
            return right;
        }

        public void setRight(Note right) {
            this.right = right;
        }
    }
}
