package graph

import Node

class Path {
    val nodes = mutableListOf<Node>()

    fun prepend(path: Path) {
        for (p in path.nodes.reversed()) {
            nodes.add(0, p)
        }
    }

    fun append(path: Path) {
        for (p in path.nodes) {
            nodes.add(p)
        }
    }

    fun prepend(node: Node) {
        nodes.add(0, node)
    }

    fun append(node: Node) {
        nodes.add(node)
    }

    fun reconstruct(start: Node, parents: Map<Node, Node>): Path {
        var next: Node? = start
        while (next != null) {
            nodes.add(next)
            next = parents[next]
        }
        return this
    }

    fun setRouting() {
        nodes.zip(nodes.drop(1)).forEach { (a, b) ->
            if (a is RoutingNode) a.setRoute(b)
        }
    }
}