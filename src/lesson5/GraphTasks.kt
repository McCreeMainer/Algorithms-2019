@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson5

import java.util.*
import kotlin.collections.LinkedHashSet


/**
 * Эйлеров цикл.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
 * Если в графе нет Эйлеровых циклов, вернуть пустой список.
 * Соседние дуги в списке-результате должны быть инцидентны друг другу,
 * а первая дуга в списке инцидентна последней.
 * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
 * Веса дуг никак не учитываются.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
 *
 * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
 * связного графа ровно по одному разу
 */
fun Graph.findEulerLoop(): List<Graph.Edge> {
    TODO()
}

/**
 * Минимальное остовное дерево.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему минимальное остовное дерево.
 * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
 * вернуть любое из них. Веса дуг не учитывать.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ:
 *
 *      G    H
 *      |    |
 * A -- B -- C -- D
 * |    |    |
 * E    F    I
 * |
 * J ------------ K
 */
fun Graph.minimumSpanningTree(): Graph {
    TODO()
}

/**
 * Максимальное независимое множество вершин в графе без циклов.
 * Сложная
 *
 * Дан граф без циклов (получатель), например
 *
 *      G -- H -- J
 *      |
 * A -- B -- D
 * |         |
 * C -- F    I
 * |
 * E
 *
 * Найти в нём самое большое независимое множество вершин и вернуть его.
 * Никакая пара вершин в независимом множестве не должна быть связана ребром.
 *
 * Если самых больших множеств несколько, приоритет имеет то из них,
 * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
 *
 * В данном случае ответ (A, E, F, D, G, J)
 *
 * Если на входе граф с циклами, бросить IllegalArgumentException
 *
 * Эта задача может быть зачтена за пятый и шестой урок одновременно
 */
// N - count of vertices
// E - count of edges
//  Time Complexity:
//      T = O(min[(N * N), (N * E)])
//  Memory Complexity:
//      R = O(N + E)
fun Graph.largestIndependentVertexSet(): Set<Graph.Vertex> {
    val largestSet = mutableSetOf<Graph.Vertex>()
    val checked = mutableSetOf<Graph.Vertex>()
    for (vertex in vertices)
        if (!checked.contains(vertex)) {
            val oddVertices = mutableSetOf<Graph.Vertex>()
            val evenVertices = mutableSetOf<Graph.Vertex>()
            val queue = LinkedList<Pair<Graph.Vertex, Boolean>>()
            queue.offer(vertex to true)
            while (queue.isNotEmpty()) {
                val (current, available) = queue.poll()
                if (available) oddVertices.add(current) else evenVertices.add(current)
                for (neighbor in getNeighbors(current)) if (neighbor !in checked) queue.add(neighbor to !available)
                checked.add(current)
            }
            largestSet.addAll(if (oddVertices.size >= evenVertices.size) oddVertices else evenVertices)
        }

    return largestSet
}

/**
 * Наидлиннейший простой путь.
 * Сложная
 *
 * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
 * Простым считается путь, вершины в котором не повторяются.
 * Если таких путей несколько, вернуть любой из них.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ: A, E, J, K, D, C, H, G, B, F, I
 */
// N - count of vertices
//  Time Complexity:
//      T = O(N^2)
//  Memory Complexity:
//      R = O(N)
fun Graph.longestSimplePath(): Path {
    val listOfPaths = LinkedList<Path>()
    var longestPath = Path()
    for (vertex in vertices) listOfPaths.offer(Path(vertex))
    while (listOfPaths.isNotEmpty()) {
        val path = listOfPaths.poll()
        if (path.length > longestPath.length) longestPath = path
        for (neighbour in getNeighbors(path.vertices[path.length]))
            if (!path.contains(neighbour)) listOfPaths.offer(Path(path, this, neighbour))
    }
    return longestPath
}