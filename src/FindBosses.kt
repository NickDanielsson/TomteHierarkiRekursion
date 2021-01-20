import kotlin.system.exitProcess

fun rekursionFindBosses(tMap: Map<String, Any>, nisse: String): List<String> {

    tailrec fun getList(list: List<Pair<String, Any>>, nisse: String, bossList: MutableList<String>): MutableList<String> {

        var newNisse = nisse
        for (x in 0..list.size - 1) {
            if (list[x].second.toString().contains(newNisse, true)) {
                bossList.add(list[x].first)
                newNisse = list[x].first
                break
            }
        }
        return if (newNisse == list[0].first) bossList
        else getList(list, newNisse, bossList)

    }

    val list = tMap.toList()
    val tempList = mutableListOf<String>()
    return getList(list, nisse, tempList).reversed()
}


fun main() {

    val tommteMap = mapOf(
        "Tomten" to listOf("Glader", "Butter"),
        "Glader" to listOf("Tröger", "Trötter", "Blyger"),
        "Butter" to listOf("Rådjuret", "Nyckelpigan", "Haren", "Räven"),
        "Trötter" to "Skumtomten",
        "Skumtomten" to "Dammråttan",
        "Räven" to listOf("Gråsuggan", "Myran"),
        "Myran" to "Bladlusen"
    )


    fun tomteName(): String {
        val list = tommteMap.toList()

        var name = ""
        do {
            println("Vilken nisse vill du veta cheferna till?")
            name = readLine().toString()
            if (!list.toString().contains(name,ignoreCase = true))
                println("Nisse med det namnet finns inte.")
        }while (!list.toString().contains(name,true))

        if (name.equals(list[0].first,ignoreCase = true)){
            println("Tomten är högsta chefen!")
            exitProcess(0)
        }

       return name
    }

    val name = tomteName()
    val chefLista = rekursionFindBosses(tommteMap,name).joinToString(" <-- ","", "")
    println("Cheferna till $name är:\n$chefLista")
}