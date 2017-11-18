@file:Suppress("UNUSED_PARAMETER")
package lesson7.task2

import lesson7.task1.Cell
import lesson7.task1.Matrix
import lesson7.task1.createMatrix
import java.lang.Math.*

// Все задачи в этом файле требуют наличия реализации интерфейса "Матрица" в Matrix.kt

/**
 * Пример
 *
 * Транспонировать заданную матрицу matrix.
 * При транспонировании строки матрицы становятся столбцами и наоборот:
 *
 * 1 2 3      1 4 6 3
 * 4 5 6  ==> 2 5 5 2
 * 6 5 4      3 6 4 1
 * 3 2 1
 */
fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0 until matrix.width) {
        for (j in 0 until matrix.height) {
            result[i, j] = matrix[j, i]
        }
    }
    return result
}

/**
 * Пример
 *
 * Сложить две заданные матрицы друг с другом.
 * Складывать можно только матрицы совпадающего размера -- в противном случае бросить IllegalArgumentException.
 * При сложении попарно складываются соответствующие элементы матриц
 */
operator fun Matrix<Int>.plus(other: Matrix<Int>): Matrix<Int> {
    if (width != other.width || height != other.height) throw IllegalArgumentException()
    if (width < 1 || height < 1) return this
    val result = createMatrix(height, width, this[0, 0])
    for (i in 0 until height) {
        for (j in 0 until width) {
            result[i, j] = this[i, j] + other[i, j]
        }
    }
    return result
}

/**
 * Сложная
 *
 * Заполнить матрицу заданной высоты height и ширины width
 * натуральными числами от 1 до m*n по спирали,
 * начинающейся в левом верхнем углу и закрученной по часовой стрелке.
 *
 * Пример для height = 3, width = 4:
 *  1  2  3  4
 * 10 11 12  5
 *  9  8  7  6
 */

fun generateSpiral(height: Int, width: Int): Matrix<Int> {
    val result = createMatrix(height, width, 0)
    var passedCircles = 0
    var counter = 0
    val countLim = height * width
    do {
        for (column in passedCircles until width - passedCircles) {
            result[passedCircles, column] = ++counter
        }
        for (row in 1 + passedCircles until height - passedCircles) {
            result[row, width - 1 - passedCircles] = ++counter
        }
        for (column in width - 2 - passedCircles downTo 0 + passedCircles) {
            if (counter < countLim) result[height - 1 - passedCircles, column] = ++counter
        }
        for (row in height - 2 - passedCircles downTo 1 + passedCircles) {
            if (counter < countLim) result[row, 0 + passedCircles] = ++counter
        }
        passedCircles++
    } while (counter < countLim)
    return result
}
/**
 * Сложная
 *
 * Заполнить матрицу заданной высоты height и ширины width следующим образом.
 * Элементам, находящимся на периферии (по периметру матрицы), присвоить значение 1;
 * периметру оставшейся подматрицы – значение 2 и так далее до заполнения всей матрицы.
 *
 * Пример для height = 5, width = 6:
 *  1  1  1  1  1  1
 *  1  2  2  2  2  1
 *  1  2  3  3  2  1
 *  1  2  2  2  2  1
 *  1  1  1  1  1  1
 */


fun generateRectangles(height: Int, width: Int): Matrix<Int> {
    val result = createMatrix(height, width, 0)
    var passedCircles = 0
    val circlesLim = min(height, width) / 2 + min(height, width) % 2
    do {
        for (column in passedCircles until width - passedCircles) {
            result[passedCircles, column] = passedCircles + 1
        }
        for (row in 1 + passedCircles until height - passedCircles) {
            result[row, width - 1 - passedCircles] = passedCircles + 1
        }
        for (column in width - 2 - passedCircles downTo 0 + passedCircles) {
            result[height - 1 - passedCircles, column] = passedCircles + 1
        }
        for (row in height - 2 - passedCircles downTo 1 + passedCircles) {
            result[row, 0 + passedCircles] = passedCircles + 1
        }
        passedCircles++
    } while (passedCircles < circlesLim)
    return result
}
/**
 * Сложная
 *
 * Заполнить матрицу заданной высоты height и ширины width диагональной змейкой:
 * в левый верхний угол 1, во вторую от угла диагональ 2 и 3 сверху вниз, в третью 4-6 сверху вниз и так далее.
 *
 * Пример для height = 5, width = 4:
 *  1  2  4  7
 *  3  5  8 11
 *  6  9 12 15
 * 10 13 16 18
 * 14 17 19 20
 */

fun generateSnake(height: Int, width: Int): Matrix<Int>{
    val result = createMatrix(height, width, 0)
    var counter = 0
    for (column in 0 until width)
        for (row in 0 until min(height, column + 1))
            result[row, column - row] = ++counter
    for (row in 1 until height)
        for (column in 0 until min(width, height - row))
            result[row + column, width - 1 - column] = ++counter
    return result
}


/**
 * Средняя
 *
 * Содержимое квадратной матрицы matrix (с произвольным содержимым) повернуть на 90 градусов по часовой стрелке.
 * Если height != width, бросить IllegalArgumentException.
 *
 * Пример:    Станет:
 * 1 2 3      7 4 1
 * 4 5 6      8 5 2
 * 7 8 9      9 6 3
 */

fun <E> rotate(matrix: Matrix<E>): Matrix<E> {
    if (matrix.height != matrix.width) throw IllegalArgumentException()
    val size = matrix.height
    val result = createMatrix(size, size, matrix[0, 0])
    for (row in 0 until size)
        for (column in 0 until size)
            result[row, column] = matrix[row, column]
    for (row in 0 until size){
        for (column in 0 until size)
            result[column, size - 1 - row] = matrix[row, column]
    }
    return result
}

/**
 * Сложная
 *
 * Проверить, является ли квадратная целочисленная матрица matrix латинским квадратом.
 * Латинским квадратом называется матрица размером n x n,
 * каждая строка и каждый столбец которой содержат все числа от 1 до n.
 * Если height != width, вернуть false.
 *
 * Пример латинского квадрата 3х3:
 * 2 3 1
 * 1 2 3
 * 3 1 2
 */
fun isLatinSquare(matrix: Matrix<Int>): Boolean {
    if (matrix.height != matrix.width) throw IllegalArgumentException()
    val rotateMatrix = rotate(matrix)
    val size = matrix.height
        for (row in 0 until size) {
            val visited = mutableListOf<Int>()
            val rotateVisited = mutableListOf<Int>()
            for (column in 0 until size) {
                val value = matrix[row, column]
                if (value !in visited && value in 1..size)
                    visited.add(matrix[row, column])
                else return false
                val rotateValue = rotateMatrix[row, column]
                if (rotateValue !in rotateVisited && rotateValue in 1..size)
                    rotateVisited.add(rotateMatrix[row, column])
                else return false
            }
        }
    return true
}

/**
 * Средняя
 *
 * В матрице matrix каждый элемент заменить суммой непосредственно примыкающих к нему
 * элементов по вертикали, горизонтали и диагоналям.
 *
 * Пример для матрицы 4 x 3: (11=2+4+5, 19=1+3+4+5+6, ...)
 * 1 2 3       11 19 13
 * 4 5 6  ===> 19 31 19
 * 6 5 4       19 31 19
 * 3 2 1       13 19 11
 *
 * Поскольку в матрице 1 х 1 примыкающие элементы отсутствуют,
 * для неё следует вернуть как результат нулевую матрицу:
 *
 * 42 ===> 0
 */

fun sumNeighbours(matrix: Matrix<Int>): Matrix<Int> {
    val height = matrix.height
    val width = matrix.width
    val result = createMatrix(height, width, 0)
    for (row in 0 until  height)
        for (column in 0 until width)
            for (a in -1..1)
                for (b in -1..1)
                    if ((a != 0 || b != 0) && row + a in 0 until height && column + b in 0 until width)
                        result[row + a, column + b] += matrix[row, column]
return result
}

/**
 * Средняя
 *
 * Целочисленная матрица matrix состоит из "дырок" (на их месте стоит 0) и "кирпичей" (на их месте стоит 1).
 * Найти в этой матрице все ряды и колонки, целиком состоящие из "дырок".
 * Результат вернуть в виде Holes(rows = список дырчатых рядов, columns = список дырчатых колонок).
 * Ряды и колонки нумеруются с нуля. Любой из спискоов rows / columns может оказаться пустым.
 *
 * Пример для матрицы 5 х 4:
 * 1 0 1 0
 * 0 0 1 0
 * 1 0 0 0 ==> результат: Holes(rows = listOf(4), columns = listOf(1, 3)): 4-й ряд, 1-я и 3-я колонки
 * 0 0 1 0
 * 0 0 0 0
 */
fun findHoles(matrix: Matrix<Int>): Holes {
    var rows = listOf<Int>()
    var columns = listOf<Int>()
    for (row in 0 until matrix.height) {
        var sum = 0
        for (column in 0 until matrix.width)
            sum += matrix[row, column]
        if (sum == 0) rows += row
    }
    for (column in 0 until matrix.width) {
        var sum = 0
        for (row in 0 until matrix.height)
            sum += matrix[row, column]
        if (sum == 0) columns += column
    }
    return Holes(rows, columns)
}

/**
 * Класс для описания местонахождения "дырок" в матрице
 */
data class Holes(val rows: List<Int>, val columns: List<Int>)

/**
 * Средняя
 *
 * В целочисленной матрице matrix каждый элемент заменить суммой элементов подматрицы,
 * расположенной в левом верхнем углу матрицы matrix и ограниченной справа-снизу данным элементом.
 *
 * Пример для матрицы 3 х 3:
 *
 * 1  2  3      1  3  6
 * 4  5  6  =>  5 12 21
 * 7  8  9     12 27 45
 *
 * К примеру, центральный элемент 12 = 1 + 2 + 4 + 5, элемент в левом нижнем углу 12 = 1 + 4 + 7 и так далее.
 */
fun sumSubMatrix(matrix: Matrix<Int>): Matrix<Int> {
    val result = createMatrix(matrix.height, matrix.width, 0)
    for (row in 0 until matrix.height)
        for (column in 0 until matrix.width) {
            var sum = 0
            for (i in 0..row)
                for (j in 0..column)
                    sum += matrix[i, j]
            result[row, column] = sum
    }
    return result
}
/**
 * Сложная
 *
 * Даны мозаичные изображения замочной скважины и ключа. Пройдет ли ключ в скважину?
 * То есть даны две матрицы key и lock, key.height <= lock.height, key.width <= lock.width, состоящие из нулей и единиц.
 *
 * Проверить, можно ли наложить матрицу key на матрицу lock (без поворота, разрешается только сдвиг) так,
 * чтобы каждой единице в матрице lock (штырь) соответствовал ноль в матрице key (прорезь),
 * а каждому нулю в матрице lock (дырка) соответствовала, наоборот, единица в матрице key (штырь).
 * Ключ при сдвиге не может выходить за пределы замка.
 *
 * Пример: ключ подойдёт, если его сдвинуть на 1 по ширине
 * lock    key
 * 1 0 1   1 0
 * 0 1 0   0 1
 * 1 1 1
 *
 * Вернуть тройку (Triple) -- (да/нет, требуемый сдвиг по высоте, требуемый сдвиг по ширине).
 * Если наложение невозможно, то первый элемент тройки "нет" и сдвиги могут быть любыми.
 */
fun main(args: Array<String>) {
    println(canOpenLock(key = createMatrix(listOf(listOf(0), listOf(0))),
            lock = createMatrix(listOf(listOf(1), listOf(1)))))
}


fun canOpenLock(key: Matrix<Int>, lock: Matrix<Int>): Triple<Boolean, Int, Int> {
    val invertKey = invertKey(key)
    for (rowShift in 0..lock.height - key.height) {
        for (columnShift in 0..lock.width - key.width) {
            val subMatrix = createSubMatrix(lock,
                    Cell(rowShift, columnShift),
                    Cell(key.height - 1 + rowShift, key.width - 1 + columnShift))
            if (subMatrix == invertKey) return Triple(true, rowShift, columnShift)
        }
    }
    return Triple(false, 0, 0)
}

fun invertKey(key: Matrix<Int>): Matrix<Int> {
    for (i in 0 until key.height)
        for (j in 0 until key.width)
            key[i, j] = if (key[i, j] == 1) 0 else 1
    return key
}


fun <E> createSubMatrix(matrix: Matrix<E>, start: Cell, end: Cell): Matrix<E> {
    val result = createMatrix(end.row - start.row + 1,
            end.column - start.column + 1,
            matrix[0, 0])
    for (row in 0 until result.height)
        for (column in 0 until result.width)
            result[row, column] = matrix[row + start.row, column + start.column]
    return result
}
/**
 * Простая
 *
 * Инвертировать заданную матрицу.
 * При инвертировании знак каждого элемента матрицы следует заменить на обратный
 */
operator fun Matrix<Int>.unaryMinus(): Matrix<Int> {
    for (row in 0 until height)
        for (column in 0 until width)
            this[row,column] *= -1
    return this
}

/**
 * Средняя
 *
 * Перемножить две заданные матрицы друг с другом.
 * Матрицы можно умножать, только если ширина первой матрицы совпадает с высотой второй матрицы.
 * В противном случае бросить IllegalArgumentException.
 * Подробно про порядок умножения см. статью Википедии "Умножение матриц".
 */
operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> {
    if (width != other.height) throw IllegalArgumentException()
    val result = createMatrix(height, other.width, 0)
    for (row in 0 until height)
        for (column in 0 until other.width) {
        var sum = 0
        for (i in 0 until width)
            sum += this[row, i] * other[i, column]
        result[row, column] = sum
    }
    return result
}
    /**
 * Сложная
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  1
 *  2 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой. Цель игры -- упорядочить фишки на игровом поле.
 *
 * В списке moves задана последовательность ходов, например [8, 6, 13, 11, 10, 3].
 * Ход задаётся номером фишки, которая передвигается на пустое место (то есть, меняется местами с нулём).
 * Фишка должна примыкать к пустому месту по горизонтали или вертикали, иначе ход не будет возможным.
 * Все номера должны быть в пределах от 1 до 15.
 * Определить финальную позицию после выполнения всех ходов и вернуть её.
 * Если какой-либо ход является невозможным или список содержит неверные номера,
 * бросить IllegalStateException.
 *
 * В данном случае должно получиться
 * 5  7  9  1
 * 2 12 14 15
 * 0  4 13  6
 * 3 10 11  8
 */

fun fifteenGameMoves(matrix: Matrix<Int>, moves: List<Int>): Matrix<Int> {
    for (el in moves)
        matrix.swap(el, 0) ?: throw IllegalStateException()
    return matrix
}

fun Matrix<Int>.swap(a: Int, b: Int): Matrix<Int>?{
    val square1 = find(a) ?: return null
    val square2 = find(b) ?: return null
    if (abs(square1.column - square2.column) > 1 || abs(square1.column - square2.column) > 1)
        return null
    val element = this[square1]
    this[square1] = this[square2]
    this[square2] = element
    return this
}

fun Matrix<Int>.find(element: Int): Cell? {
    for (row in 0 until height)
        for (column in 0 until width)
            if (element == this[row, column]) return Cell(row, column)
    return null
}
/**
 * Очень сложная
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  2
 *  1 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой.
 *
 * Цель игры -- упорядочить фишки на игровом поле, приведя позицию к одному из следующих двух состояний:
 *
 *  1  2  3  4          1  2  3  4
 *  5  6  7  8   ИЛИ    5  6  7  8
 *  9 10 11 12          9 10 11 12
 * 13 14 15  0         13 15 14  0
 *
 * Можно математически доказать, что РОВНО ОДНО из этих двух состояний достижимо из любой исходной позиции.
 *
 * Вернуть решение -- список ходов, приводящих исходную позицию к одной из двух упорядоченных.
 * Каждый ход -- это перемена мест фишки с заданным номером с пустой клеткой (0),
 * при этом заданная фишка должна по горизонтали или по вертикали примыкать к пустой клетке (но НЕ по диагонали).
 * К примеру, ход 13 в исходной позиции меняет местами 13 и 0, а ход 11 в той же позиции невозможен.
 *
 * Одно из решений исходной позиции:
 *
 * [8, 6, 14, 12, 4, 11, 13, 14, 12, 4,
 * 7, 5, 1, 3, 11, 7, 3, 11, 7, 12, 6,
 * 15, 4, 9, 2, 4, 9, 3, 5, 2, 3, 9,
 * 15, 8, 14, 13, 12, 7, 11, 5, 7, 6,
 * 9, 15, 8, 14, 13, 9, 15, 7, 6, 12,
 * 9, 13, 14, 15, 12, 11, 10, 9, 13, 14,
 * 15, 12, 11, 10, 9, 13, 14, 15]
 *
 * Перед решением этой задачи НЕОБХОДИМО решить предыдущую
 */
fun fifteenGameSolution(matrix: Matrix<Int>): List<Int> = TODO()
