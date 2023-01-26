package graph

import Node
import recipes.Item

class FactoryNode(override val tag: String) : Node() {
    private var item: Item? = null

    fun setRecipe(item: Item) {
        if (this.item != null) throw Exception("Recipe for factory already set")
        println("Setting recipe to $item to $this")
        this.item = item
    }

    fun hasRecipe() = item != null


}