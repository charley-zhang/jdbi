/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdbi.v3.sqlobject.config.internal;

import java.lang.annotation.Annotation;

import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.extension.SimpleExtensionConfigurer;
import org.jdbi.v3.core.mapper.RowMappers;
import org.jdbi.v3.sqlobject.config.RegisterRowMapperFactory;

public class RegisterRowMapperFactoryImpl extends SimpleExtensionConfigurer {

    @Override
    public void configure(ConfigRegistry config, Annotation annotation, Class<?> sqlObjectType) {
        RegisterRowMapperFactory registerRowMapperFactory = (RegisterRowMapperFactory) annotation;
        RowMappers mappers = config.get(RowMappers.class);
        try {
            mappers.register(registerRowMapperFactory.value().getConstructor().newInstance());
        } catch (ReflectiveOperationException | SecurityException e) {
            throw new IllegalStateException("Unable to instantiate row mapper factory class " + registerRowMapperFactory.value(), e);
        }
    }
}
