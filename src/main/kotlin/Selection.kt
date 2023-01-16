class SelectionGroup(val selections: MutableList<Selection> = mutableListOf()) : Iterable<Selection> {

    override fun iterator(): Iterator<Selection> {
        return selections.iterator()
    }

    fun run() {
        for ((index, selection) in mainSelectionGroup.withIndex()) {
            println("${index + 1}:${selection.selectionName}")
        }
        val choice = requireChoice(1, selections.size)
        selections[choice - 1].toDo.run()
    }
}

data class Selection(val selectionName: String, val toDo: Runnable) {}

fun requireChoice(from: Int, to: Int): Int {
    while (true) {
        val choice = try {
            readln().toInt()
        } catch (e: Exception) {
            println("You entered the option incorrectly, please re-enter it")
            continue;
        }
        if (choice in from..to) {
            return choice
        } else {
            println("You entered the option incorrectly, please re-enter it")
        }
    }
}