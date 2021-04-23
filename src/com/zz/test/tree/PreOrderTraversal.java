package com.zz.test.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <P>
 * @Desc:二叉树先序遍历
 * </P>
 * 
 * @auth jld.zz
 * @Time 2021年4月8日 下午2:38:29
 * @Version 1.0
 */

public class PreOrderTraversal {
	static List<String> traversalList = new ArrayList<>();

	public static void main(String[] args) {
		// 构造树节点如当前图:tree_PreOrderTraversal_1_tree.png
		TreeNode root = new TreeNode("F",
				new TreeNode("B", new TreeNode("A", null, null),
						new TreeNode("D", new TreeNode("C", null, null), new TreeNode("E", null, null))),
				new TreeNode("G", null, new TreeNode("I", new TreeNode("H", null, null), null)));

		/**
		 * 先序遍历
		 */
		System.out.print("先序_递归_遍历结果为：");
		preOrderTraversal(root).forEach(v -> {
			System.out.print(v + " ");
		});
		System.out.println();
		
		// 迭代执行算法
		System.out.print("先序_迭代_方式遍历结果为：");
		preOrderTraversalIteration(root).forEach(v -> {
			System.out.print(v + " ");
		});
		;
		System.out.println();
		
		// 迭代执行算法，单while
		System.out.print("先序-迭代-单while，遍历结果为：");
		preOrderTraversalIterationOth(root).forEach(v -> {
			System.out.print(v + " ");
		});
		;
		System.out.println();
		
		

		/**
		 * 中序
		 */
		//中序-递归
		traversalList = new ArrayList<>();
		System.out.print("中序-递归-遍历结果为：");
		midOrderTraversal(root).forEach(v -> {
			System.out.print(v + " ");
		});
		System.out.println();
		
		// 中序-迭代
		System.out.print("中序-迭代-单while，遍历结果为：");
		midOrderTraversalIterationOth(root).forEach(v -> {
			System.out.print(v + " ");
		});
		System.out.println();
		

		
		/**
		 * 后序
		 */
		//后序递归
		traversalList = new ArrayList<>();
		System.out.print("后序遍历结果为：");
		afterOrderTraversal(root).forEach(v -> {
			System.out.print(v + " ");
		});
		;
		System.out.println();

		
		
	}

	// 递归先序遍历
	public static List<String> preOrderTraversal(TreeNode treeNode) {

		traversalList.add(treeNode.val);

		if (treeNode.left != null) {
			preOrderTraversal(treeNode.left);
		}
		if (treeNode.right != null) {
			preOrderTraversal(treeNode.right);
		}

		return traversalList;
	}

	/**
	 * 
	 * @Desc 迭代 法，先序遍历 ；利用出栈，入栈
	 * @auth <a href="http://albagu.com">jld.zz</a>
	 * @Time 2021-04-09 11:04
	 * @param treeNode
	 * @return
	 */
	public static List<String> preOrderTraversalIteration(TreeNode treeNode) {
		List<String> traversalList1 = new ArrayList<>();

		Stack<TreeNode> stack = new Stack<>();

		while (treeNode != null || !stack.isEmpty()) {

			while (treeNode != null) {
				traversalList1.add(treeNode.val);
				stack.push(treeNode);
				treeNode = treeNode.left;

			}
			treeNode = stack.pop().right;

		}

		return traversalList1;
	}

	/**
	 * 
	 * @Desc 迭代 法，先序遍历 ；利用出栈，入栈；单while
	 * @auth <a href="http://albagu.com">jld.zz</a>
	 * @Time 2021-04-09 11:04
	 * @param treeNode
	 * @return
	 */
	public static List<String> preOrderTraversalIterationOth(TreeNode treeNode) {
		List<String> traversalList1 = new ArrayList<>();

		Stack<TreeNode> stack = new Stack<>();
		stack.push(treeNode);
		while (stack.size() > 0) {
			treeNode = stack.pop();

			
			traversalList1.add(treeNode.val);
			

			if (treeNode.right != null) {
				stack.push(treeNode.right);
			}

			if (treeNode.left != null) {
				stack.push(treeNode.left);
			}

		}

		return traversalList1;
	}

	public static List<String> midOrderTraversal(TreeNode treeNode) {

		if (treeNode.left != null) {
			midOrderTraversal(treeNode.left);
		}
		traversalList.add(treeNode.val);
		if (treeNode.right != null) {
			midOrderTraversal(treeNode.right);
		}

		return traversalList;
	}

	/**
	 * 
	 * @Desc 迭代 法，中序遍历 ；利用出栈，入栈；
	 * @auth <a href="http://albagu.com">jld.zz</a>
	 * @Time 2021-04-09 11:04
	 * @param treeNode
	 * @return
	 */
	public static List<String> midOrderTraversalIterationOth(TreeNode treeNode) {
		List<String> traversalList1 = new ArrayList<>();

		Stack<TreeNode> stack = new Stack<>();
		int i =0;
		
		while (stack.size()>0 || i == 0) {


			if(treeNode.left == null) {//左节点空，则添加值
				traversalList1.add(treeNode.val);
				if(treeNode.right != null) {
					stack.push(treeNode.right);
					
				}
			}else {
				treeNode = treeNode.left;
				stack.push(treeNode);
			}
			
			i++;
		}

		return traversalList1;
	}
	
	public static List<String> afterOrderTraversal(TreeNode treeNode) {

		if (treeNode.left != null) {
			afterOrderTraversal(treeNode.left);
		}

		if (treeNode.right != null) {
			afterOrderTraversal(treeNode.right);
		}
		traversalList.add(treeNode.val);
		return traversalList;
	}

}

/**
 * 
 * @Desc
 *       <p>
 *       二叉树
 *       </p>
 * @auth jld.zz {@link email:idiot_jilidan@163.com}
 * @link <a href="http://albagu.com">个站</a>;<a href=
 *       "https://blog.csdn.net/zhangzhouisme">csdn博客</a>;
 * @Time 2021年4月8日 下午2:39:23
 * @Version 1.0
 */
class TreeNode {
	String val;
	TreeNode left;
	TreeNode right;

	public TreeNode() {

	}

	public TreeNode(String val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

}