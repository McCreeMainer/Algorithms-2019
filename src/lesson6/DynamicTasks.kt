@file:Suppress("UNUSED_PARAMETER")

package lesson6

import java.io.File
import java.io.IOException
import kotlin.collections.ArrayList
import kotlin.math.min

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {
    TODO()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
// N - size of list
//  Time Complexity:
//      T = O(N) + O(N) + O(N) = O(N)
//  Memory Complexity:
//      R = O(N) + O(N)
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isEmpty()) return listOf()
    val length = Array(list.size) { 1 }
    val prev = Array(list.size) { -1 }
    for (i in 1 until list.size) {
        for (j in 0 until i) {
            if (list[j] < list[i] && length[i] <= length[j]) {
                prev[i] = j
                length[i] = length[j] + 1
            }
        }
    }
    val longestSequence = mutableListOf<Int>()
    var i = 0
    for (l in 1 until length.size)
        if (length[l] > length[i]) i = l
    while (i >= 0) {
        longestSequence.add(list[i])
        i = prev[i]
    }
    return longestSequence.reversed()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
// H - height of matrix
// W - width of matrix
//  Time Complexity:
//      T = O(W) + O(H * W) = O(H * W)
//  Memory Complexity:
//      R = O(H * W)
fun shortestPathOnField(inputName: String): Int {
    try {
        val field = ArrayList<ArrayList<Int>>()
        File(inputName).readLines().forEach { field.add(ArrayList(it.split(' ').map { init -> init.toInt() })) }
        val width = field.first().size
        for (i in 1 until width) field[0][i] += field[0][i - 1]
        for (i in 1 until field.size) {
            field[i][0] += field[i - 1][0]
            for (j in 1 until width)
                field[i][j] = min(
                    min(
                        field[i][j] + field[i - 1][j],
                        field[i][j] + field[i][j - 1]
                    ),
                    field[i][j] + field[i - 1][j - 1]
                )
        }
        return field[field.size - 1][width - 1]
    } catch (e: IOException) {
        return 0
    }
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5