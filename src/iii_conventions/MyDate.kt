package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year.compareTo(other.year)
        month != other.month -> month.compareTo(other.month)
        else -> dayOfMonth.compareTo(other.dayOfMonth)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)
operator fun MyDate.plus(time: TimeInterval) = this.addTimeIntervals(time, 1)
operator fun MyDate.plus(time: RepeatedTimeInterval) = this.addTimeIntervals(time.ti, time.n)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun TimeInterval.times(n: Int) = RepeatedTimeInterval(this, n)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    operator fun contains(date: MyDate) = start <= date && date <= endInclusive
    override fun iterator() = DateIterator(this)
}

class DateIterator(private val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start

    override fun hasNext() :Boolean {
        return current <= this.dateRange.endInclusive
    }

    override fun next() : MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
}
