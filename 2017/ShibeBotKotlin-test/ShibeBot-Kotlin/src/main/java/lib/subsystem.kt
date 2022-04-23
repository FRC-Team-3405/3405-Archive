package lib

import edu.wpi.first.wpilibj.command.Subsystem

/**
 * Created by ryanberger on 11/3/17.
 */

class BaseSubSystem(val c: BaseCommand = command {}, block: Subsystem.() -> Unit): Subsystem() {
    override fun initDefaultCommand() {
        defaultCommand = c
    }
}

fun subsystem(c: BaseCommand = command {}, block: Subsystem.() -> Unit): BaseSubSystem {
    return BaseSubSystem(c, block)
}