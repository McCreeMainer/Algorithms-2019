package lesson3

import java.lang.IllegalArgumentException
import java.util.*
import kotlin.NoSuchElementException
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.math.max

// Attention: comparable supported but comparator is not
open class KtBinaryTree<T : Comparable<T>> : AbstractMutableSet<T>(), CheckableSortedSet<T> {

    private var root: Node<T>? = null

    override var size = 0
//        private set

    private class Node<T>(val value: T) {

        var left: Node<T>? = null

        var right: Node<T>? = null
    }

    override fun add(element: T): Boolean {
        val closest = find(element)
        val comparison = if (closest == null) -1 else element.compareTo(closest.value)
        if (comparison == 0) {
            return false
        }
        val newNode = Node(element)
        when {
            closest == null -> root = newNode
            comparison < 0 -> {
                assert(closest.left == null)
                closest.left = newNode
            }
            else -> {
                assert(closest.right == null)
                closest.right = newNode
            }
        }
        size++
        return true
    }

//    override fun addAll(collection: Collection<T>): Boolean {
//
//    }

    override fun checkInvariant(): Boolean =
        root?.let { checkInvariant(it) } ?: true

    override fun height(): Int = height(root)

    private fun checkInvariant(node: Node<T>): Boolean {
        val left = node.left
        if (left != null && (left.value >= node.value || !checkInvariant(left))) return false
        val right = node.right
        return right == null || right.value > node.value && checkInvariant(right)
    }

    private fun height(node: Node<T>?): Int {
        if (node == null) return 0
        return 1 + max(height(node.left), height(node.right))
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    override fun remove(element: T): Boolean {
        TODO()
    }

    override operator fun contains(element: T): Boolean {
        val closest = find(element)
        return closest != null && element.compareTo(closest.value) == 0
    }

    private fun find(value: T): Node<T>? =
        root?.let { find(it, value) }

    private fun find(start: Node<T>, value: T): Node<T> {
        val comparison = value.compareTo(start.value)
        return when {
            comparison == 0 -> start
            comparison < 0 -> start.left?.let { find(it, value) } ?: start
            else -> start.right?.let { find(it, value) } ?: start
        }
    }

    inner class BinaryTreeIterator internal constructor() : MutableIterator<T> {

        private var current: Node<T>? = null

        //  N - count of nodes
        //  Time Complexity:
        //      T = O(N)
        //  Memory Complexity:
        //      R = O(1)
        private fun findNext(): Node<T>? {
            if (root == null) return null
            current?.let {
                if (it.value == last()) return null
                if (it.right != null) {
                    var result = it.right ?: throw NoSuchElementException()
                    while (result.left != null) {
                        result = result.left ?: return result
                    }
                    return result
                } else {
                    var result = root!!
                    var checkChild = result
                    while (checkChild != it) {
                        if (checkChild.value > it.value) {
                            result = checkChild
                            checkChild = checkChild.left ?: return null
                        } else checkChild = checkChild.right ?: return null
                    }
                    return result
                }
            } ?: return find(first())
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         */
        //  N - count of nodes
        //  Time Complexity:
        //      T = O(N)
        //  Memory Complexity:
        //      R = O(1)
        override fun hasNext(): Boolean = findNext() != null

        /**
         * Поиск следующего элемента
         * Средняя
         */
        //  N - count of nodes
        //  Time Complexity:
        //      T = O(N)
        //  Memory Complexity:
        //      R = O(1)
        override fun next(): T {
            current = findNext()
            return current?.value ?: throw NoSuchElementException()
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        override fun remove() {
            // TODO
            throw NotImplementedError()
        }
    }

    override fun iterator(): MutableIterator<T> = BinaryTreeIterator()

    override fun comparator(): Comparator<in T>? = null

    inner class SubTree<T : Comparable<T>> constructor(
        private val mainTree: KtBinaryTree<T>,
        private val fromElement: T?,
        private val toElement: T?
    ) : KtBinaryTree<T>() {

        override var size: Int = 0
            get() = mainTree.filter { inRange(it) }.size

        private fun inRange(element: T) =
            (fromElement == null || element >= fromElement) && (toElement == null || element < toElement)

        override fun add(element: T): Boolean {
            if (inRange(element)) return mainTree.add(element)
            else throw IllegalArgumentException()
        }

        override fun contains(element: T): Boolean = mainTree.contains(element) && inRange(element)
    }

    /**
     * Найти множество всех элементов в диапазоне [fromElement, toElement)
     * Очень сложная
     */
    //  Time Complexity:
    //      T = O(1)
    //  Memory Complexity:
    //      R = O(1)
    override fun subSet(fromElement: T, toElement: T): SortedSet<T> = SubTree(this, fromElement, toElement)

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    //  Time Complexity:
    //      T = O(1)
    //  Memory Complexity:
    //      R = O(1)
    override fun headSet(toElement: T): SortedSet<T> = SubTree(this, null, toElement)

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    //  Time Complexity:
    //      T = O(1)
    //  Memory Complexity:
    //      R = O(1)
    override fun tailSet(fromElement: T): SortedSet<T> = SubTree(this, fromElement, null)

    //  N - count of nodes
    //  Time Complexity:
    //      T = O(N)
    //  Memory Complexity:
    //      R = O(1)
    override fun first(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    //  N - count of nodes
    //  Time Complexity:
    //      T = O(N)
    //  Memory Complexity:
    //      R = O(1)
    override fun last(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.right != null) {
            current = current.right!!
        }
        return current.value
    }
}
