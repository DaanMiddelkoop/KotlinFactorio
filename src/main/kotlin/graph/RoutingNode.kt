package graph

open class RoutingNode(override val tag: String) : Node() {
    private var switch: Node? = null

    // Maybe some more complicated switching when possible like splitting 50/50?
    fun setRoute(node: Node) {
        if (switch != null) throw Exception("Can not reroute already routed switch")
        if (node !in this.outgoing) throw Exception("Can not route to unknown node.")
        switch = node
    }

    fun hasRoute() = switch != null
}