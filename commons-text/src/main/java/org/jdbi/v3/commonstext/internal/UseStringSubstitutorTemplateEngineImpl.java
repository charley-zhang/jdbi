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
package org.jdbi.v3.commonstext.internal;

import java.lang.annotation.Annotation;

import org.jdbi.v3.commonstext.StringSubstitutorTemplateEngine;
import org.jdbi.v3.commonstext.UseStringSubstitutorTemplateEngine;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.extension.SimpleExtensionConfigurer;
import org.jdbi.v3.core.statement.SqlStatements;
import org.jdbi.v3.core.statement.TemplateEngine;

public class UseStringSubstitutorTemplateEngineImpl extends SimpleExtensionConfigurer {

    @Override
    public void configure(ConfigRegistry config, Annotation annotation, Class<?> sqlObjectType) {
        UseStringSubstitutorTemplateEngine anno = (UseStringSubstitutorTemplateEngine) annotation;
        TemplateEngine engine = StringSubstitutorTemplateEngine.between(anno.prefix(), anno.suffix(), anno.escape());
        config.get(SqlStatements.class).setTemplateEngine(engine);
    }
}
