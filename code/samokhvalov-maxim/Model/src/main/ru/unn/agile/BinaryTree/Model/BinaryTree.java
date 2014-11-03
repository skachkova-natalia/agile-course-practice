package ru.unn.agile.BinaryTree.Model;

import java.util.ArrayList;

public class BinaryTree {

    private Node root;
    private ArrayList<Node> searchNodes = null;
    public Node getRoot() { return this.root; }

    public void insert(final Node newNode) {
        this.insert(this.root, newNode);
    }

    private void insert(final Node root, final Node newNode) {
        if (root == null) {
            this.root = newNode;
        } else {
            if (root.getKey() == newNode.getKey()) {
                root.setValue(newNode.getValue());
            } else if (root.getKey() > newNode.getKey()) {
                if (root.getLeft() == null) {
                    root.setLeft(newNode);
                } else {
                    insert(root.getLeft(), newNode);
                }
            } else if (root.getKey() < newNode.getKey()) {
                if (root.getRight() == null) {
                    root.setRight(newNode);
                } else {
                    insert(root.getRight(), newNode);
                }
            }
        }
    }

    /*private void delete(final Node root, final int key) {
        if (root == null) {
            return;
        }

        if (key > root.getKey()) {
            delete(root.getRight(), key);
        } else if (key < root.getKey()) {
            delete(root.getLeft(), key);
        } else {
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
            } else if (root.getLeft() == null) {
                root = root.getRight();
                int ket = root.getKey();
            } else if (root.getRight() == null) {
                root = root.getLeft();
                int ket = root.getKey();
            } else {
                if (root.getRight().getLeft() == null) {
                    root.getRight().setLeft(root.getLeft());
                    root = root.getRight();
                } else {
                    root.setKey(root.getRight().getLeft().getKey());
                    root.setValue(root.getRight().getLeft().getValue());
                    this.delete(root.getRight().getLeft());
                }
            }
        }
    } */

    public void delete(final int key) {
        if (this.root == null) {
            return;
        }

        Node node = this.find(this.root, key);

        if (node != null) {
            this.deleteNode(node);
        }
    }

    private void deleteNode(final Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            deleteChildByKey(this.root, null, node.getKey());

        } else if (node.getLeft() == null) {
            node.initBy(node.getRight());
        } else if (node.getRight() == null) {
            node.initBy(node.getLeft());
        } else {
            if (node.getRight().getLeft() == null) {
                node.getRight().setLeft(node.getLeft());
                node.initBy(node.getRight());
            } else {
                node.setKey(node.getRight().getLeft().getKey());
                node.setValue(node.getRight().getLeft().getValue());
                this.deleteNode(node.getRight().getLeft());
            }
        }
    }

    private void deleteChildByKey(final Node root, final Node parent, final int key) {
        if (root.getKey() == key) {
            if (root.getKey() < parent.getKey()) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
            return;
        }

        if (root.getKey() < key) {
            deleteChildByKey(root.getRight(), root, key);
        } else {
            deleteChildByKey(root.getLeft(), root, key);
        }
    }

    private Node find(final Node root, final int key) {
        if (root == null) {
            return null;
        }

        if (root.getKey() == key) {
            return root;
        }

        if (root.getKey() < key) {
            return find(root.getRight(), key);
        } else {
            return find(root.getLeft(), key);
        }
    }

    public ArrayList<Node> findByValue(final Object value) {
        searchNodes = new ArrayList<Node>();
        recInOrder(this.root, value);
        return searchNodes;
    }

    private void recInOrder(final Node root, final Object value) {
        if (root != null) {
            recInOrder(root.getLeft(), value);
            if (root.getValue().equals(value)) {
                searchNodes.add(root);
            }
            recInOrder(root.getRight(), value);
        }
    }
}
