import graph.FactoryNode
import graph.Graph
import graph.RoutingNode
import recipes.Item

class Builder {


    fun build(item: Item, graph: Graph) {
        val startNode = graph.getOutputs().first()
        buildNode(item, startNode)


    }

    fun buildNode(item: Item, node: Node) {
        // Select factory for item
        val badFactories = mutableSetOf<Node>()

        while (true) {
            val factoryPath = node.bfs { n ->
                n is FactoryNode && n !in badFactories
            } ?: throw Exception("Could not finish graph, no eligible factories")
            val factory = factoryPath.nodes.first() as FactoryNode

            // Check if path exists from this factory to input.
            if (factory.bfs { n -> n.incoming.isEmpty()} != null) {
                // commit to this path
                factory.setRecipe(item)
                factoryPath.setRouting()
                for (i in item.ingredients) {
                    buildNode(i, factory.incoming[0])
                }
                return
            }
        }
    }






}