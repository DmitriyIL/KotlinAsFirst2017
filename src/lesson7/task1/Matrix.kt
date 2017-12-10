@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}


/**
 * Простая
 *s
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> =
        if (height <= 0 || width <= 0) throw IllegalArgumentException()
        else MatrixImpl(width, height, e)

fun <E> createMatrix(values: List<List<E>>): Matrix<E> {
    val matrix = createMatrix(values.size, values[0].size, values[0][0])
    for (row in 0 until values.size) {
        for (column in 0 until values[0].size) {
            matrix[row, column] = values[row][column]
        }
    }
    return matrix
}
/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val width: Int, override val height: Int, e: E) : Matrix<E> {

    private val matrix = MutableList(height) { MutableList(width) { e } }

    override fun get(row: Int, column: Int): E  = matrix[row][column]

    override fun get(cell: Cell): E  = matrix[cell.row][cell.column]

    override fun set(row: Int, column: Int, value: E) {
        matrix[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        matrix[cell.row][cell.column] = value
    }

    override fun equals(other: Any?): Boolean {
        if (other is Matrix<*> && height == other.height && width == other.width)
        for (row in 0 until height)
            for (column in 0 until width)
                if (this[row, column] != other[row, column]) return false
        return true
    }

    override fun toString(): String {
        val str = StringBuilder("")
        for (i in 0 until height) {
            if (i != 0) str.append("\n")
            for (j in 0 until width) str.append(String.format("%2d", this[i, j]), ' ')
        }
        return str.toString()

    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + matrix.hashCode()
        return result
    }
}

