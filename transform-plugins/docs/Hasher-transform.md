# Hasher Transform


## Description
Hashes fields using a digest algorithm such as ``MD2``, ``MD5``, ``SHA1``, ``SHA256``, ``SHA384``, or ``SHA512``.


## Configuration
**fields:** Specifies the fields to be hashed. Only `String` values are allowed. Hashed output would also be of type String.

**hash:** Specifies the hashing algorithm.

***Note:*** Output schema of this plugin would be same as input schema.

## Example

**Input Data**

```
+==========================================+
| duration |  name  | connects | bytecount |
+==========================================+
|      9   | C5089  |   C24735 |   152     |
|     13   | C11573 |   C5736  |   272     |
|      7   | C20101 |   C5720  |   162     |
+==========================================+
```

**Plugin Configuration**

`To hash 'name' and 'connects' fields from input using MD5 algorithm`
```
{
    "name": "MD5/SHA Field Dataset",
    "plugin": {
        "name": "Hasher",
        "type": "transform",
        "label": "MD5/SHA Field Dataset",
        "artifact": {
            "name": "transform-plugins",
            "version": "2.1.1-SNAPSHOT_5.1.2047",
            "scope": "SYSTEM"
        },
        "properties": {
            "hash": "MD5",
            "fields": "name,connects"
        }
    }
}
```

**Output Data**

```
+============================================================================================+
| duration |                name              |             connects             | bytecount |
+============================================================================================+
|      9   | 9cfdb22658f71105e99a42f40b3dde45 | 4c9535e27a40026a6dae794c796af73b |   152     |
|     13   | 2d399c093016bd2fca48195705b482ec | 3dd975eb76b9e12a40cc8fc3f9b7105e |   272     |
|      7   | e3bcdc59184318064887fcb7e2d4320e | 3d6ab34a324aba9febe6c2c0bdaea207 |   162     |
+============================================================================================+
```