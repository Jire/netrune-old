package org.jire.netrune.common

import org.jire.netrune.common.Steps.Step

class MutableSteps : Steps {

    private val steps: MutableList<Step> = ArrayList()

    fun addStep(step: Step) {
        steps.add(step)
    }

    inline fun completed(crossinline isDone: () -> Boolean) =
        addStep { isDone() }

    inline fun done(crossinline run: () -> Unit) = completed {
        run()
        true
    }

    inline fun completable(crossinline run: Steps.CompletableStep.() -> Unit) =
        addStep(object : Steps.CompletableStep() {
            override fun isDone(): Boolean {
                run()
                return super.isDone()
            }
        })

    override lateinit var step: Step
    var stepIndex = 0

    override fun next() {
        val nextIndex = ++stepIndex
        step = if (nextIndex < steps.size)
            steps[nextIndex]
        else Steps.Done
    }

    override fun run() {
        if (stepIndex == 0 || step == Steps.Done) {
            step = steps[0]
        }

        super.run()
    }

}

inline fun mutableSteps(build: MutableSteps.() -> Unit): MutableSteps =
    MutableSteps()
        .apply(build)

/*inline fun steps(build: MutableSteps.() -> Unit): Steps =
    mutableSteps(build)*/
