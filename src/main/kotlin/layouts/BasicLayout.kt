package layouts

import graph.Node
import graph.FactoryNode
import graph.Graph
import graph.RoutingNode

class BasicLayout : Graph() {

    /// Forms a graph of n by n routing nodes, where each horizontally adjacent pair of routing nodes also has a factory.
    init {

        val layers = mutableListOf<MutableList<Node>>()


        val inputLayer = mutableListOf<Node>()
        for (i in 0 until 10) {
            val node = RoutingNode("input$i")
            inputLayer.add(node)
            addInput(node)
        }
        layers.add(inputLayer)

        for (l in 1 until 10) {
            val nextLayer = mutableListOf<Node>()
            val factories = mutableListOf<Node>()
            val previousLayer = layers[l - 1]
            // Add factories
            for (i in 0 until 10) {
                val factoryNode = FactoryNode("factory$l,$i")
                factories.add(factoryNode)
                addNode(factoryNode, listOf(previousLayer[i]))
            }

            // Add next layer
            for (i in 0 until 10) {
                val node = RoutingNode("routing$l,$i")
                nextLayer.add(node)

                val dependencies = mutableListOf(factories[i], previousLayer[i])
                // Make vertical connections
                if (i != 0) {
                    dependencies.add(nextLayer[i - 1])
                }
                addNode(node, dependencies)
            }
            layers.add(nextLayer)
        }

        val lastLayer = layers.last()
        for (i in 0 until 10) {
            val node = RoutingNode("output$i")
            addOutput(node, listOf(lastLayer[i]))
        }

    }

    fun toBlueprint() {

    }
}