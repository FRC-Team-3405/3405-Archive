package lib

import com.sun.xml.internal.rngom.parse.host.Base
import edu.wpi.first.wpilibj.IterativeRobot

/**
 * Created by ryanberger on 11/3/17.
 */
abstract class BaseRobot(builder: BaseRobot.() -> Unit) : IterativeRobot() {
    private var _onInit: (BaseRobot.() -> Unit)? = null
    private var _disabled: (BaseRobot.() -> Unit)? = null
    private var _autonomous: (BaseRobot.() -> Unit)? = null
    private var _teleop: (BaseRobot.() -> Unit)? = null

    init {
        builder()
    }

    override fun robotInit() {
        _onInit?.invoke(this)
    }

    override fun disabledInit() {
        _disabled?.invoke(this)
    }
    override fun autonomousInit() {
        _autonomous?.invoke(this)
    }
    override fun teleopInit() {
        _teleop?.invoke(this)
    }

    fun onInit(init: BaseRobot.() -> Unit) { this._onInit = init }
    fun disabled(init: BaseRobot.() -> Unit) { this._disabled = init }
    fun autonomous(init: BaseRobot.() -> Unit) { this._autonomous = init }
    fun teleop(init: BaseRobot.() -> Unit) { this._teleop = init }
}