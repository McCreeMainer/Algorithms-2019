@file:Suppress("UNUSED_PARAMETER")

package lesson1

import java.io.File
import java.io.IOException
import java.time.DateTimeException
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import kotlin.random.Random

/**
 * Сортировка времён
 *
 * Простая
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
 * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
 *
 * Пример:
 *
 * 01:15:19 PM
 * 07:26:57 AM
 * 10:00:03 AM
 * 07:56:14 PM
 * 01:15:19 PM
 * 12:40:31 AM
 *
 * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
 * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
 *
 * 12:40:31 AM
 * 07:26:57 AM
 * 10:00:03 AM
 * 01:15:19 PM
 * 01:15:19 PM
 * 07:56:14 PM
 *
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
//  N - count of lines
//  Time Complexity:
//      T = O(N) + O(N - 1) * O(N - 1) = O(N^2)
//  Memory Complexity:
//      R = O(N)
fun sortTimes(inputName: String, outputName: String) {
    try {
        val sort = File(inputName).readLines().toMutableList()
        require(sort.isNotEmpty())
        val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
        for (i in 1 until sort.size) {
            val sorting = formatter.parse(sort[i])
            var j = i
            while (j > 0) {
                val prev = formatter.parse(sort[j - 1])
                if (
                    (12 * prev.get(ChronoField.AMPM_OF_DAY) + prev.get(ChronoField.HOUR_OF_AMPM)) * 3600
                    + prev.get(ChronoField.MINUTE_OF_HOUR) * 60
                    + prev.get(ChronoField.SECOND_OF_MINUTE)
                    > (12 * sorting.get(ChronoField.AMPM_OF_DAY) + sorting.get(ChronoField.HOUR_OF_AMPM)) * 3600
                    + sorting.get(ChronoField.MINUTE_OF_HOUR) * 60
                    + sorting.get(ChronoField.SECOND_OF_MINUTE)
                ) {
                    val current = sort[j]
                    sort[j] = sort[j - 1]
                    sort[j - 1] = current
                    j--
                } else break
            }
        }
        File(outputName).writeText(sort.joinToString("\n"))
    } catch (ex: IOException) {
        throw ex
    } catch (ex: IllegalArgumentException) {
        throw ex
    } catch (ex: DateTimeException) {
        throw ex
    }
}

/**
 * Сортировка адресов
 *
 * Средняя
 *
 * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
 * где они прописаны. Пример:
 *
 * Петров Иван - Железнодорожная 3
 * Сидоров Петр - Садовая 5
 * Иванов Алексей - Железнодорожная 7
 * Сидорова Мария - Садовая 5
 * Иванов Михаил - Железнодорожная 7
 *
 * Людей в городе может быть до миллиона.
 *
 * Вывести записи в выходной файл outputName,
 * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
 * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
 *
 * Железнодорожная 3 - Петров Иван
 * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
 * Садовая 5 - Сидоров Петр, Сидорова Мария
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun sortAddresses(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сортировка температур
 *
 * Средняя
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
 * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
 * Например:
 *
 * 24.7
 * -12.6
 * 121.3
 * -98.4
 * 99.5
 * -12.6
 * 11.0
 *
 * Количество строк в файле может достигать ста миллионов.
 * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
 * Повторяющиеся строки сохранить. Например:
 *
 * -98.4
 * -12.6
 * -12.6
 * 11.0
 * 24.7
 * 99.5
 * 121.3
 */
fun sortTemperatures(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сортировка последовательности
 *
 * Средняя
 * (Задача взята с сайта acmp.ru)
 *
 * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
 *
 * 1
 * 2
 * 3
 * 2
 * 3
 * 1
 * 2
 *
 * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
 * а если таких чисел несколько, то найти минимальное из них,
 * и после этого переместить все такие числа в конец заданной последовательности.
 * Порядок расположения остальных чисел должен остаться без изменения.
 *
 * 1
 * 3
 * 3
 * 1
 * 2
 * 2
 * 2
 */
//  N - count of lines
//  Time Complexity:
//      T = O(N) + O(N) + O(log N) + O(N) + O(N) + O(N) = O(N)
//  Memory Complexity:
//      R = O(N)
fun sortSequence(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    try {
        val sort = File(inputName).readLines().map { it.toInt() }
        require(sort.isNotEmpty())
        val counts = mutableMapOf<Int, Int>()
        sort.forEach { counts[it] = counts[it]?.plus(1) ?: 1 }
        val maxVal = counts.toSortedMap().maxBy { it.value }
        sort.forEach {
            if (it != maxVal!!.key) {
                writer.write("$it")
                writer.newLine()
            }
        }
        repeat(maxVal?.value ?: 0) {
            writer.write(maxVal!!.key.toString())
            writer.newLine()
        }
    } catch (ex: IOException) {
        throw ex
    } catch (ex: IllegalArgumentException) {
        throw ex
    } finally {
        writer.close()
    }
}

/**
 * Соединить два отсортированных массива в один
 *
 * Простая
 *
 * Задан отсортированный массив first и второй массив second,
 * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
 * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
 *
 * first = [4 9 15 20 28]
 * second = [null null null null null 1 3 9 13 18 23]
 *
 * Результат: second = [1 3 4 9 9 13 15 20 23 28]
 */
fun <T : Comparable<T>> mergeArrays(first: Array<T>, second: Array<T?>) {
    TODO()
}

fun main(args: Array<String>) {
    sortSequence("input/seq_in7.txt", "input/seq_out7.txt")
}
