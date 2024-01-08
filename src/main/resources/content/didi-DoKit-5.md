#@import CoreTelephony，@import EventKit; 这种方式不支持

Owner: didi

Repo: DoKit

Labels: 

## S209 (30 Oct 2018)

@import CoreTelephony，@import EventKit; 要换成 #import <EventKit/EventKit.h>
#import <CoreTelephony/CTCellularData.h>，这中格式才可以。

## yixiangboy (31 Oct 2018)

tag = 1.0.3  已解决


