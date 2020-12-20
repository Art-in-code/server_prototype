package com.systemsample.storage;

import com.systemsample.SensorsData;

public interface SensorsDataSnapshotStorage {
   void updateSnapshot(SensorsData data);
   SensorsDataSnapshot getSnapshot();
}
