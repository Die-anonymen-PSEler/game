from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import commands
import sys
import os
# Prerequisites:
# - the main menu screen is displayed
# - there are less than 5 profiles stored not named 'ann'
# Device display resolution: 960x540

#define methods
def calcWidth(width): return int(int(displayWidth) * int(width) / int(960))
def calcHeight(height): return int(int(displayHeight) * int(height) / int(540))

#start test
print "Starting test sequence T1"
device = MonkeyRunner.waitForConnection()
displayWidth = device.getProperty("display.width")
displayHeight = device.getProperty("display.height")
print "Clicking the 'user' button"
device.touch (calcWidth(900), calcHeight(340), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the plus symobol"
device.touch (calcWidth(480), calcHeight(500), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Clicking the text field"
device.touch (calcWidth(800), calcHeight(170), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Pressing the letter a"
device.touch (calcWidth(60), calcHeight(405), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Pressing the letter n"
device.touch (calcWidth(610), calcHeight(450), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Pressing the letter n"
device.touch (calcWidth(610), calcHeight(450), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Close keyboard"
device.touch (calcWidth(940), calcHeight(520), "DOWN_AND_UP")
MonkeyRunner.sleep(1)
print "Pressing the 'OK'Button"
device.touch (calcWidth(540), calcHeight(500), "DOWN_AND_UP")
MonkeyRunner.sleep(1)

print "Finishing the test sequence"