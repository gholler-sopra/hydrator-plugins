/*
 * Copyright © 2015 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.hydrator.plugin.common;

/**
 * Class to define property names for source and sinks
 */
public final class Properties {

  /**
   * Class to hold properties for FileBatchSource
   */
  public static class File {
    public static final String FILESYSTEM = "fileSystem";
    public static final String FILESYSTEM_PROPERTIES = "fileSystemProperties";
    public static final String PATH = "path";
    public static final String FILE_REGEX = "fileRegex";
    public static final String TIME_TABLE = "timeTable";
    public static final String INPUT_FORMAT_CLASS = "inputFormatClass";
    public static final String FORMAT = "format";
    public static final String SCHEMA = "schema";
    public static final String MAX_SPLIT_SIZE = "maxSplitSize";
    public static final String IGNORE_NON_EXISTING_FOLDERS = "ignoreNonExistingFolders";
    public static final String RECURSIVE = "recursive";
  }

  /**
   * Properties for the StreamBatchSource as well as the real-time StreamSink
   */
  public static class Stream {
    public static final String NAME = "name";
    public static final String SCHEMA = "schema";
    public static final String FORMAT = "format";
    public static final String DELAY = "delay";
    public static final String DURATION = "duration";
    public static final String BODY_FIELD = "body.field";
    public static final String DEFAULT_BODY_FIELD = "body";
    public static final String HEADERS_FIELD = "headers.field";
    public static final String DEFAULT_HEADERS_FIELD = "headers";
  }

  /**
   * Properties for the TimePartitionedFileSetDatasetAvroSink
   */
  public static class TimePartitionedFileSetDataset {
    public static final String TPFS_NAME = "name";
    public static final String SCHEMA = "schema";
    public static final String BASE_PATH = "basePath";
    public static final String DURATION = "duration";
    public static final String DELAY = "delay";
    public static final String FILE_PATH_FORMAT = "filePathFormat";
    public static final String OUTPUT_TIME_ZONE = "timeZone";
  }

  /**
   * Properties for KeyValueTables
   */
  public static class KeyValueTable {
    public static final String KEY_FIELD = "key.field";
    public static final String VALUE_FIELD = "value.field";
    public static final String DEFAULT_KEY_FIELD = "key";
    public static final String DEFAULT_VALUE_FIELD = "value";
  }

  /**
   * Properties for Cube
   */
  public static class Cube {
    public static final String DATASET_NAME = "name";
    public static final String DATASET_RESOLUTIONS =
      co.cask.cdap.api.dataset.lib.cube.Cube.PROPERTY_RESOLUTIONS;
    public static final String DATASET_OTHER = "dataset.cube.properties";
    public static final String AGGREGATIONS = "dataset.cube.aggregations";

    public static final String FACT_TS_FIELD = "cubeFact.timestamp.field";
    public static final String FACT_TS_FORMAT = "cubeFact.timestamp.format";
    public static final String MEASUREMENT_PREFIX = "cubeFact.measurement.";

    public static final String MEASUREMENTS = "cubeFact.measurements";
  }

  /**
   * Properties for Tables
   */
  public static class Table {
    public static final String NAME = "name";
    public static final String PROPERTY_SCHEMA = co.cask.cdap.api.dataset.table.Table.PROPERTY_SCHEMA;
    public static final String PROPERTY_SCHEMA_ROW_FIELD =
      co.cask.cdap.api.dataset.table.Table.PROPERTY_SCHEMA_ROW_FIELD;
  }

  /**
   * Properties for SnapshotFileSets
   */
  public static class SnapshotFileSetSink {
    public static final String NAME = "name";
    public static final String BASE_PATH = "basePath";
    public static final String FILE_PROPERTIES = "fileProperties";
  }

  /**
   * Common properties for BatchWritable source and sinks
   */
  public static class BatchReadableWritable {
    public static final String NAME = "name";
    public static final String TYPE = "type";
  }

  private Properties() {
  }
}
