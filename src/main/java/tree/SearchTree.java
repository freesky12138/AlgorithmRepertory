package tree;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:tree
 * @description
 * @date 2018/6/7 16:58
 *
 * 二叉搜索树，左叶子<根<右叶子 */
public class SearchTree {

    public static void main(String[] args) {
        SearchTree searchTree = new SearchTree();
     /*   for (int i = 0; i < 55; i++) {
            searchTree.insert(new Random().nextInt(1000));
        }*/
        System.out.printf(searchTree.find(233) + "\t");
        searchTree.insert(1);
        searchTree.insert(2);
        searchTree.insert(0);
        searchTree.insert(-2);
        searchTree.insert(-1);
        searchTree.insert(4);
        searchTree.insert(3);

        System.out.printf(searchTree.find(0) + "\t");
        System.out.printf(searchTree.find(1) + "\t");
        searchTree.removed(1);
        System.out.printf(searchTree.find(1) + "\t");
        searchTree.removed(0);
        System.out.printf(searchTree.find(0) + "\t");
        System.out.printf(searchTree.find(2) + "\t");
    }

    private Note root;

    /**
     * 插入节点
     *
     * @param x
     */
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

    /**
     * 查找节点
     *
     * @param x
     * @return
     */
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

    /**
     * 删除节点
     *
     * @param x
     */
    public void removed(int x) {
        Note r = root;
        Note parent = null;
        //查找需要删除的节点
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

        //
        while (r != null) {
            //没有子节点 就把parent指向它的设置为null
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

                //如果左子树不为空，不能直接更新左节点，还要考虑左节点是否有右节点，如果有右节点那么就
            } else if (r.left != null) {
                if (r.left.right == null) {
                    parent = r;
                    r.val=r.left.val;
                    r = r.left;
                } else {
                    parent = r.left;
                    r.val=r.left.right.val;
                    r = r.left.right;
                }
            } else {
                parent = r;
                r.val=r.right.val;
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
