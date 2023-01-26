package recipes

abstract class Item {
    abstract val ingredients: MutableList<Item>
    abstract val craftingTime: Double
    abstract val outputCount: Int
}

object IronPlate : Item() {
    override val ingredients = mutableListOf<Item>()
    override val craftingTime = 1.0
    override val outputCount = 1
}

object Gear : Item() {
    override val ingredients = mutableListOf<Item>(IronPlate)
    override val craftingTime = 0.5
    override val outputCount = 1
}