#include "ReleaseGround.h"

ReleaseGround::ReleaseGround()
{
	AddSequential(new OpenArm());
	AddSequential(new SetDestinationLevel(0));
}
