package org.jire.netrune.common

interface Steps {

    fun interface Step {
        fun isDone(): Boolean
    }

    abstract class CompletableStep : Step {
        private var done = false

        fun done() {
            done = true
        }

        override fun isDone() = done
    }

    object Done : Step {
        override fun isDone() = false
    }

    var step: Step

    fun next()

    fun run() {
        while (step.isDone()) {
            next()
        }
    }

}
