package lib

import edu.wpi.first.wpilibj.command.Command

/**
 * Created by ryanberger on 11/3/17.
 */


class BaseCommand(var finished: Boolean = true, private val _command: BaseCommand.() -> Unit = {}) : Command() {

    override fun isFinished(): Boolean {
        return finished
    }

    override fun execute() {
        super.execute()
        _command()
    }
}


fun command (block: BaseCommand.() -> Unit): BaseCommand {
    return BaseCommand { block() }
}