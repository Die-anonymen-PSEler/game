from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import commands
import sys
import os
# Prerequisites:
# - the main menu screen is displayed
# - only the 1st level is unlocked for the active user
# Device display resolution: 960x540

#define methods
def calcWidth(width): return int(int(displayWidth) * int(width) / int(960))
def calcHeight(height): return int(int(displayHeight) * int(height) / int(540))

#start test
print "Starting test sequence T7-LevelMenu"
device = MonkeyRunner.waitForConnection()
displayWidth = device.getProperty("display.width")
displayHeight = device.getProperty("display.height")
print "Clicking the 'play' button"
device.touch (calcWidth(470), calcHeight(330), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 2nd level button"
device.touch (calcWidth(300), calcHeight(320), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
# the 2nd level is not unlocked so there should appear a dialog that it's not
print "Clicking on the 'OK' button"
device.touch (calcWidth(480), calcHeight(325), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 1st level button"
device.touch (calcWidth(135), calcHeight(310), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
# now the 1st level should open because it is unlocked.
print "Test finished"