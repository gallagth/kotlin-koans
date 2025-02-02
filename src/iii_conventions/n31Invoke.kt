package iii_conventions

import util.TODO


class Invokable(private var invocations: Int = 0) : () -> Invokable {
    override fun invoke(): Invokable {
        this.invocations++
        return this
    }

    fun getNumberOfInvocations() = invocations
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}
