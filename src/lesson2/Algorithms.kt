@file:Suppress("UNUSED_PARAMETER")

package lesson2

import sun.invoke.empty.Empty
import java.io.File
import java.io.IOException
import kotlin.math.max
import kotlin.math.min

/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    TODO()
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 *
 * Общий комментарий: решение из Википедии для этой задачи принимается,
 * но приветствуется попытка решить её самостоятельно.
 */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    TODO()
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */
//  F - length of first String
//  S - length of second String
//  Time Complexity:
//      T = O(F * S)
//  Memory Complexity:
//      R = O(F * S)
fun longestCommonSubstring(first: String, second: String): String {
    val subStringMatrix = Array(first.length) { Array(second.length) { 0 } }
    var result = ""
    var i = 0
    while (first.length - 1 > i) {
        var j = 0
        while (second.length - 1 > j) {
            var k = 0
            while (i + k < first.length && j + k < second.length && first[i + k] == second[j + k]) {
                subStringMatrix[i + k][j + k] = k + 1
                k++
            }
            if (k > 0) k--
            if (subStringMatrix[i + k][j + k] > result.length) result = first.substring(i, i + k + 1)
            j += k + 1
        }
        i++
        if (subStringMatrix.size - i < result.length) return result
    }
    return result
}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
fun calcPrimesNumber(limit: Int): Int {
    TODO()
}

/**
 * Балда
 * Сложная
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 */
//  H - count of lines
//  W - length of lines
//  n - count of words
//  w - words length
//  Time Complexity:
//      T = O(H * W) + O(n) + O(H * W * n * (4 * 3^(w - 2))) = O(H * W * n * (4 * 3^(w - 2)))
//  Memory Complexity:
//      R = O(H * W * n * w) + O(n * w)
fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    val result = mutableSetOf<String>()
    try {
        require(words.all { !it.contains("""\s""".toRegex()) })
        val matrixBalda = File(inputName).readLines().map { it.toUpperCase().split(' ').joinToString("") }
        if (matrixBalda.isEmpty() || words.isEmpty()) return emptySet()
        require(matrixBalda.all { it.length == matrixBalda[0].length })
        val wordSet = words.map { it.toUpperCase() }.toMutableSet()
        for (i in matrixBalda.indices) for (j in matrixBalda[i].indices) {
            for (w in wordSet) if (chainStart(matrixBalda, w, i, j)) result.add(w)
            wordSet.removeAll(result)
            if (wordSet.isEmpty()) return result
        }
    } catch (ex: IOException) {
        throw ex
    } catch (ex: IllegalArgumentException) {
        throw ex
    }
    return result
}

fun chainStart(matrix: List<String>, word: String, i: Int, j: Int): Boolean {
    if (matrix[i][j] == word[0]) {
        val map = Array(matrix.size) { Array(matrix[0].length) { true } }
        return chainSearcher(matrix, map, word, 1, i, j)
    }
    return false
}

fun chainSearcher(matrix: List<String>, map: Array<Array<Boolean>>, word: String, ch: Int, i: Int, j: Int): Boolean {
    map[i][j] = false
    if (i + 1 < matrix.size) if (map[i + 1][j] && matrix[i + 1][j] == word[ch]) {
        if (ch + 1 == word.length) return true
        if (chainSearcher(matrix, map, word, ch + 1, i + 1, j)) return true
    }
    if (i - 1 > -1) if (map[i - 1][j] && matrix[i - 1][j] == word[ch]) {
        if (ch + 1 == word.length) return true
        if (chainSearcher(matrix, map, word, ch + 1, i - 1, j)) return true
    }
    if (j + 1 < matrix[i].length) if (map[i][j + 1] && matrix[i][j + 1] == word[ch]) {
        if (ch + 1 == word.length) return true
        if (chainSearcher(matrix, map, word, ch + 1, i, j + 1)) return true
    }
    if (j - 1 > -1) if (map[i][j - 1] && matrix[i][j - 1] == word[ch]) {
        if (ch + 1 == word.length) return true
        if (chainSearcher(matrix, map, word, ch + 1, i, j - 1)) return true
    }
    return false
}