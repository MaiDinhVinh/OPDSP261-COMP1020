package treepackage.treesimplified;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
    public boolean contains(T value){
        if(this.root == null){
            return false;
        }else{
            return this.contains(value, this.root);
        }
    }

    private boolean contains(T value, Node<T> node){
        if(node.getValue().compareTo(value) == 0){
            return true;
        }else if(value.compareTo(node.getValue()) > 0 && node.getRight() != null){
            return contains(value, node.getRight());
        }else if(value.compareTo(node.getValue()) < 0 && node.getLeft() != null){
            return contains(value, node.getLeft());
        }else{
            return false;
        }
    }

    public boolean add(T value){
        if(this.root == null){
            Node<T> n = new Node<>();
            n.setValue(value);
            this.root = n;
            return true;
        }else{
            return this.add(value, this.root);
        }
    }

    private boolean add(T value, Node<T> node){
        if(value.compareTo(node.getValue()) == 0){
            return false;
        }else if(value.compareTo(node.getValue()) > 0 && node.getRight() != null){
            return add(value, node.getRight());
        }else if(value.compareTo(node.getValue()) < 0 && node.getLeft() != null){
            return add(value, node.getLeft());
        }else if(value.compareTo(node.getValue()) > 0 && node.getRight() == null){
            Node<T> n = new Node<>();
            n.setValue(value);
            node.setRight(n);
            return true;
        }else{
            Node<T> n = new Node<>();
            n.setValue(value);
            node.setLeft(n);
            return true;
        }
    }

    public boolean remove(T value){
        Node<T> n = this.search(value, this.root);
        Node<T> nParent = this.findParent(value, this.root);
        if(n == null){
            return false;
        }else{
            if(n.getRight() == null && n.getLeft() == null && nParent != null){
                if(nParent.getLeft().getValue().compareTo(value) == 0){
                    nParent.setLeft(null);
                }else{
                    nParent.setRight(null);
                }
            }else if((n.getRight() == null || n.getLeft() == null) && nParent != null){
                if(nParent.getLeft() != null && nParent.getLeft().getValue().compareTo(value) == 0){
                    if(n.getRight() == null){
                        nParent.setLeft(n.getLeft());
                    }else{
                        nParent.setLeft(n.getRight());
                    }
                }else{
                    if(n.getRight() == null){
                        nParent.setRight(n.getLeft());
                    }else{
                        nParent.setRight(n.getRight());
                    }
                }
            }else if(n.getRight() == null && n.getLeft() == null && nParent == null){
                this.root = null;
            }else if((n.getRight() == null || n.getLeft() == null) && nParent == null){
                if(n.getRight() == null){
                    this.root = n.getLeft();
                }else{
                    this.root = n.getRight();
                }
            }else{
                Node<T> current = n.getRight();
                while(current.getLeft() != null){
                    current = current.getLeft();
                }
                Node<T> rootRightSub = n.getRight();
                Node<T> rootLeftSub = n.getLeft();
                Node<T> currentParent = this.findParent(current.getValue(), this.root);
                currentParent.setLeft(current.getRight());
                current.setLeft(rootLeftSub);
                if(!(current.getValue().compareTo(n.getRight().getValue()) == 0)){
                    current.setRight(rootRightSub);
                }
                if(nParent != null){
                    if(nParent.getLeft().getValue().compareTo(value) == 0){
                        nParent.setLeft(current);
                    }else{
                        nParent.setRight(current);
                    }
                }else{
                    this.root = current;
                }
            }
            return true;
        }
    }

    private Node<T> search(T value, Node<T> node){
        if(node.getValue().compareTo(value) == 0){
            return node;
        }else if(value.compareTo(node.getValue()) > 0 && node.getRight() != null){
            return search(value, node.getRight());
        }else if(value.compareTo(node.getValue()) < 0 && node.getLeft() != null){
            return search(value, node.getLeft());
        }else{
            return null;
        }
    }

    public Node<T> findParent(T value, Node<T> node){
        if(node == null){
            return null;
        }else if(node.getLeft() != null && node.getLeft().getValue().compareTo(value) == 0){
            return node;
        }else if(node.getRight() != null && node.getRight().getValue().compareTo(value) == 0){
            return node;
        }else if(value.compareTo(node.getValue()) > 0 && node.getRight() != null){
            return findParent(value, node.getRight());
        }else if(value.compareTo(node.getValue()) < 0 && node.getLeft() != null){
            return findParent(value, node.getLeft());
        }else{
            return null;
        }
    }
}