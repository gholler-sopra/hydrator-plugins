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

package co.cask.hydrator.plugin;

import co.cask.cdap.api.annotation.Description;
import co.cask.cdap.api.annotation.Name;
import co.cask.cdap.api.annotation.Plugin;
import co.cask.cdap.api.data.format.StructuredRecord;
import co.cask.cdap.api.data.schema.Schema;
import co.cask.cdap.api.plugin.PluginConfig;
import co.cask.cdap.etl.api.Emitter;
import co.cask.cdap.etl.api.PipelineConfigurer;
import co.cask.cdap.etl.api.Transform;
import co.cask.cdap.etl.api.TransformContext;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import javax.ws.rs.Path;

/**
 * Hash field(s) values using one the the digest algorithms.
 */
@Plugin(type = "transform")
@Name("Hasher")
@Description("Encodes field values using one of the digest algorithms. MD2, MD5, SHA1, SHA256, " +
  "SHA384 and SHA512 are the supported message digest algorithms.")
public final class Hasher extends Transform<StructuredRecord, StructuredRecord> {
  private final Config config;
  private Set<String> fieldSet = new HashSet<>();

  private static final Set<String> VALID_HASHERS = Stream.of("MD2", "MD5", "SHA1", "SHA256", "SHA384", "SHA512").collect(Collectors.toCollection(HashSet::new));

  // For testing purpose only.
  public Hasher(Config config) {
    this.config = config;
  }

  @Override
  public void initialize(TransformContext context) throws Exception {
    super.initialize(context);
    config.validate(context.getInputSchema());
    // Split the fields to be hashed.
    String[] fields = config.fields.split(",");
    for (String field : fields) {
      fieldSet.add(field);
    }
  }

  @Override
  public void configurePipeline(PipelineConfigurer pipelineConfigurer) throws IllegalArgumentException {
    super.configurePipeline(pipelineConfigurer);
    Schema inputSchema = pipelineConfigurer.getStageConfigurer().getInputSchema();
    config.validate(inputSchema);
    pipelineConfigurer.getStageConfigurer().setOutputSchema(inputSchema);
  }

  @Override
  public void transform(StructuredRecord in, Emitter<StructuredRecord> emitter) throws Exception {
    StructuredRecord.Builder builder = StructuredRecord.builder(in.getSchema());
    
    List<Schema.Field> fields = in.getSchema().getFields();
    for (Schema.Field field : fields) {
      String name = field.getName();
      if (fieldSet.contains(name)) {
        String value = in.get(name);
        String digest = value;
        switch(config.hash.toLowerCase()) {
          case "md2":
            digest = DigestUtils.md2Hex(value);
            break;
          case "md5":
            digest = DigestUtils.md5Hex(value);
            break;
          case "sha1":
            digest = DigestUtils.sha1Hex(value);
            break;
          case "sha256":
            digest = DigestUtils.sha256Hex(value);
            break;
          case "sha384":
            digest = DigestUtils.sha384Hex(value);
            break;
          case "sha512":
            digest = DigestUtils.sha512Hex(value);
            break;
        }
        builder.set(name, digest);
      } else {
        builder.set(name, in.get(name));
      }
    }
    emitter.emit(builder.build());
  }

  @Path("outputSchema")
  public Schema getOutputSchema(GetSchemaRequest request) {
    return request.inputSchema;
  }

  /**
   * Endpoint request for output schema.
   */
  public static class GetSchemaRequest extends Config {
    public Schema inputSchema;
  }

  /**
   * Hasher Plugin Config.
   */
  public static class Config extends PluginConfig {

    @Name("hash")
    @Description("Specifies the Hash method for hashing fields.")
    private String hash;
    
    @Name("fields")
    @Description("List of fields to hash. Only string fields are allowed")
    private String fields;

    public Config() {
    }

    public Config(String hash, String fields) {
      this.hash = hash;
      this.fields = fields;
    }

    private void validate(Schema inputSchema) {
      // Checks if hash specified is one of the supported types.
      if (!VALID_HASHERS.contains(hash.toUpperCase())) {
        throw new IllegalArgumentException("Invalid hasher '" + hash + "' specified. Allowed hashers are " + VALID_HASHERS);
      }
      if (fields == null || "".equals(fields.trim())) {
        throw new IllegalArgumentException("Fields can not be empty");
      }
      String[] fieldArr = fields.split(",");
      for (String field : fieldArr) {
        if (inputSchema.getField(field) == null) {
          throw new IllegalArgumentException("Invalid field: '" + field + "', not present in input schema");
        } else {
          Schema fieldSchema = inputSchema.getField(field).getSchema();
          if (fieldSchema.isNullable()) {
            fieldSchema = fieldSchema.getNonNullable();
          }
          if (fieldSchema.getType() != Schema.Type.STRING) {
            throw new IllegalArgumentException("Invalid field: '" + field + "', only string values are allowed");
          }
        }
      }
    }
  }
}

