package graph

open class Graph {
    private val nodes = mutableListOf<Node>()

    private val inputs = mutableListOf<Node>()
    private val outputs = mutableListOf<Node>()


    fun addInput(node: Node) {
        nodes.add(node)
        inputs.add(node)
    }


    fun addOutput(node: Node, dependencies: List<Node>) {
        addNode(node, dependencies)
        outputs.add(node)
    }

    fun addNode(node: Node, dependencies: List<Node>) {
        nodes.add(node)
        for (d in dependencies) {
            node.addIncoming(d)
            d.addOutgoing(node)
        }
    }

    fun getOutputs(): List<Node> {
        return outputs.toList()
    }
}