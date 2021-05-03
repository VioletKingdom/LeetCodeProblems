// given a list of edges, build a set of nodes as graph
public Set<Node> buildGraph(List<List<Integer>> edges){
	if (edges == null){
		return null;
	}
	Map<Integer, Node> map = new HashMap<>();
	for (List<Integer> edge : edges){
		int val1 = edge.get(0);
		int val2 = edge.get(1);
		Node n1 = map.get(val1);
		Node n2 = map.get(val2);
		if (n1 == null){
			n1 = new Node(val1);
			map.put(val1, n1);
		}
		if (n2 == null){
			n2 = new Node(val2);
			map.put(val2, n2);
		}
		n1.neighbors.add(n2);
		n2.neighbors.add(n1);
	}
	return new HashSet<Node>(map.values());
}

/*
遍历所有edge 把edge的两个node放进map里
有的话没事 没有的话新建
然后两个node把相互加进neighbors里就好了
记得return 要new一个set出来 因为光return map.values()是一个set的view
本质上还是一个map，要新建
*/

public Set<Node> buildGraph2(List<List<Integer>> edges){
	if (edges == null){
		return null;
	}
	Map<Integer, Node> map = new HashMap<>();
	for (List<Integer> edge : edges){
		// edge is <val1, val2>  1 -> 2
		int val1 = edge.get(0);
		int val2 = edge.get(1);
		Node n1 = map.get(val1);
		Node n2 = map.get(val2);
		if (n1 == null){
			n1 = new Node(val1);
			map.put(val1, n1);
		}
		if (n2 == null){
			n2 = new Node(val2);
			map.put(val2, n2);
		}
		// 注意要点！！！如果是有向图
		// n2 is n1's neighbor
		// n1 is not n2's neighbor
		n1.neighbors.add(n2);
	}
	return new HashSet<Node>(map.values());
}


// Clone Graph
// 两种情况 一种是给List<Node> 一种是只给一个seed node
// (1)
public List<Node> clone(List<Node> graph){
	List<Node> cloneGraph = new ArrayList<>();
	Map<Node, Node> map = new HashMap<>();
	for (Node node: graph){
		Node copyNode = map.get(node);
		if(copyNode == null){
			copyNode = new Node(node.val);
			map.put(node, copyNode);
		}
		for (Node nei : node.neighbors){
			Node copyNei = map.get(nei);
			if (copyNei == null){
				copyNei = new Node(nei.val);
				map.put(nei, copyNei);
			}
			//这步别忘！
			copyNode.neighbors.add(copyNei);
		}
		//这步别忘！
		cloneGraph.add(copyNode);
	}
	return cloneGraph;
} 
/*
这道题先new 出来要return的List<Node> 记得每建好一个node加进去
也是用map 一一对应来clone
遍历所有node in graph,如果有没事，如果没有新建
然后新建出对应的neighbors，加进这个node的neighbors里面
最后就完成了 所有node都已知比较简单
*/

// (2) 只有一个seed node
public Node clone2(Node node){
	if (node == null){
		return null;
	}
	return dfs(node, new HashMap<>());
}

public Node dfs(Node cur, Map<Node, Node> map){
	Node copyNode = map.get(cur);
	if (copyNode != null){
		return copyNode;
	}
	copyNode = new Node(node.val);
	map.put(node, copyNode);
	for(Node nei : cur.neighbors){
		copyNode.neighbors.add(dfs(n, map));
	}
	return copyNode;
}

/*
用dfs来做 因为没有node list无法遍历
我们已知的是这个node 和 它的neighbors

dfs这个function的作用是
当一个copy node不存在在map里时，新建出来，
并且新建出它的neighbor，一个一个来
若它的neighbor也不存在在map里，同上的过程，
这就dfs了

当一个copy node已经存在，说明我们已经建出来它了，
并且正在试图建上一层recursion的邻居
所以返回它本身就可以

*/

// (2) 只有一个seed node 用BFS来做
public Node cloneGraph2(Node node){
	if(node == null){
		return null;
	}
	Queue<Node> queue = new ArrayDeque<>();
	queue.offer(node);
	Map<Node, Node> map = new HashMap<>();
	Node copyNode = new Node(node.val);
	map.put(node, copyNode);

	while (!queue.isEmpty()){
		Node cur = queue.poll();
		Node copyCur = new Node(cur.val);
		for (Node nei : cur.neighbors){
			Node copyNei = map.get(nei);
			if(copyNei == null){
				queue.offer(nei);
				copyNei = new Node(nei.val);
				map.put(nei, copyNei);
			}
			copyCur.neighbors.add(copyNei);
		}
	}
	return copyNode;
}

/*
bfs不需要用到recursion 一层层往外走
用了一个queue来记录现在在的node是哪个
新建的node 一定要加进queue里
每到一个node 遍历neighbors 这个node其实就都完成了
*/

// make a reverse copy of a directed graph
// 有向图 clone一个所有edge都是反的
// 2 -> 3 <- 1        2 <- 3 -> 1
public Node cloneGraphReverse(Node node){
	if (node == null){
		return null;
	}
	return dfs(node, new HashMap<>());
}

public Node dfs(Node node, HashMap<Node, Node> map){
	Node copyNode = map.get(node);
	if(copy != null){
		return copyNode;
	}
	copyNode = new Node(node.val);
	map.put(node, copyNode);
	for(Node nei: node.neighbors){
		Node copyNei = map.get(nei);
		dfs(nei, map).neighbors.add(copyNode);
	}
	return copyNode;
}


























