from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import commands
import sys
import os
# Prerequisites:
# - the main menu screen is displayed
# - there are two users stored
# The user above will be deleted.
# Device display resolution: 960x540

#define methods
def calcWidth(width): return int(int(displayWidth) * int(width) / int(960))
def calcHeight(height): return int(int(displayHeight) * int(height) / int(540))

#start test
print "Starting test sequence T4-DeleteUser"
device = MonkeyRunner.waitForConnection()
displayWidth = device.getProperty("display.width")
displayHeight = device.getProperty("display.height")
print "Clicking the 'user' button"
device.touch (calcWidth(900), calcHeight(340), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the username above"
device.touch (calcWidth(460), calcHeight(260), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 'delete' button"
device.touch (calcWidth(145), calcHeight(505), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 'OK' button"
device.touch (calcWidth(340), calcHeight(320), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Test finished"