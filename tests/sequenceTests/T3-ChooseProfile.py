from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import commands
import sys
import os
# Prerequisites:
# - the main menu screen is displayed
# - there are two users stored, the name of the one above consists
#   of minimum 4 letters
# - the bottom user is active, the one above will be activated
# Device display resolution: 960x540

#define methods
def calcWidth(width): return int(int(displayWidth) * int(width) / int(960))
def calcHeight(height): return int(int(displayHeight) * int(height) / int(540))

#start test
print "Starting test sequence T3-Choose Profile"
device = MonkeyRunner.waitForConnection()
displayWidth = device.getProperty("display.width")
displayHeight = device.getProperty("display.height")
print "Clicking the 'user' button"
device.touch (calcWidth(900), calcHeight(340), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the username above"
device.touch (calcWidth(460), calcHeight(260), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'choose user' button"
device.touch (calcWidth(800), calcHeight(480), "DOWN_AND_UP")
MonkeyRunner.sleep(1)

print "Finished test sequence"