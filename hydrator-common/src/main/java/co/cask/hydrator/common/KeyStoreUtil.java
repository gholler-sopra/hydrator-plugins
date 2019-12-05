/*
 * Copyright © 2015-2018 Cask Data, Inc.
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

package co.cask.hydrator.common;

import com.google.common.base.Strings;

import java.nio.file.Files;
import java.nio.file.Paths;

public class KeyStoreUtil {

    public static String getValidPath(String keyStorePath) {

        if (!Strings.isNullOrEmpty(keyStorePath)) {

            if (keyStorePath.startsWith("file://")) {
                keyStorePath = keyStorePath.substring(7);
            }

            // In case file is send through --files option in Spark-Submit the file will be available at "./" location
            // Eg : Path : "/var/log/mycert.p12" is available inside Spark as "./mycert.p12"

            // first check is file is available in local classpath (ie passed using --files option)
            String[] tokens = keyStorePath.split("/");
            if (tokens.length > 0) {
                String localPath = "./" + tokens[tokens.length - 1];
                if (Files.exists(Paths.get(localPath))) {
                    return localPath;
                }
            }

            // if file is available at full path on machine
            if (Files.exists(Paths.get(keyStorePath))) {
                return keyStorePath;
            }
        }

        return keyStorePath;
    }
}
