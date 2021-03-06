from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import commands
import sys
import os
# Prerequisites:
# - the main menu screen is displayed
# the information will be displayed.
# Device display resolution: 960x540

#define methods
def calcWidth(width): return int(int(displayWidth) * int(width) / int(960))
def calcHeight(height): return int(int(displayHeight) * int(height) / int(540))

#start test
print "Starting test sequence T6-ReadInfos"
device = MonkeyRunner.waitForConnection()
displayWidth = device.getProperty("display.width")
displayHeight = device.getProperty("display.height")
print "Clicking the 'info' button"
device.touch (calcWidth(860), calcHeight(245), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Test finished"