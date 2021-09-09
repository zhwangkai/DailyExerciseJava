package DataStructures;

public class StringTOBTree {
    public static void main(String[] args) {
     str2tree("a(cd)");
    }
    public static TreeNode str2tree(String s) {
        if (s.length() == 0) return null;
        StringBuilder value = new StringBuilder();
        TreeNode root = new TreeNode();
        int start = -1;

        for (int i = 0; i< s.length(); i++){
            if (s.charAt(i)=='(') {
                root.val = Integer.parseInt(value.toString());
                start = i;
                break;
            }
            if (i == s.length()-1){
                value.append(s.charAt(i));
                root.val = Integer.parseInt(value.toString());
                return root;
            }
            value.append(s.charAt(i));
        }

        int count = 1;
        for (int i = start + 1; i < s.length(); i++) {
            if ('(' == s.charAt(i)) count++;
            if (')' == s.charAt(i)) count--;
            if (count == 0) {
                root.left = str2tree(s.substring(start + 1, i));
                if ( i != s.length() - 1) {
                    root.right = str2tree(s.substring(i+2, s.length() - 1));
                }
                break;
            }
        }
        return root;

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {};
    TreeNode(int val){ this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}