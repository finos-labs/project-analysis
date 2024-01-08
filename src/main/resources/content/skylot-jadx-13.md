#Missing blocks

Owner: skylot

Repo: jadx

Labels: 

## Adam77Root (30 Jul 2014)

First of all thanks for your amazing work! This is by far the best dex decompiler available.

I'm using the latest 0.5.2 version built from sources and encounter many 'Missing block' and therefore 'Inconsistent code' errors while decompiling Android frameworks, both AOSP compiled and LG custom ones. A few examples all from AOSP code:

1.) The error:

```
DEBUG -  Missing block: B:25:? in android.os.Parcel.readParcelable(java.lang.ClassLoader):T
ERROR - Inconsistent code in method: android.os.Parcel.readParcelable(java.lang.ClassLoader):T
```

Original source: https://github.com/android/platform_frameworks_base/blob/android-4.1.2_r2.1/core/java/android/os/Parcel.java#L2048
Decompiled source:

```
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends android.os.Parcelable> T readParcelable(java.lang.ClassLoader r11_loader) {
        throw new UnsupportedOperationException("Method not decompiled: android.os.Parcel.readParcelable(java.lang.ClassLoader):T");
        /*
        r10 = this;
        r6 = 0;
        r5 = r10.readString();
        if (r5 != 0) goto L_0x0008;
    L_0x0007:
        return r6;
    L_0x0008:
        r7 = mCreators;
        monitor-enter(r7);
        r6 = mCreators;  Catch:{ all -> 0x0055 }
        r4 = r6.get(r11);    Catch:{ all -> 0x0055 }
        r4 = (java.util.HashMap) r4;     Catch:{ all -> 0x0055 }
        if (r4 != 0) goto L_0x001f;
    L_0x0015:
        r4 = new java.util.HashMap;  Catch:{ all -> 0x0055 }
        r4.<init>();     Catch:{ all -> 0x0055 }
        r6 = mCreators;  Catch:{ all -> 0x0055 }
        r6.put(r11, r4);     Catch:{ all -> 0x0055 }
    L_0x001f:
        r1 = r4.get(r5);     Catch:{ all -> 0x0055 }
        r1 = (android.os.Parcelable.Creator) r1;     Catch:{ all -> 0x0055 }
        if (r1 != 0) goto L_0x010d;
    L_0x0027:
        if (r11 != 0) goto L_0x0058;
    L_0x0029:
        r0 = java.lang.Class.forName(r5);    Catch:{ IllegalAccessException -> 0x005e, ClassNotFoundException -> 0x009a, ClassCastException -> 0x00d6, NoSuchFieldException -> 0x00f0 }
    L_0x002d:
        r6 = "CREATOR";
        r3 = r0.getField(r6);    Catch:{ IllegalAccessException -> 0x005e, ClassNotFoundException -> 0x009a, ClassCastException -> 0x00d6, NoSuchFieldException -> 0x00f0 }
        r6 = 0;
        r1 = r3.get(r6);     Catch:{ IllegalAccessException -> 0x005e, ClassNotFoundException -> 0x009a, ClassCastException -> 0x00d6, NoSuchFieldException -> 0x00f0 }
        r1 = (android.os.Parcelable.Creator) r1;     Catch:{ IllegalAccessException -> 0x005e, ClassNotFoundException -> 0x009a, ClassCastException -> 0x00d6, NoSuchFieldException -> 0x00f0 }
        if (r1 != 0) goto L_0x010a;
    L_0x003c:
        r6 = new android.os.BadParcelableException;  Catch:{ all -> 0x0055 }
        r8 = new java.lang.StringBuilder;    Catch:{ all -> 0x0055 }
        r8.<init>();     Catch:{ all -> 0x0055 }
        r9 = "Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class ";
        r8 = r8.append(r9);  Catch:{ all -> 0x0055 }
        r8 = r8.append(r5);  Catch:{ all -> 0x0055 }
        r8 = r8.toString();  Catch:{ all -> 0x0055 }
        r6.<init>(r8);   Catch:{ all -> 0x0055 }
        throw r6;    Catch:{ all -> 0x0055 }
    L_0x0055:
        r6 = move-exception;
        monitor-exit(r7);    Catch:{ all -> 0x0055 }
        throw r6;
    L_0x0058:
        r6 = 1;
        r0 = java.lang.Class.forName(r5, r6, r11);   Catch:{ IllegalAccessException -> 0x005e, ClassNotFoundException -> 0x009a, ClassCastException -> 0x00d6, NoSuchFieldException -> 0x00f0 }
        goto L_0x002d;
    L_0x005e:
        r2 = move-exception;
        r6 = "Parcel";
        r8 = new java.lang.StringBuilder;    Catch:{ all -> 0x0055 }
        r8.<init>();     Catch:{ all -> 0x0055 }
        r9 = "Class not found when unmarshalling: ";
        r8 = r8.append(r9);  Catch:{ all -> 0x0055 }
        r8 = r8.append(r5);  Catch:{ all -> 0x0055 }
        r9 = ", e: ";
        r8 = r8.append(r9);  Catch:{ all -> 0x0055 }
        r8 = r8.append(r2);  Catch:{ all -> 0x0055 }
        r8 = r8.toString();  Catch:{ all -> 0x0055 }
        android.util.Log.e(r6, r8);  Catch:{ all -> 0x0055 }
        r6 = new android.os.BadParcelableException;  Catch:{ all -> 0x0055 }
        r8 = new java.lang.StringBuilder;    Catch:{ all -> 0x0055 }
        r8.<init>();     Catch:{ all -> 0x0055 }
        r9 = "IllegalAccessException when unmarshalling: ";
        r8 = r8.append(r9);  Catch:{ all -> 0x0055 }
        r8 = r8.append(r5);  Catch:{ all -> 0x0055 }
        r8 = r8.toString();  Catch:{ all -> 0x0055 }
        r6.<init>(r8);   Catch:{ all -> 0x0055 }
        throw r6;    Catch:{ all -> 0x0055 }
    L_0x009a:
        r2 = move-exception;
        r6 = "Parcel";
        r8 = new java.lang.StringBuilder;    Catch:{ all -> 0x0055 }
        r8.<init>();     Catch:{ all -> 0x0055 }
        r9 = "Class not found when unmarshalling: ";
        r8 = r8.append(r9);  Catch:{ all -> 0x0055 }
        r8 = r8.append(r5);  Catch:{ all -> 0x0055 }
        r9 = ", e: ";
        r8 = r8.append(r9);  Catch:{ all -> 0x0055 }
        r8 = r8.append(r2);  Catch:{ all -> 0x0055 }
        r8 = r8.toString();  Catch:{ all -> 0x0055 }
        android.util.Log.e(r6, r8);  Catch:{ all -> 0x0055 }
        r6 = new android.os.BadParcelableException;  Catch:{ all -> 0x0055 }
        r8 = new java.lang.StringBuilder;    Catch:{ all -> 0x0055 }
        r8.<init>();     Catch:{ all -> 0x0055 }
        r9 = "ClassNotFoundException when unmarshalling: ";
        r8 = r8.append(r9);  Catch:{ all -> 0x0055 }
        r8 = r8.append(r5);  Catch:{ all -> 0x0055 }
        r8 = r8.toString();  Catch:{ all -> 0x0055 }
        r6.<init>(r8);   Catch:{ all -> 0x0055 }
        throw r6;    Catch:{ all -> 0x0055 }
    L_0x00d6:
        r2 = move-exception;
        r6 = new android.os.BadParcelableException;  Catch:{ all -> 0x0055 }
        r8 = new java.lang.StringBuilder;    Catch:{ all -> 0x0055 }
        r8.<init>();     Catch:{ all -> 0x0055 }
        r9 = "Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class ";
        r8 = r8.append(r9);  Catch:{ all -> 0x0055 }
        r8 = r8.append(r5);  Catch:{ all -> 0x0055 }
        r8 = r8.toString();  Catch:{ all -> 0x0055 }
        r6.<init>(r8);   Catch:{ all -> 0x0055 }
        throw r6;    Catch:{ all -> 0x0055 }
    L_0x00f0:
        r2 = move-exception;
        r6 = new android.os.BadParcelableException;  Catch:{ all -> 0x0055 }
        r8 = new java.lang.StringBuilder;    Catch:{ all -> 0x0055 }
        r8.<init>();     Catch:{ all -> 0x0055 }
        r9 = "Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class ";
        r8 = r8.append(r9);  Catch:{ all -> 0x0055 }
        r8 = r8.append(r5);  Catch:{ all -> 0x0055 }
        r8 = r8.toString();  Catch:{ all -> 0x0055 }
        r6.<init>(r8);   Catch:{ all -> 0x0055 }
        throw r6;    Catch:{ all -> 0x0055 }
    L_0x010a:
        r4.put(r5, r1);  Catch:{ all -> 0x0055 }
    L_0x010d:
        monitor-exit(r7);    Catch:{ all -> 0x0055 }
        r6 = r1 instanceof android.os.Parcelable.ClassLoaderCreator;
        if (r6 == 0) goto L_0x011c;
    L_0x0112:
        r1 = (android.os.Parcelable.ClassLoaderCreator) r1;
        r6 = r1.createFromParcel(r10, r11);
        r6 = (android.os.Parcelable) r6;
        goto L_0x0007;
    L_0x011c:
        r6 = r1.createFromParcel(r10);
        r6 = (android.os.Parcelable) r6;
        goto L_0x0007;
        */
    }
```

2.) The error:

```
DEBUG -  Missing block: B:24:0x01aa in com.android.server.location.GpsLocationProvider.<init>(android.content.Context, android.location.ILocationManager):void
ERROR - Inconsistent code in method: com.android.server.location.GpsLocationProvider.<init>(android.content.Context, android.location.ILocationManager):void
```

Original source: https://github.com/android/platform_frameworks_base/blob/android-4.1.2_r2.1/services/java/com/android/server/location/GpsLocationProvider.java#L384
Decompiled source:

```
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GpsLocationProvider(android.content.Context r12_context, android.location.ILocationManager r13_locationManager) {
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.location.GpsLocationProvider.<init>(android.content.Context, android.location.ILocationManager):void");
        /*
        r11 = this;
        r10 = 1;
        r8 = 32;
        r9 = 0;
        r11.<init>();
        r11.mLocationFlags = r9;
        r11.mStatus = r10;
        r6 = android.os.SystemClock.elapsedRealtime();
        r11.mStatusUpdateTime = r6;
        r11.mInjectNtpTimePending = r9;
        r11.mDownloadXtraDataPending = r9;
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r11.mFixInterval = r6;
        r6 = 0;
        r11.mFixRequestTime = r6;
        r11.mTTFF = r9;
        r6 = new android.location.Location;
        r7 = "gps";
        r6.<init>(r7);
        r11.mLocation = r6;
        r6 = new android.os.Bundle;
        r6.<init>();
        r11.mLocationExtras = r6;
        r6 = new java.util.ArrayList;
        r6.<init>();
        r11.mListeners = r6;
        r6 = new java.util.concurrent.CountDownLatch;
        r6.<init>(r10);
        r11.mInitializedLatch = r6;
        r6 = new android.util.SparseIntArray;
        r6.<init>();
        r11.mClientUids = r6;
        r6 = new com.android.server.location.GpsLocationProvider$1;
        r6.<init>();
        r11.mGpsStatusProvider = r6;
        r6 = new com.android.server.location.GpsLocationProvider$2;
        r6.<init>();
        r11.mBroadcastReciever = r6;
        r6 = new com.android.server.location.GpsLocationProvider$3;
        r6.<init>();
        r11.mNetInitiatedListener = r6;
        r6 = new int[r8];
        r11.mSvs = r6;
        r6 = new float[r8];
        r11.mSnrs = r6;
        r6 = new float[r8];
        r11.mSvElevations = r6;
        r6 = new float[r8];
        r11.mSvAzimuths = r6;
        r6 = 3;
        r6 = new int[r6];
        r11.mSvMasks = r6;
        r6 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r6 = new byte[r6];
        r11.mNmeaBuffer = r6;
        r11.mContext = r12;
        r6 = android.util.NtpTrustedTime.getInstance(r12);
        r11.mNtpTime = r6;
        r11.mLocationManager = r13;
        r6 = new com.android.internal.location.GpsNetInitiatedHandler;
        r6.<init>(r12);
        r11.mNIHandler = r6;
        r6 = r11.mLocation;
        r7 = r11.mLocationExtras;
        r6.setExtras(r7);
        r6 = r11.mContext;
        r7 = "power";
        r4 = r6.getSystemService(r7);
        r4 = (android.os.PowerManager) r4;
        r6 = "GpsLocationProvider";
        r6 = r4.newWakeLock(r10, r6);
        r11.mWakeLock = r6;
        r6 = r11.mWakeLock;
        r6.setReferenceCounted(r9);
        r6 = r11.mContext;
        r7 = "alarm";
        r6 = r6.getSystemService(r7);
        r6 = (android.app.AlarmManager) r6;
        r11.mAlarmManager = r6;
        r6 = r11.mContext;
        r7 = new android.content.Intent;
        r8 = "com.android.internal.location.ALARM_WAKEUP";
        r7.<init>(r8);
        r6 = android.app.PendingIntent.getBroadcast(r6, r9, r7, r9);
        r11.mWakeupIntent = r6;
        r6 = r11.mContext;
        r7 = new android.content.Intent;
        r8 = "com.android.internal.location.ALARM_TIMEOUT";
        r7.<init>(r8);
        r6 = android.app.PendingIntent.getBroadcast(r6, r9, r7, r9);
        r11.mTimeoutIntent = r6;
        r2 = new android.content.IntentFilter;
        r2.<init>();
        r6 = "android.intent.action.DATA_SMS_RECEIVED";
        r2.addAction(r6);
        r6 = "sms";
        r2.addDataScheme(r6);
        r6 = "localhost";
        r7 = "7275";
        r2.addDataAuthority(r6, r7);
        r6 = r11.mBroadcastReciever;
        r12.registerReceiver(r6, r2);
        r2 = new android.content.IntentFilter;
        r2.<init>();
        r6 = "android.provider.Telephony.WAP_PUSH_RECEIVED";
        r2.addAction(r6);
        r6 = "application/vnd.omaloc-supl-init";
        r2.addDataType(r6);  Catch:{ MalformedMimeTypeException -> 0x0186 }
    L_0x00f7:
        r6 = r11.mBroadcastReciever;
        r12.registerReceiver(r6, r2);
        r6 = "connectivity";
        r6 = r12.getSystemService(r6);
        r6 = (android.net.ConnectivityManager) r6;
        r11.mConnMgr = r6;
        r6 = "batteryinfo";
        r6 = android.os.ServiceManager.getService(r6);
        r6 = com.android.internal.app.IBatteryStats.Stub.asInterface(r6);
        r11.mBatteryStats = r6;
        r6 = new java.util.Properties;
        r6.<init>();
        r11.mProperties = r6;
        r1 = new java.io.File;   Catch:{ IOException -> 0x01aa }
        r6 = "/etc/gps.conf";
        r1.<init>(r6);   Catch:{ IOException -> 0x01aa }
        r5 = new java.io.FileInputStream;    Catch:{ IOException -> 0x01aa }
        r5.<init>(r1);   Catch:{ IOException -> 0x01aa }
        r6 = r11.mProperties;    Catch:{ IOException -> 0x01aa }
        r6.load(r5);     Catch:{ IOException -> 0x01aa }
        r5.close();  Catch:{ IOException -> 0x01aa }
        r6 = r11.mProperties;    Catch:{ IOException -> 0x01aa }
        r7 = "NTP_SERVER";
        r8 = 0;
        r6 = r6.getProperty(r7, r8);     Catch:{ IOException -> 0x01aa }
        r11.mNtpServer = r6;     Catch:{ IOException -> 0x01aa }
        r6 = r11.mProperties;    Catch:{ IOException -> 0x01aa }
        r7 = "SUPL_HOST";
        r6 = r6.getProperty(r7);     Catch:{ IOException -> 0x01aa }
        r11.mSuplServerHost = r6;    Catch:{ IOException -> 0x01aa }
        r6 = r11.mProperties;    Catch:{ IOException -> 0x01aa }
        r7 = "SUPL_PORT";
        r3 = r6.getProperty(r7);     Catch:{ IOException -> 0x01aa }
        r6 = r11.mSuplServerHost;    Catch:{ IOException -> 0x01aa }
        if (r6 == 0) goto L_0x0156;
    L_0x014e:
        if (r3 == 0) goto L_0x0156;
    L_0x0150:
        r6 = java.lang.Integer.parseInt(r3);     Catch:{ NumberFormatException -> 0x0190 }
        r11.mSuplServerPort = r6;    Catch:{ NumberFormatException -> 0x0190 }
    L_0x0156:
        r6 = r11.mProperties;    Catch:{ IOException -> 0x01aa }
        r7 = "C2K_HOST";
        r6 = r6.getProperty(r7);     Catch:{ IOException -> 0x01aa }
        r11.mC2KServerHost = r6;     Catch:{ IOException -> 0x01aa }
        r6 = r11.mProperties;    Catch:{ IOException -> 0x01aa }
        r7 = "C2K_PORT";
        r3 = r6.getProperty(r7);     Catch:{ IOException -> 0x01aa }
        r6 = r11.mC2KServerHost;     Catch:{ IOException -> 0x01aa }
        if (r6 == 0) goto L_0x0174;
    L_0x016c:
        if (r3 == 0) goto L_0x0174;
    L_0x016e:
        r6 = java.lang.Integer.parseInt(r3);     Catch:{ NumberFormatException -> 0x01b3 }
        r11.mC2KServerPort = r6;     Catch:{ NumberFormatException -> 0x01b3 }
    L_0x0174:
        r6 = new com.android.server.location.GpsLocationProvider$GpsLocationProviderThread;
        r6.<init>();
        r11.mThread = r6;
        r6 = r11.mThread;
        r6.start();
    L_0x0180:
        r6 = r11.mInitializedLatch;  Catch:{ InterruptedException -> 0x01cd }
        r6.await();  Catch:{ InterruptedException -> 0x01cd }
        return;
    L_0x0186:
        r0 = move-exception;
        r6 = "GpsLocationProvider";
        r7 = "Malformed SUPL init mime type";
        android.util.Log.w(r6, r7);
        goto L_0x00f7;
    L_0x0190:
        r0 = move-exception;
        r6 = "GpsLocationProvider";
        r7 = new java.lang.StringBuilder;    Catch:{ IOException -> 0x01aa }
        r7.<init>();     Catch:{ IOException -> 0x01aa }
        r8 = "unable to parse SUPL_PORT: ";
        r7 = r7.append(r8);  Catch:{ IOException -> 0x01aa }
        r7 = r7.append(r3);  Catch:{ IOException -> 0x01aa }
        r7 = r7.toString();  Catch:{ IOException -> 0x01aa }
        android.util.Log.e(r6, r7);  Catch:{ IOException -> 0x01aa }
        goto L_0x0156;
    L_0x01aa:
        r0 = move-exception;
        r6 = "GpsLocationProvider";
        r7 = "Could not open GPS configuration file /etc/gps.conf";
        android.util.Log.w(r6, r7);
        goto L_0x0174;
    L_0x01b3:
        r0 = move-exception;
        r6 = "GpsLocationProvider";
        r7 = new java.lang.StringBuilder;    Catch:{ IOException -> 0x01aa }
        r7.<init>();     Catch:{ IOException -> 0x01aa }
        r8 = "unable to parse C2K_PORT: ";
        r7 = r7.append(r8);  Catch:{ IOException -> 0x01aa }
        r7 = r7.append(r3);  Catch:{ IOException -> 0x01aa }
        r7 = r7.toString();  Catch:{ IOException -> 0x01aa }
        android.util.Log.e(r6, r7);  Catch:{ IOException -> 0x01aa }
        goto L_0x0174;
    L_0x01cd:
        r0 = move-exception;
        r6 = java.lang.Thread.currentThread();
        r6.interrupt();
        goto L_0x0180;
        */
    }
```

In this latter case the following block seems to be missing, but it isn't actually as it's there in decompiled source:

```
        } catch (IOException e) {
            Log.w(TAG, "Could not open GPS configuration file " + PROPERTIES_FILE);
        }
```


## Adam77Root (31 Jul 2014)

1.) is fixed with the latest commit, thanks!


