#include "ReleaseDriveReverse.h"

const float TIME_TO_BACK = .5;

ReleaseDriveReverse::ReleaseDriveReverse()
{
	AddSequential(new OpenArm());
	AddSequential(new DriveDirectionalCommand(0, -1, 0), TIME_TO_BACK);
}
