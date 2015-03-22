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
print "Clicking the 'exit' button"
device.touch (calcWidth(50), calcHeight(90), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 'OK' button"
device.touch (calcWidth(360), calcHeight(300), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Finished test sequence"