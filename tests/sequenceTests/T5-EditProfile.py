from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import commands
import sys
import os
# Prerequisites:
# - the main menu screen is displayed
# - there is min one user stored
# The active user will change his avatar and his 
# control to lefthand modus.
# Device display resolution: 960x540

#define methods
def calcWidth(width): return int(int(displayWidth) * int(width) / int(960))
def calcHeight(height): return int(int(displayHeight) * int(height) / int(540))

#start test
print "Starting test sequence T5-EditProfile"
device = MonkeyRunner.waitForConnection()
displayWidth = device.getProperty("display.width")
displayHeight = device.getProperty("display.height")
print "Clicking the 'settings' button"
device.touch (calcWidth(845), calcHeight(80), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 'userspecific settings' button"
device.touch (calcWidth(780), calcHeight(480), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 'change avatar' button"
device.touch (calcWidth(70), calcHeight(250), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 'lefthand control' button"
device.touch (calcWidth(600), calcHeight(260), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking on the 'OK' button"
device.touch (calcWidth(500), calcHeight(500), "DOWN_AND_UP")
MonkeyRunner.sleep(1)

print "Test finished"