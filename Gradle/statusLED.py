import os
import sys
import thread
from bbio import *
pinMode(GPIO2_16, OUTPUT)
blink1 = 1
failure_count = 0

def blink():
        global blink1
        while 1:
                if blink1 == 1:
                        print "Toggling LED"
                        toggle(GPIO2_16)
                elif blink1 == 0:
                        print "Setting LED high"
                        digitalWrite(GPIO2_16, HIGH)
                delay(1000)

thread.start_new_thread(blink, ())

while 1:
        print "Starting ping"
        response = os.system("ping -c1 8.8.8.8 > /dev/null 2>&1")
        if response == 0:
                print "Ping worked"
                blink1 = 0
                failure_count = 0
        else:
                print "Ping failed"
                failure_count = failure_count + 1

        if failure_count > 0:
                print "Failure count > 0, setting blink1=1"
                blink1 = 1
        sys.stdout.flush()
        delay(60000)
