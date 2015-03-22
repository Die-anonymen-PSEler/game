from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import commands
import sys
import os
# Prerequisites:
# - the main menu screen is displayed
# Device display resolution: 960x540

#define methods
def calcWidth(width): return int(int(displayWidth) * int(width) / int(960))
def calcHeight(height): return int(int(displayHeight) * int(height) / int(540))

#start test
print "Starting test sequence T8-Settings"
device = MonkeyRunner.waitForConnection()
displayWidth = device.getProperty("display.width")
displayHeight = device.getProperty("display.height")
print "Clicking the 'settings' button"
device.touch (calcWidth(840), calcHeight(100), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 'louder' button"
device.touch (calcWidth(860), calcHeight(290), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'sound on/off' button"
device.touch (calcWidth(80), calcHeight(300), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'back' button"
device.touch (calcWidth(50), calcHeight(100), "DOWN_AND_UP")
MonkeyRunner.sleep(1)

print "Finished test sequence"