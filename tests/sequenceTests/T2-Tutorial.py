from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import commands
import sys
import os
# Prerequisites:
# - the main menu screen is displayed
# - the user did not see the tutorial
# Device display resolution: 960x540

#define methods
def calcWidth(width): return int(int(displayWidth) * int(width) / int(960))
def calcHeight(height): return int(int(displayHeight) * int(height) / int(540))

#start test
print "Starting test sequence T2-Tutorial"
device = MonkeyRunner.waitForConnection()
displayWidth = device.getProperty("display.width")
displayHeight = device.getProperty("display.height")
print "Clicking the 'play' button"
device.touch (calcWidth(450), calcHeight(270), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'Level 1' button"
device.touch (calcWidth(150), calcHeight(290), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'OK' button 1st tutorial screen"
device.touch (calcWidth(450), calcHeight(470), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'OK' button 2nd tutorial screen"
device.touch (calcWidth(450), calcHeight(470), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'OK' button 3rd tutorial screen"
device.touch (calcWidth(450), calcHeight(470), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'OK' button 4th tutorial screen"
device.touch (calcWidth(450), calcHeight(470), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'OK' button 5th tutorial screen"
device.touch (calcWidth(450), calcHeight(470), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the 'OK' button 6th tutorial screen"
device.touch (calcWidth(450), calcHeight(470), "DOWN_AND_UP")
MonkeyRunner.sleep(1)

print "Finishing the test sequence"