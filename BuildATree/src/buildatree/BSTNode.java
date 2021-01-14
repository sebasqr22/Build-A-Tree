package buildatree;

public class BSTNode {
    private BSTNode left;
    private BSTNode right;
    private Integer data;

    public BSTNode(Integer data) {
        this.data = data;
    }

    public BSTNode getLeft() {
        return left;
    }
    public void setLeft(BSTNode left) {
        this.left = left;
    }
    public BSTNode getRight() {
        return right;
    }
    public void setRight(BSTNode right) {
        this.right = right;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
