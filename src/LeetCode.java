import java.util.*;

public class LeetCode {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    // link to question: https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
    public int minDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (isLeaf(root)) return 1;
        if (root.left==null){
            return 1+right;
        }
        if (root.right==null){
            return 1+left;
        }
        return Math.min(left, right) + 1;
    }

    private static boolean isLeaf(TreeNode node){
        if (node==null) return false;
        else return node.left == null && node.right == null;
    }

    //link to question: https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
    public int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        else if (root.right==null && root.left == null){
            return 1;
        }
        else {
            return Math.max(1+maxDepth(root.left), 1+maxDepth(root.right));
        }
    }

        // link to question: https://leetcode.com/problems/same-tree/description/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p!=null && q!=null && p.val!=q.val){
            return false;
        }
        else if(p==null && q==null){
            return true;
        }
        else if ((p==null && q!=null) || (p!=null && q==null)){
            return false;
        }
        else {
            return isSameTree(p.left, q.left) & isSameTree(p.right, q.right);
        }

    }

//    // question link: https://leetcode.com/problems/path-sum/description/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        boolean left = false, right= false;
        if (isLeaf(root) && targetSum-root.val==0){
            return true;
        }
        else if(isLeaf(root) && targetSum-root.val!=0){
            return false;
        }
        else if (root!=null){
            if (root.left!=null){
                left = hasPathSum(root.left, targetSum-root.val);
            }
            if (root.right!=null){
                right =hasPathSum(root.right, targetSum-root.val);
            }
        }
        return left | right;
    }

    private boolean isLeaf(TreeNode node){
        if (node!=null && node.right==null && node.left==null){
            return true;
        }
        else return false;
    }

    // link to question: https://leetcode.com/problems/set-matrix-zeroes/description/
    public static void setZeroes(int[][] matrix) {
        List<Integer[]> indexesToChange = new ArrayList<>();
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                if (i<matrix.length && j<matrix[0].length && matrix[i][j]==0){
                    indexesToChange.add(new Integer[]{i,j});
                }
            }
        }
        for (int k=0; k<indexesToChange.size(); k++){
            setRowAndColZero(matrix, indexesToChange.get(k)[0], indexesToChange.get(k)[1]);
        }

        List<Integer> lst = new ArrayList<>();

    }


    private static void setRowAndColZero(int[][] matrix, int row, int col){
        for (int i=0; i<matrix[0].length; i++){
            matrix[row][i]=0;
        }
        for (int j=0; j<matrix.length; j++){
            matrix[j][col]=0;
        }
    }


    public static int cookies(int k, List<Integer> A) {
        Collections.sort(A);
        int totalOperations = 0;
        if (A.get(0)>=k){
            return 0;
        }
        int currentMix, index=0;
        while (A.get(0)<k && index<A.size()-1){
            currentMix = A.get(index)+(2*A.get(index+1));
            A.remove(index);
            A.remove(index);
            insertIntoSortedList(A, currentMix);
            totalOperations++;

        }
        return totalOperations;
    }

    private static void insertIntoSortedList(List<Integer> lst, int num){
        int index = Arrays.binarySearch(lst.toArray(), num);
        if (index>=0){
            lst.add(num, index);
        }
        else{
            index = index * (-1) - 1;
            if (index< lst.size()) {
                lst.add(index, num);
            }
            else {
                lst.add(num);
            }
        }
    }

    public static void preOrderTraversal(TreeNode root){
        if (root==null){
            return;
        }
        else {
            System.out.println(root.val);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(1);
        root.left=node1;
        root.right = new TreeNode(10);
        node1.left=node2;
        node2.left=node3;
        node2.right = new TreeNode(7);
//
//        preOrderTraversal(root);
//        List<Integer> lst = new ArrayList<>(Arrays.stream(new int[]{1, 2, 3, 9, 10, 12}).boxed().toList());
//        cookies(7,lst);

//        setZeroes(new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}});
    }
}
