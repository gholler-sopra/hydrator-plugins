# Compressor Transform


Description
-----------
Compressor transform is used for compressing the fields of a dataset using the configured compression algorithm. You can specify multiple fields to be compressed using different compression algorithms.
The accelerator supports SNAPPY, ZIP, and GZIP types of compression.


Configuration
-------------
You need to specify the following configuration for the accelerator:
**compressor:** List of key value pairs. Key represents the input field that needs to be compressed and Value represents the compression algorithm to be used.

**Output Schema:** Output schema will be same as the input schema except one difference that the compressed fields in the output schema will have the same field name but type as *Non-nullable* ``BYTES``.

**Note**: 
- Do not use sink plugins that store data in textual format because Compressor converts the field values to `bytes` and the text-based sink plugin will convert `bytes` to `string` at the time of writing the data.
Use any columnar format like `ORC`, `Parquet`, etc.
- Input fields that need to compressed must be of type `String` or `Bytes` and non-nullable.

Example
-------

This example compresses the fields fname, lname, and cost of a dataset using the compression format provided with the field.

```
{
    "name": "Compressor",
    "type": "transform",
    "properties": {
        "compressor": "fname:SNAPPY,lname:ZIP,cost:GZIP"
    }
}
```

Input

    +======================================+
    | fname  | lname   | cost   |  zipcode |
    +======================================+
    | bob    | smith   | 50.23  |  12345   |
    | bob    | jones   | 30.64  |  23456   |
    | alice  | smith   | 1.50   |  34567   |
    | bob    | smith   | 0.50   |  45678   |
    | alice  | smith   | 30.21  |  56789   |
    | alice  | jones   | 500.93 |  67890   |
    +======================================+

Output
```
+======================================================================+
|        fname       |        lname       |         cost       |zipcode|
+======================================================================+
|[03 08 62 6F 62]    |[50 4B 03 04 14 0...|[1F 8B 08 00 00 0...| 12345 |
|[03 08 62 6F 62]    |[50 4B 03 04 14 0...|[1F 8B 08 00 00 0...| 23456 |
|[05 10 61 6C 69 6...|[50 4B 03 04 14 0...|[1F 8B 08 00 00 0...| 34567 |
|[03 08 62 6F 62]    |[50 4B 03 04 14 0...|[1F 8B 08 00 00 0...| 45678 |
|[05 10 61 6C 69 6...|[50 4B 03 04 14 0...|[1F 8B 08 00 00 0...| 56789 |
|[05 10 61 6C 69 6...|[50 4B 03 04 14 0...|[1F 8B 08 00 00 0...| 67890 |
+======================================================================+
```
