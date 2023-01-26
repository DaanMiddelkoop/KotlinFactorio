import graph.FactoryNode
import graph.Path
import graph.RoutingNode

abstract class Node {
    abstract val tag: String
    val incoming = mutableListOf<Node>()
    val outgoing = mutableListOf<Node>()

    fun addIncoming(node: Node) {
        incoming.add(node)
    }

    fun addOutgoing(node: Node) {
        outgoing.add(node)
    }

    fun bfs(predicate: (Node) -> Boolean): Path? {
        val expansion = mutableListOf(this)
        val parents = mutableMapOf<Node, Node>()
        val seen = mutableSetOf<Node>()

        while (expansion.isNotEmpty()) {
            val next = expansion.removeAt(0)
            if (next in seen) continue
            seen.add(next)



            if (predicate(next)) {
                // reconstruct parents
                return Path().reconstruct(next, parents)
            }

            for (i in next.incoming) {
                if (i in seen) continue
                if (i is RoutingNode && i.hasRoute()) continue
                if (i is FactoryNode && i.hasRecipe()) continue

                if (i !in parents.keys) parents[i] = next
                expansion.add(i)
            }
        }
        return null
    }

    override fun toString(): String {
        return "Node($tag)"
    }
}